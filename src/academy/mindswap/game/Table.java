package academy.mindswap.game;

import academy.mindswap.server.ClientHandler;
import academy.mindswap.server.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static academy.mindswap.server.Server.NUMBER_OF_PLAYERS_PER_GAME;

public class Table implements Runnable {

	private static final int AMOUNT_OF_INITIAL_CARDS = 2;
	private static final double BLACKJACK_MULTIPLIER = 2.5;
	private static final double SIMPLE_WIN_MULTIPLIER = 2;

	private final Dealer dealer;
	private final DealerShoe dealerShoe;
	private final List<ClientHandler> clients;
	ExecutorService executorService;

	public Table() {
		this.clients = new ArrayList<>();
		this.dealer = new Dealer();
		this.dealerShoe = new DealerShoe();
		executorService = Executors.newCachedThreadPool();
	}

	@Override
	public void run() {
		startGame();
	}

	private void startGame() {
		if (clients.stream().allMatch(ClientHandler::isReadyToStart)) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException exception) {
				throw new RuntimeException(exception);
			}

			startGame();
		}

		dealerShoe.populateShoe();
		playBlackJack();
	}

	public void addClient(ClientHandler clientHandler) {
		clients.add(clientHandler);
		executorService.submit(clientHandler);
	}

	public boolean canStart() {
		return clients.size() >= NUMBER_OF_PLAYERS_PER_GAME;
	}

	private void playBlackJack() {
		try {
			dealFirstRound();
			playRound();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void playRound() throws InterruptedException {
		clients.forEach(client -> {
			Player player = client.getPlayer();
			checkBlackjack(client);
			do {
				client.showCards(player);
				showDealerCard(client);
				client.askForNextMove();
				if (player.wantMoreCards()) {
					dealCardTo(player);
					checkPlayer(client);
				}
			} while (player.wantMoreCards() && !player.isOutOfGame());
		});
		clients.forEach(client -> client.showCards(dealer));
		while (dealer.shouldDrawCards()) {
			dealCardTo(dealer);
			clients.forEach(client -> {
				client.sendMessageToUser(Messages.DEALER_DRAWING_CARD);
				client.showCards(dealer);
			});
		}
		checkResults(clients);
//		announcePaymentResults(clients);
	}

	// TODO: Fix concurrency issues
	private void announcePaymentResults(List<ClientHandler> clients) {
		clients.forEach(client -> {
			client.sendMessageToUser(String.format(Messages.PAYMENT, client.getPlayer().getValueWon()));
			client.sendMessageToUser(String.format(Messages.MONEY_AVAILABLE, client.getPlayer().getBudget()));
		});
	}

	private void checkBlackjack(ClientHandler client) {
		Player player = client.getPlayer();
		if (player.hasBlackJack()) {
			client.showCards(player);
			player.setIsOutOfGame(true);
		}
	}

	private void showDealerCard(ClientHandler client) {
		client.sendMessageToUser(String.format(
				Messages.SHOW_DEALER_FIRST_CARD,
				dealer.firstCard(),
				dealer.firstCard().getValue()
		));
	}

	private void checkPlayer(ClientHandler client) {
		Player player = client.getPlayer();
		if (player.hasBlackJack() || player.hasBusted()) {
			client.showCards(player);
			player.setIsOutOfGame(true);
		}
		if (player.hasBlackJack()) {
			client.sendMessageToUser(Messages.BLACKJACK);
			return;
		}
		if (player.hasBusted()) {
			client.sendMessageToUser(Messages.BUSTED);
		}
	}

	private void checkResults(List<ClientHandler> clients) {
		clients.forEach(client -> {
			checkPlayer(client);
			Player player = client.getPlayer();
			if (dealer.hasBusted()) {
				client.sendMessageToUser(Messages.BUSTED_DEALER);
				client.sendMessageToUser(Messages.SIMPLE_WIN);
				dealWithBets();
				return;
			}
			if (dealer.getScore() > player.getScore()) {
				client.sendMessageToUser(Messages.DEALER_WIN);
			} else if (dealer.getScore() == player.getScore()) {
				client.sendMessageToUser(Messages.DRAW);
			} else {
				client.sendMessageToUser(Messages.SIMPLE_WIN);
			}
			dealWithBets();
		});
	}

	private void dealWithBets() {
		int dealerScore = dealer.getScore();
		clients.forEach(client -> {
			Player player = client.getPlayer();
			double betMultiplier;
			if (player.hasBlackJack()) {
				betMultiplier = BLACKJACK_MULTIPLIER;
			} else if (player.getScore() > dealerScore || dealer.hasBusted()) {
				betMultiplier = SIMPLE_WIN_MULTIPLIER;
			} else {
				betMultiplier = 0;
			}
			pay(client, betMultiplier);
		});
	}

	private void pay(ClientHandler client, double betMultiplier) {
		client.getPlayer().setPayment(betMultiplier);
	}

	private void dealFirstRound() {
		for (int i = 0; i < AMOUNT_OF_INITIAL_CARDS; i++) {
			clients.forEach(client -> dealCardTo(client.getPlayer()));
			dealCardTo(dealer);
		}
	}

	private void dealCardTo(Person person) {
		Card card = dealerShoe.askForCard();
		person.addCard(card);
	}
}

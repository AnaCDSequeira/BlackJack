package academy.mindswap.game;

import academy.mindswap.server.ClientHandler;
import academy.mindswap.server.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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
		for (ClientHandler client : clients) {
			Player player = client.getPlayer();
			checkBlackjack(client);
			do {
				client.showCards(player);
				showDealerCard(client);
				client.askForCards();
				if (player.wantMoreCards()) {
					dealCardTo(player);
					checkPlayer(client);
				}
			} while (player.wantMoreCards() && !player.isOutOfGame());
		}
		while (dealer.shouldDrawCards()) {
			dealCardTo(dealer);
			for (ClientHandler client : clients) {
				client.sendMessageToUser(Messages.DEALER_DRAWING_CARD);
				client.showCards(dealer);
			}
		}
		roundCheck();
	}

	private void checkBlackjack(ClientHandler client) {
		Player player = client.getPlayer();
		if (player.hasBlackJack()) {
			client.showCards(player);
			dealWithBets();
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

	private void roundCheck() {
		for (ClientHandler client : clients) {
			checkPlayer(client);
		}
	}

	private void checkPlayer(ClientHandler client) {
		Player player = client.getPlayer();
		if (player.hasBlackJack() || player.hasBusted()) {
			client.showCards(player);
			dealWithBets();
			player.setIsOutOfGame(true);
		}
		if (player.hasBlackJack()) {
			client.sendMessageToUser(Messages.BLACKJACK);
			return;
		}
		if (player.hasBusted()) {
			client.sendMessageToUser(Messages.BUSTED);
			return;
		}
		if (dealer.hasBusted()) {
			client.sendMessageToUser(Messages.BUSTED_DEALER);
			client.sendMessageToUser(Messages.SIMPLE_WIN);
			return;
		}
		if (dealer.getScore() > player.getScore()) {
			client.sendMessageToUser(Messages.DEALER_WIN);
		} else if (dealer.getScore() == player.getScore()) {
			client.sendMessageToUser(Messages.DRAW);
		} else {
			client.sendMessageToUser(Messages.SIMPLE_WIN);
		}
	}

	private void dealWithBets() {
		int dealerScore = dealer.getScore();
		for (ClientHandler client : clients) {
			Player player = client.getPlayer();
			double betMultiplier;
			if (player.hasBlackJack()) {
				betMultiplier = BLACKJACK_MULTIPLIER;
			} else if (player.getScore() > dealerScore || dealer.hasBusted()) {
				betMultiplier = SIMPLE_WIN_MULTIPLIER;
			} else {
				betMultiplier = 0;
			}
			pay(player, betMultiplier);
		}
	}

	private void pay(Player player, double betMultiplier) {
		player.getPayment(betMultiplier);
	}

	private void dealFirstRound() {
		for (int i = 0; i < AMOUNT_OF_INITIAL_CARDS; i++) {
			for (ClientHandler client : clients) {
				dealCardTo(client.getPlayer());
			}
			dealCardTo(dealer);
		}
	}

	private void dealCardTo(Person person) {
		Card card = dealerShoe.askForCard();
		person.addCard(card);
	}
}

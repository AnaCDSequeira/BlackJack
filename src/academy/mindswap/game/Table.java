package academy.mindswap.game;

import academy.mindswap.server.ClientHandler;
import academy.mindswap.server.Messages;

import java.util.List;

public class Table implements Runnable {

	private static final int AMOUNT_OF_INITIAL_CARDS = 2;

	private static final double BLACKJACK_MULTIPLIER = 2.5;

	private static final double SIMPLE_WIN_MULTIPLIER = 2;
	private final Dealer dealer;
	private final DealerShoe dealerShoe;
	private volatile List<ClientHandler> clients;
	private boolean askedForCard; //TODO: associar o HIT

	public Table(List<ClientHandler> clients) {
		this.clients = clients;
		this.dealer = new Dealer();
		this.dealerShoe = new DealerShoe();
	}

	@Override
	public void run() {
		startGame();
	}

	private void startGame() {
		dealerShoe.populateShoe();
		playBlackJack();
	}

	private void playBlackJack() {
		try {
			dealFirstRound();
			roundCheck();
			playRound();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void playRound() throws InterruptedException {
		for (ClientHandler client : clients) {
			Player player = client.getPlayer();
			do {
				client.showCards();
				showDealerCard(client);
				client.askForCards();
				if (player.wantMoreCards()) {
					dealCardTo(player);
					checkPlayer(client);
				}
			} while (player.wantMoreCards() && !player.isOutOfGame());
			client.sendMessageToUser("Game is over.");
		}
	}

	private void showDealerCard(ClientHandler client) {
		client.sendMessageToUser(String.format(
				Messages.SHOW_DEALER_FIRST_CARD,
				dealer.firstCard(),
				dealer.firstCard().getValue())
		);
	}

	private void roundCheck() {
		for (ClientHandler client : clients) {
			checkPlayer(client);
		}
	}

	private void checkPlayer(ClientHandler client) {
		Player player = client.getPlayer();
		if (player.hasBlackJack() || player.hasBusted()) {
			client.showCards();
			dealWithBets();
			player.setIsOutOfGame(true);
		}
		if (player.hasBlackJack()) {
			client.sendMessageToUser(Messages.BLACKJACK);
		}
		if (player.hasBusted()) {
			client.sendMessageToUser(Messages.GAME_OVER);
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

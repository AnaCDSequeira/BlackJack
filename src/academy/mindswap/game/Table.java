package academy.mindswap.game;

import academy.mindswap.server.ClientHandler;
import academy.mindswap.server.Messages;

import java.util.List;

public class Table implements Runnable {

	private static final int AMOUNT_OF_INITIAL_CARDS = 2;

	private static final double BLACKJACK_MULTIPLIER = 2.5;

	private static final double SIMPLE_WIN_MULTIPLIER = 2;

	private final List<ClientHandler> clients;

	private final Dealer dealer;

	private final DealerShoe dealerShoe;

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
		dealFirstRound();
		roundCheck();
		while (clients.size() > 0) {
			playRound();
		}
	}

	private void playRound() {
		for (ClientHandler client : clients) {
			Player player = client.getPlayer();
			client.showCards();
			client.sendMessageToUser(Messages.SHOW_SCORE + client.getPlayer().getScore() + "\nDealer cards is: " + dealer.firstCard());
			while (client.wantMoreCards()) {
				dealCardTo(client.getPlayer());
				client.sendMessageToUser(client.getPlayer().getHand().toString());
				client.sendMessageToUser(Messages.SHOW_SCORE + client.getPlayer().getScore());
				checkPlayer(client);
				if (player.isOutOfGame()) {
					break;
				}
			}
		}
		while (dealer.canPlay()) {
			System.out.println("Estamos no dealer!!");
			dealCardTo(dealer);
		}
		roundCheck();
	}

	private void roundCheck() {
		for (ClientHandler client : clients) {
			checkPlayer(client);
		}
	}

	private void checkPlayer(ClientHandler client) {
		Player player = client.getPlayer();
		if (player.hasBlackJack() || player.hasBusted()) {
			dealWithBets();
			client.sendMessageToUser(Messages.GAME_OVER);
			player.setIsOutOfGame(true);
		}
	}

	private void dealWithBets() {
		int dealerScore = dealer.getScore();
		for (ClientHandler client : clients) {
			double betMultiplier;
			if (client.getPlayer().hasBlackJack()) {
				betMultiplier = BLACKJACK_MULTIPLIER;
			} else if (client.getPlayer().getScore() > dealerScore || dealer.hasBusted()) {
				betMultiplier = SIMPLE_WIN_MULTIPLIER;
			} else {
				betMultiplier = 0;
			}
			pay(client.getPlayer(), betMultiplier);
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

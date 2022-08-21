package academy.mindswap.Card;

import java.util.ArrayList;

public class Table {

	private static final int AMOUNT_OF_INITIAL_CARDS = 2;

	private static final double BLACKJACK_MULTIPLIER = 2.5;

	private static final double SIMPLE_WIN_MULTIPLIER = 2;

	private ArrayList<Player> players;

	private Dealer dealer;

	private DealerShoe dealerShoe;

	public Table(ArrayList<Player> players, Dealer dealer, DealerShoe dealerShoe) {
		this.players = players;
		this.dealer = dealer;
		this.dealerShoe = dealerShoe;
		startGame();
	}

	private void startGame() {
		dealerShoe.populateShoe();
		askForBets(players);
		dealFirstRound();
		dealCardsToPlayers();
		dealCardsToDealer();
		payToWinners();
	}

	private void payToWinners() {
		int dealerScore = dealer.getScore();
		for (Player player : players) {
			double betMultiplier;
			if (player.hasBlackJack()) {
				betMultiplier = BLACKJACK_MULTIPLIER;
			} else if (player.getScore() > dealerScore) {
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

	private void askForBets(ArrayList<Player> players) {
		for (Player player : players) {
			player.askForBet();
		}
	}

	private void dealFirstRound() {
		for (int i = 0; i < AMOUNT_OF_INITIAL_CARDS; i++) {
			for (Player player : players) {
				dealCardTo(player);
			}
			dealCardTo(dealer);
		}
	}

	private void dealCardTo(Player player) {
		Card card = dealerShoe.askForCard();
		player.addCard(card);
	}

	private void dealCardTo(Dealer dealer) {
		Card card = dealerShoe.askForCard();
		dealer.addCard(card);
	}

	private void dealCardsToPlayers() {
		for (Player player : players) {
			while (player.canPlay() && player.wantsToPlay()) {
				dealCardTo(player);
			}
		}
	}

	private void dealCardsToDealer() {
		while (dealer.canPlay()) {
			Card card = dealerShoe.askForCard();
			dealer.addCard(card);
		}
	}

}

package academy.mindswap.Card;

public class Player {

	private Hand hand;

	private String name;

	private int bet;

	private double moneyAvailable;

	public Player(String name, double moneyAvailable) {
		this.hand = new Hand();
		this.name = name;
		this.moneyAvailable = moneyAvailable;
	}

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public boolean wantsToPlay() {
		return true; // TODO(ask player)
	}

	public boolean canPlay() {
		// TODO: Verify minimum bet amount (table should define it)
		return hand.canPlay();
	}

	public void askForBet() {
		// TODO: Ask for bet
		bet = 100;
		moneyAvailable -= bet;
	}

	public boolean hasBlackJack() {
		return hand.hasBlackJack();
	}

	public int getScore() {
		return hand.getScore();
	}

	public void getPayment(double betMultiplier) {
		moneyAvailable += bet * betMultiplier;
		resetBet();
	}

	private void resetBet() {
		bet = 0;
	}

	public int getBet() {
		return bet;
	}
}

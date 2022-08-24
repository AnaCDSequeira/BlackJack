package academy.mindswap.game;

/**
 * Class that draws a player extending person
 */
public class Player extends Person {

	private String name;
	private int bet;  // value to bet each round
	private double budget;  // total amount of money player has
	private double valueWon;
	private boolean wantMoreCards;  // can draw more cards
	private boolean outOfGame;  // can't play

	public Player() {
		super();
	}

	public boolean shouldDrawCards() {
		// TODO: Verify minimum bet amount (table should define it)
		return getHand().canPlay();
	}

	public boolean hasBlackJack() {
		return getHand().hasBlackJack();
	}

	/**
	 * Method to calculate the money to win multiplying the type of winning (normal, with blackjack) and resets bet
	 * @param betMultiplier accepts an int which represents the type of winning to multiply by the bet
	 */
	public void setPayment(double betMultiplier) {
		valueWon = bet * betMultiplier;
		budget += valueWon;
		resetBet();
	}

	public double getValueWon() {
		return valueWon;
	}

	private void resetBet() {
		bet = 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public boolean wantMoreCards() {
		return wantMoreCards;
	}

	public void setWantMoreCards(boolean wantMoreCards) {
		this.wantMoreCards = wantMoreCards;
	}

	public void setIsOutOfGame(boolean outOfGame) {
		this.outOfGame = outOfGame;
		wantMoreCards = false;
	}

	public boolean isOutOfGame() {
		return outOfGame;
	}

	@Override
	public String getName() {
		return name;
	}
}

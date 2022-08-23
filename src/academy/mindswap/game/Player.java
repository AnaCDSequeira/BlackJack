package academy.mindswap.game;

public class Player extends Person {

	private String name;
	private int bet;
	private double budget;
	private double valueWon;
	private boolean wantMoreCards;
	private boolean outOfGame;

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

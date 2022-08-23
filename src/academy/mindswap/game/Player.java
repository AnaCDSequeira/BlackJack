package academy.mindswap.game;

public class Player extends Person {

	private String name;
	private int bet;
	private int budget;
	private boolean wantCard;
	private boolean outOfGame;

	public Player() {
		super();
	}

	public boolean canPlay() {
		// TODO: Verify minimum bet amount (table should define it)
		return getHand().canPlay();
	}

	public boolean hasBlackJack() {
		return getHand().hasBlackJack();
	}

	public int getScore() {
		return getHand().getScore();
	}

	public void getPayment(double betMultiplier) {
		budget += bet * betMultiplier;
		resetBet();
	}

	private void resetBet() {
		bet = 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBudget() {
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

	public boolean getWantCard() {
		return wantCard;
	}

	public void setWantCard(boolean wantCard) {
		this.wantCard = wantCard;
	}

	public void setIsOutOfGame(boolean outOfGame) {
		this.outOfGame = outOfGame;
	}

	public boolean isOutOfGame() {
		return outOfGame;
	}
}

package academy.mindswap.game;

public class Player extends Person{

	private String name;

	private int bet;

	private double moneyAvailable;

	public Player(String name, double moneyAvailable) {
		super();
		this.name = name;
		this.moneyAvailable = moneyAvailable;
	}

	public boolean wantMoreCards() {
		return true; // TODO(ask player)
	}

	public boolean canPlay() {
		// TODO: Verify minimum bet amount (table should define it)
		return getHand().canPlay();
	}

	public void askForBet() {
//		bet = Integer.parseInt(sendMessageAndReadAnswer("How much do you want to bet?"));
//		//TODO create enum for chips
//		if (budget < bet) {
//			sendMessageToUser("There's not enough money to bet. You have: " + budget);
//			askForBet();
//		}
//		budget -= bet;
	}

	public boolean hasBlackJack() {
		return getHand().hasBlackJack();
	}

	public int getScore() {
		return getHand().getScore();
	}

	public void getPayment(double betMultiplier) {
		moneyAvailable += bet * betMultiplier;
		resetBet();
	}

	public int getBet() {
		return bet;
	}

	private void resetBet() {
		bet = 0;
	}
}

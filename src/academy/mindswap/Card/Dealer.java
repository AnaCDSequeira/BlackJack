package academy.mindswap.Card;

public class Dealer {

	private static final int MINIMUM_VALUE_TO_DRAW = 16;

	private Hand hand;

	public Dealer(Hand hand) {
		this.hand = hand;
	}

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public boolean canPlay() {
		return hand.getScore() <= MINIMUM_VALUE_TO_DRAW;
	}

	public int getScore() {
		return hand.getScore();
	}
}

package academy.mindswap.Card;

public class Dealer extends Person {

	private static final int MINIMUM_VALUE_TO_DRAW = 16;

	public Dealer() {
		super();
	}

	public boolean canPlay() {
		return getHand().getScore() <= MINIMUM_VALUE_TO_DRAW;
	}

	public int getScore() {
		return getHand().getScore();
	}
}

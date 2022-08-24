package academy.mindswap.game;

/**
 * Superclass containing properties a player must have
 */
public abstract class Person {

	private final Hand hand;


	public Person() {
		this.hand = new Hand();
	}

	public abstract boolean shouldDrawCards();

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public boolean hasBusted() {
		return hand.hasBusted();
	}

	public Hand getHand() {
		return hand;
	}

	public int getScore() {
		return getHand().getScore();
	}

	public abstract String getName();
}

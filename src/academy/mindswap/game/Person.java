package academy.mindswap.game;

public abstract class Person {

	private Hand hand;

	public Person() {
		this.hand = new Hand();
	}

	public abstract boolean canPlay();

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public boolean hasBusted() {
		return hand.hasBusted();
	}

	public Hand getHand() {
		return hand;
	}
}

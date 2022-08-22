package academy.mindswap.game;

public abstract class Person {

	private final Hand hand;

	public Person() {
		this.hand = new Hand();
	}

	public abstract boolean canPlay();

	public void addCard(Card card) {
		hand.addCard(card);
//		sendMessageToUser(card.getValue());
	}

	public boolean hasBusted() {
		return hand.hasBusted();
	}

	public Hand getHand() {
		return hand;
	}
}

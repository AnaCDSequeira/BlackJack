package academy.mindswap.game;

public class Card {

	private final CardValue cardValue;
	private final CardSuit cardSuit;

	public Card(CardValue value, CardSuit suit) {
		cardValue = value;
		cardSuit = suit;
	}

	@Override
	public String toString() {
		return  cardValue + " of " + cardSuit;
	}

	public int getValue() {
		return cardValue.getValue();
	}
}

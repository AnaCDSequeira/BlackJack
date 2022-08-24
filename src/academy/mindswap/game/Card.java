package academy.mindswap.game;

/**
 * Class that produces a card with a value and a suit
 */
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

	public String getValue() {
		return String.valueOf(cardValue.getValue());
	}
}

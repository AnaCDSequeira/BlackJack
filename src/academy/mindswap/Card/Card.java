package academy.mindswap.Card;

public class Card {

	private final CardValue cardValue;
	private final CardSuit cardSuit;

	public Card(CardValue value, CardSuit suit) {
		cardValue = value;
		cardSuit = suit;
	}

	public int getValue() {
	}
	@Override
	public String toString() {
		return "Card{" +
				"cardValue=" + cardValue +
				", cardSuit=" + cardSuit +
				'}';
	}
}

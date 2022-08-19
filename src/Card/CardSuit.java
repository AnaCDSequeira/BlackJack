package Card;

public enum CardSuit {

	DIAMONDS,
	SPADES,
	HEARTS,
	CLUBS;

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}

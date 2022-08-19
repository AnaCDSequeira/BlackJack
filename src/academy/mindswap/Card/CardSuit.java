package academy.mindswap.Card;

public enum CardSuit {

	DIAMONDS,
	SPADES,
	HEARTS,
	CLUBS;

	/*
	public static CardSuit getSuit(int position) {
		return switch (position) {
			case 0 -> DIAMONDS;
			case 1 -> SPADES;
			case 2 -> HEARTS;
			case 3 -> CLUBS;
			default -> null;
		};
	}
	*/
	@Override
	public String toString() {
		return name().toLowerCase();
	}

}

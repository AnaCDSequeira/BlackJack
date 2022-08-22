package academy.mindswap.game;

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

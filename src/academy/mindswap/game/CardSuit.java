package academy.mindswap.game;

/**
 * Enumerator with all cases of cards suit
 */

public enum CardSuit {
	DIAMONDS,
	SPADES,
	HEARTS,
	CLUBS;

	@Override
	public String toString() {
		return name().charAt(0) + name().substring(1).toLowerCase();
	}
}

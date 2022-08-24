package academy.mindswap.game;

/**
 * Enumerator with all cases of cards suit
 */

public enum CardSuit {
    DIAMONDS,
    SPADES,
    HEARTS,
    CLUBS;

    /**
     * Transform the enum value in to a String and after the 2nd letter to lower case.
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}

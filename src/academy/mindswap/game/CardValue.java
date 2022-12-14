package academy.mindswap.game;

/**
 * Enumerator with all cases of cards value
 */
public enum CardValue {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private final int value;

    /**
     * Constructor
     *
     * @param value
     */
    CardValue(int value) {
        this.value = value;
    }

    /**
     * Transform the enum value in to a String and after the 2nd letter to lower case.
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    public int getValue() {
        return value;
    }
}

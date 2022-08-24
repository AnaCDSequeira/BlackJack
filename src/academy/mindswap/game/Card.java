package academy.mindswap.game;

/**
 * Class that produces a card with a value and a suit
 */
public class Card {

    private final CardValue cardValue;
    private final CardSuit cardSuit;

    /**
     * Constructor
     *
     * @param value
     * @param suit
     */

    public Card(CardValue value, CardSuit suit) {
        cardValue = value;
        cardSuit = suit;
    }

    /**
     * Adds together the card value and the suit
     */

    @Override
    public String toString() {
        return cardValue + " of " + cardSuit;
    }

    public String getValue() {
        return String.valueOf(cardValue.getValue());
    }
}

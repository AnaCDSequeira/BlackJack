package academy.mindswap.game;

import java.util.ArrayList;

/**
 * Class representing the hand of a player, which contains cards
 */
public class Hand {

    public static final int FIRST_CARD_POSITION = 0;
    private static final int INITIAL_SCORE = 0;
    private static final int BLACKJACK_SCORE = 21;
    private final ArrayList<Card> cards;
    //TODO getter to pass to the printCards class
    private int score;

    /**
     * constructor that instantiates a new list and set property score to be the INITIAL_SCORE
     */
    public Hand() {
        cards = new ArrayList<>();
        score = INITIAL_SCORE;
    }

    /**
     * Method that adds a card to the player hand (list) and updates his score
     *
     * @param card receives a card to add to the hand (list)
     */
    public void addCard(Card card) {
        cards.add(card);
        updateScore(Integer.parseInt(card.getValue()));
    }

    /**
     * Method to update the user score adding the value of the card into his score
     *
     * @param value the value of the card received
     */
    private void updateScore(int value) {
        score += value;
    }

    /**
     * Method to check if player is able to play (not having blackJack or be bust)
     *
     * @return true or false depending on condition above
     */
    public boolean canPlay() {
        return score < BLACKJACK_SCORE;
    }

    /**
     * Method to check if player is unable to play (having blackjack)
     *
     * @return true or false depending on condition above
     */
    public boolean hasBlackJack() {
        return score == BLACKJACK_SCORE;
    }

    /**
     * Method to check if player is unable to play (be bust)
     *
     * @return true or false depending on condition above
     */
    public boolean hasBusted() {
        return score > BLACKJACK_SCORE;
    }

    public Card showFirstCard() {
        return cards.get(FIRST_CARD_POSITION);
    }

    /**
     * toString Builder
     *
     * @return the builder
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            builder.append(i + 1);
            builder.append(". ");
            builder.append(cards.get(i));
            builder.append(" - ");
            builder.append(cards.get(i).getValue());
            builder.append(" points");
            builder.append("\n");
        }
        return builder.toString();
    }

    public int getScore() {
        return score;
    }
}

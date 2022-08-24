package academy.mindswap.game;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the machine that throws random cards to fill players hands
 */
public class DealerShoe {

	private static final int NUMBER_OF_DECKS = 6;

	private final ArrayList<Card> shoe = new ArrayList<>();

	/**
	 * Method to fill the list shoe with cards and shuffle them
	 */
	public void populateShoe() {
		for (int i = 0; i < NUMBER_OF_DECKS; i++) {
			Deck deck = new Deck();
			shoe.addAll(deck.getCards());
		}
		shuffleCards();
	}

	/**
	 * Method to shuffle the cards from the arraylist shoe
	 */

	private void shuffleCards() {
		Collections.shuffle(shoe);
	}

	/**
	 * Method to get a card returned by other method (drawCardFromTop())
	 *
	 * @return the last card from the shuffled shoe
	 */

	public Card askForCard() {
		return drawCardFromTop();
	}

	/**
	 * Method to get the last card from the list shoe, remove it and return it
	 *
	 * @return the last card from the shuffled shoe
	 */
	private Card drawCardFromTop() {
		int lastCardPosition = getLastCardPosition();
		Card card = shoe.get(lastCardPosition);
		removeCard(card);
		return card;
	}

	/**
	 * Method removes the card from the shoe after is added to the array
	 *
	 * @param card
	 */

	private void removeCard(Card card) {
		shoe.remove(card);
	}

	/**
	 * Method gets the size of the shoe
	 *
	 * @return
	 */

	private int getLastCardPosition() {
		return shoe.size() - 1;
	}
}

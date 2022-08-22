package academy.mindswap.game;

import java.util.ArrayList;
import java.util.Collections;

public class DealerShoe {

	private static final int NUMBER_OF_DECKS = 6;

	private final ArrayList<Card> shoe = new ArrayList<>();

	public void populateShoe() {
		for (int i = 0; i < NUMBER_OF_DECKS; i++) {
			Deck deck = new Deck();
			shoe.addAll(deck.getCards());
		}
		shuffleCards();
	}

	public Card askForCard() {
		return drawCardFromTop();
	}

	private void shuffleCards() {
		Collections.shuffle(shoe);
	}

	private Card drawCardFromTop() {
		int lastCardPosition = getLastCardPosition();
		Card card = shoe.get(lastCardPosition);
		removeCard(card);
		return card;
	}

	private void removeCard(Card card) {
		shoe.remove(card);
	}

	private int getLastCardPosition() {
		return shoe.size() - 1;
	}
}

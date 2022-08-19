package academy.mindswap.Card;

import java.util.ArrayList;
import java.util.Collections;

public class DealerShoe {

	private static final int NUMBER_OF_DECKS = 6;

	private final ArrayList<Card> shoe = new ArrayList<>();

	public ArrayList<Card> populateShoe() {
		for (int i = 0; i < NUMBER_OF_DECKS; i++) {
			Deck deck = new Deck();
			shoe.addAll(deck.getCards());
		}
		shuffleCards();
		return shoe;
	}

	private void shuffleCards() {
		Collections.shuffle(shoe);
	}

	private Card lastCard() {
		int lastCardPosition = getLastCardPosition();
		return shoe.get(lastCardPosition);
	}

	private int getLastCardPosition() {
		return shoe.size() - 1;
	}
}

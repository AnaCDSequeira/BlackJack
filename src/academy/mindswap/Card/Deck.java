package academy.mindswap.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck {

	private ArrayList<Card> deck;

	public ArrayList<Card> populate() {
		deck = new ArrayList<>();
		for (int i = 0; i < CardSuit.values().length; i++) {
			for (int j = 0; j < CardValue.values().length; j++) {
				deck.add(new Card(CardValue.values()[j], CardSuit.values()[i]));
			}
		}
		return deck;
	}

	public ArrayList<Card> getCards() {
		return deck;
	}
}

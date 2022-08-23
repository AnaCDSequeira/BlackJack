package academy.mindswap.game;

import java.util.ArrayList;

/**
 * Class with container of a list of cards
 */
public class Deck {

	private ArrayList<Card> cards;

	/**
	 * constructor that calls a method to create a deck with 52 cards
	 */
	public Deck() {
		this.cards = populate();
	}

	/**
	 * Method that runs through all cards value and suit and inserts them in a list
	 * @return the list of cards (deck)
	 */
	public ArrayList<Card> populate() {
		cards = new ArrayList<>();
		for (int i = 0; i < CardSuit.values().length; i++) {
			for (int j = 0; j < CardValue.values().length; j++) {
				cards.add(new Card(CardValue.values()[j], CardSuit.values()[i]));
			}
		}
		return cards;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
}

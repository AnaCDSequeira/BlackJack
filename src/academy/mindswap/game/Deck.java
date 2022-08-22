package academy.mindswap.game;

import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> cards;

	public Deck() {
		this.cards = populate();
	}

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

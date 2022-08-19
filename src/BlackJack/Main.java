package BlackJack;

import BlackJack.Deck;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");


		Deck deck = new Deck();
		deck.setNumberOfDecks(2);
		deck.storeCards();
		deck.printCards();

	}
}

import BlackJack.Card;
import BlackJack.Deck;


public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		Card card = new Card();
		PrintCards print = new PrintCards();


		card.deckOf52();
		System.out.println(card.deckOf52());


		card.first2Cards();
		System.out.println(card.getCardValue());

		print.cardsToPrint();



		/*
		Deck deck = new Deck();
		deck.setNumberOfDecks(2);
		deck.storeCards();
		deck.printCards();

*/
	}
}

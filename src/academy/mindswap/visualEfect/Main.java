package academy.mindswap.visualEfect;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello world!");
        Card card = new Card();
        Game game = new Game();
        PrintCards print = new PrintCards();


        card.deckOf52();
        System.out.println(card.deckOf52());


        card.first2Cards();
        System.out.println(card.getCardValue());


        print.rounds();
        System.out.println(print.val.length);








		/*
		Deck deck = new Deck();
		deck.setNumberOfDecks(2);
		deck.storeCards();
		deck.printCards();

*/
    }
}

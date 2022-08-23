package academy.mindswap.visualEfect;

import academy.mindswap.server.Messages;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello world!");
        Card card = new Card();
        PrintCards print = new PrintCards();
        System.out.println(Messages.WELCOME_PLAYER);

        System.out.println(Messages.CHOOSE_USERNAME);
        System.out.println("Diogo");
        System.out.println(Messages.WELCOME_USERNAME);
        System.out.println(Messages.SPENT_AMOUNT);
        System.out.println("5000");
        System.out.println(Messages.AMOUNT_STARTED);
        System.out.println(Mensagens.CHIPS);
        System.out.println(Messages.SET_BET);
        System.out.println("50");

        print.dealerHiddenCard();
        print.test();
        System.out.println("You want to 'Hit' or 'Pass'?");
        System.out.println("Hit");
        print.test1();
        System.out.println(Mensagens.HELP1);
        //print.rounds();
        //System.out.println(print.val.length);








		/*
		Deck deck = new Deck();
		deck.setNumberOfDecks(2);
		deck.storeCards();
		deck.printCards();

*/
    }
}

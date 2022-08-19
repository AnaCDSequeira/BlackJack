package BlackJack;

import java.util.ArrayList;
import java.util.Collections;

public class Card {

    private static String[] cardArray = {"Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
    private static String[] suitArray = {"Clubs","Diamonds", "Spades", "Hearts"};
    private static ArrayList<String> oneDeck;

    public static ArrayList<String> deckOf52() {
        oneDeck = new ArrayList<>();
            for (int i = 0; i < suitArray.length; i++) {
                for (int j = 0; j < cardArray.length; j++) {
                    oneDeck.add(cardArray[j] + " of " + suitArray[i]);
                }
            }
        return oneDeck;
    }

    public void printCards() {
        for (String v : oneDeck) {
            System.out.println(v);
        }
    }

    public static ArrayList<String> getOneDeck() {
        return oneDeck;
    }
}
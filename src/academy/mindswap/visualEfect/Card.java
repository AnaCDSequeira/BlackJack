package academy.mindswap.visualEfect;

import java.util.ArrayList;

public class Card {

    private ArrayList<Integer> cardValue = new ArrayList<>();
    private ArrayList<String> suits = new ArrayList<>();


    private static String[] cardArray = {"Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
    private static String[] suitArray = {"Clubs","Diamonds", "Spades", "Hearts"};
    private static ArrayList<String> oneDeck = new ArrayList<>();


    public ArrayList<String> deckOf52() {
        this.suits.add(0, "Diamonds");
        this.suits.add(1, "Spades");
        oneDeck = new ArrayList<>();
            for (int i = 0; i < suitArray.length; i++) {
                for (int j = 0; j < cardArray.length; j++) {
                    oneDeck.add(cardArray[j] + " of " + suitArray[i]);

                }
            }
        return oneDeck ;
    }

    public void first2Cards() {

        for (int i = 0; i < 5; i++) {
            String zeroCard = oneDeck.get(i);
            System.out.println(zeroCard);
            //oneDeck.remove(0);
            if (zeroCard.contains("Ace")) {
                cardValue.add(i,1);
            } else if (zeroCard.contains("Two")) {
                cardValue.add(i,2);
            } else if (zeroCard.contains("Three")) {
                cardValue.add(i,3);
            } else if (zeroCard.contains("Four")) {
                cardValue.add(i,4);
            } else if (zeroCard.contains("Five")) {
                cardValue.add(i,5);
            } else if (zeroCard.contains("Six")) {
                cardValue.add(i,6);
            } else if (zeroCard.contains("Seven")) {
                cardValue.add(i,7);
            } else if (zeroCard.contains("Eight")) {
                cardValue.add(i,8);
            } else if (zeroCard.contains("Nine")) {
                cardValue.add(i,9);
            } else if (zeroCard.contains("Ten") ||zeroCard.contains("Jack") ||zeroCard.contains("Queen")|| zeroCard.contains("King")){
                cardValue.add(i,10);
            }

        }
    }

    public static ArrayList<String> getOneDeck() {
        return oneDeck;
    }

    public ArrayList<Integer> getCardValue() {
        return cardValue;
    }

    public ArrayList<String> getSuits() {
        return suits;
    }
}
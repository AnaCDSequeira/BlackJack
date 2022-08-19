package BlackJack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private String[] cardArray = {"Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
    private String[] suitArray = {"Clubs","Diamonds", "Spades", "Hearts"};
    private ArrayList<String> result;
    private int numberOfDecks;



    public ArrayList<String> storeCards() {
        int counter = 0;
        result = new ArrayList<>();
        while (counter != numberOfDecks) {

            for (int i = 0; i < suitArray.length; i++) {
                for (int j = 0; j < cardArray.length; j++) {
                    result.add(cardArray[j] + " of " + suitArray[i]);
                }
            }
            counter++;
        }
        Collections.shuffle(result);
        return result;
    }

    public void printCards() {
        for (String v : result) {
            System.out.println(v);
        }
    }



    public void setNumberOfDecks(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
    }
}

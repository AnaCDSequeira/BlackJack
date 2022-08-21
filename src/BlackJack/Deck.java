package BlackJack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<String> shoe;
    private int numberOfDecks;



    /*public ArrayList<String> storeCards() {
        shoe = new ArrayList<String>(Card.deckOf52());
        int counter = 1;
        while (counter != numberOfDecks) {
            for (String v: Card.getOneDeck()){
                shoe.add(v);
            }
            counter++;
        }
        Collections.shuffle(shoe);
        return shoe;
    }
    public void printCards() {
        for (String v : shoe) {
            System.out.println(v);
        }
    }




    public void setNumberOfDecks(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
    }
    */

}

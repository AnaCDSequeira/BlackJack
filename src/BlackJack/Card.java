package BlackJack;

public class Card {

    private Suit mySuit;
    private int myNumber;
    private String[] cardArray = {"Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};

    public Card(Suit aSuit, int aNumber){
        this.mySuit= aSuit;
        this.myNumber= aNumber;
    }

    public int getMyNumber(){
        return myNumber;
    }



    @Override
    public String toString(){

        return myNumber + " of " + mySuit.toString();
    }
    public enum Suit {

        Clubs,
        Diamonds,
        Spades,
        Hearts
    }

    public enum Value{
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King,
        Ace
    }
}

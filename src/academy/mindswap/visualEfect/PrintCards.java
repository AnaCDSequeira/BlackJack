package academy.mindswap.visualEfect;

import academy.mindswap.game.CardValue;
import academy.mindswap.game.Hand;

import java.util.ArrayList;
import java.util.Arrays;


public class PrintCards {

    ArrayList<String> printCardsInConsole = new ArrayList<>();
    Hand hand;
    private int[] newValue = new int[6];
    private char[] suit = {'♠','♦','♥','♣','♣'};
    String [] val = {"7","K","3"};
    String [] val1 = {"9","5"};

    public void test(){
        this.printCardsInConsole.clear();
        this.printCardsInConsole.add(" ___________   ___________");
        this.printCardsInConsole.add("| " + val[0] + "         | | " + val[1] + "         |");
        this.printCardsInConsole.add("|           | |           |");
        this.printCardsInConsole.add("|  " + suit[0] + "   | | " + suit[1] + "  |");
        this.printCardsInConsole.add("|           | |           |");
        this.printCardsInConsole.add("|        " + val[0] + "  | |         " + val[1] + " |");
        this.printCardsInConsole.add("|___________| |___________|");
        printCards();
        System.out.println("You have: Seven of " + suit[0] + " and King of " + suit[1]);
        System.out.println("(Your total is 17)");
    }

    public void test1(){
        this.printCardsInConsole.clear();
        this.printCardsInConsole.add(" ___________   ___________  ___________ ");
        this.printCardsInConsole.add("| " + val[0] + "         | | " + val[1] + "         || " + val[2] + "         |");
        this.printCardsInConsole.add("|           | |           ||           |");
        this.printCardsInConsole.add("|  " + suit[0] + "   | | " + suit[1] + "  ||  " + suit[2] + "   | ");
        this.printCardsInConsole.add("|           | |           ||           |");
        this.printCardsInConsole.add("|        " + val[0] + "  | |         " + val[1] + " ||        " + val[2] + "  |");
        this.printCardsInConsole.add("|___________| |___________||___________|");
        printCards();
        System.out.println("You have: Seven of " + suit[0] + ", King of " + suit[1]+" and Three of " + suit[2]);
        System.out.println("(Your total is 20)");
    }

    /**
     * Checks the length of the array of cards on the player hand and adds the cards to the Arraylist
     * Prints the number of Cards depending on the length of the array
     * @author Carlos Pinto
     */
      public void rounds() {
            if (val.length == 2) {
                this.printCardsInConsole.add(" ___________   ___________");
                this.printCardsInConsole.add("| " + val[0] + "         | | " + val[1] + "         |");
                this.printCardsInConsole.add("|           | |           |");
                this.printCardsInConsole.add("|  " + suit[0] + "   | | " + suit[1] + "  |");
                this.printCardsInConsole.add("|           | |           |");
                this.printCardsInConsole.add("|        " + val[0] + "  | |         " + val[1] + " |");
                this.printCardsInConsole.add("|___________| |___________|");
                printCards();
                System.out.println("You have: " + val[0] + " of " + suit[0] + " and " + val[1] + " of " + suit[1]);
                System.out.println("(Your total is " + Arrays.stream(newValue).sum());
                return;
            }
                if (val.length == 3) {
                    this.printCardsInConsole.add(" ___________   ___________  ___________ ");
                    this.printCardsInConsole.add("| " + val[0] + "         | | " + val[1] + "         || " + val[2] + "         |");
                    this.printCardsInConsole.add("|           | |           ||           |");
                    this.printCardsInConsole.add("|  " + suit[0] + "   | | " + suit[1] + "  ||  " + suit[2] + "   | ");
                    this.printCardsInConsole.add("|           | |           ||           |");
                    this.printCardsInConsole.add("|        " + val[0] + "  | |         " + val[1] + " ||        " + val[2] + "  |");
                    this.printCardsInConsole.add("|___________| |___________||___________|");
                    printCards();
                    System.out.println("You have: " + val[0] + " of " + suit[0] + ", " + val[1] + " of " + suit[1] + " and " + val[2] + " of " + suit[2]);
                    System.out.println("(Your total is " + Arrays.stream(newValue).sum());
                    return;
                }
                if (val.length == 4) {
                    this.printCardsInConsole.add(" ___________   ___________  ___________   ___________ ");
                    this.printCardsInConsole.add("| " + val[0] + "         | | " + val[1] + "         || " + val[2] + "         | | " + val[3] + "         |");
                    this.printCardsInConsole.add("|           | |           ||           | |           |");
                    this.printCardsInConsole.add("|  " + suit[0] + "   | | " + suit[1] + "  ||  " + suit[2] + "   | | " + suit[3] + "  |");
                    this.printCardsInConsole.add("|           | |           ||           | |           |");
                    this.printCardsInConsole.add("|        " + val[0] + "  | |         " + val[1] + " ||        " + val[2] + "  | |         " + val[3] + " |");
                    this.printCardsInConsole.add("|___________| |___________||___________| |___________|");
                    printCards();
                    System.out.println("You have: " + val[0] + " of " + suit[0] + ", " + val[1] + " of " + suit[1] + ", "
                            + val[2] + " of " + suit[2] + " and " + val[3] + " of " + suit[3]);
                    System.out.println("(Your total is " + Arrays.stream(newValue).sum());
                    return;

                }
                if (val.length == 5) {
                    this.printCardsInConsole.add(" ___________   ___________  ___________   ___________  ___________");
                    this.printCardsInConsole.add("| " + val[0] + "         | | " + val[1] + "         || " + val[2] + "         | | " + val[3] + "         || " + val[4] + "         |");
                    this.printCardsInConsole.add("|           | |           ||           | |           ||           |");
                    this.printCardsInConsole.add("|  " + suit[0] + "   | | " + suit[1] + "  ||  " + suit[2] + "   | | " + suit[3] + "  ||  " + suit[4] + "   |");
                    this.printCardsInConsole.add("|           | |           ||           | |           ||           |");
                    this.printCardsInConsole.add("|        " + val[0] + "  | |         " + val[1] + " ||        " + val[2] + "  | |         " + val[3] + " ||        " + val[4] + "  |");
                    this.printCardsInConsole.add("|___________| |___________||___________| |___________||___________|");
                    printCards();
                    System.out.println("You have: " + val[0] + " of " + suit[0] + ", " + val[1] + " of " + suit[1] + ", "
                            + val[2] + " of " + suit[2] + " and " + val[3] + " of " + suit[3] + ", " + val[4] + " of " + suit[4]);
                    System.out.println("(Your total is " + Arrays.stream(newValue).sum());
                    return;

         }
        }
    /**
     *
     * Prints the first 2 cards from the dealer, one is hidden
     * @author Carlos Pinto
     */
      public void dealerHiddenCard(){
        this.printCardsInConsole.add(" ___________   ___________");
        this.printCardsInConsole.add("| " + val1[0] + "         | |###########|");
        this.printCardsInConsole.add("|           | |###########|");
        this.printCardsInConsole.add("| " + suit[1] + "  | |#####BJ####|");
        this.printCardsInConsole.add("|           | |###########|");
        this.printCardsInConsole.add("|        " + val1[0] + "  | |###########|");
        this.printCardsInConsole.add("|___________| |###########|");
        printCards();
          System.out.println("Dealer has: Nine of " + suit[1] + " and a hidden card");
          System.out.println("(His score is 10");
    }


/*public void checkCardsInHand(Hand hand){
        String currentCard = "";
    for (int i = 0; i < hand.getCards().size() ; i++) {
        currentCard = hand.getCards().get(i).toString();
        if(currentCard.contains("ace")){
            val[i] ="A";
            break;
        }
        if(currentCard.contains("two")){
            val[i] ="2";
            break;
        }
        if(currentCard.contains("three")){
            val[i] ="3";
            break;
        }
        if(currentCard.contains("four")){
            val[i] ="4";
            break;
        }
        if(currentCard.contains("five")){
            val[i] ="5";
            break;
        }
        if(currentCard.contains("six")){
            val[i] ="6";
            break;
        }
        if(currentCard.contains("seven")){
            val[i] ="7";
            break;
        }
        if(currentCard.contains("eight")){
            val[i] ="8";
            break;
        }
        if(currentCard.contains("nine")){
            val[i] ="9";
            break;
        }
        if(currentCard.contains("ten")){
            val[i] ="10";
            break;
        }
        if(currentCard.contains("jack")){
            val[i] ="J";
            break;
        }
        if(currentCard.contains("queen")){
            val[i] ="Q";
            break;
        }
        if(currentCard.contains("king")){
            val[i] ="K";
            break;
        }
    }
}*/

    /**
     * Print the arraylist to form a card in ASCII art
     * @author Carlos Pinto
     */
    public void printCards(){
        for (String v : printCardsInConsole) {
            System.out.println(v);
        }
    }
}

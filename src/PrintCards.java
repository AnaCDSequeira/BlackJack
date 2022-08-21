import BlackJack.Card;

import java.util.ArrayList;
import java.util.Arrays;


public class PrintCards {

    ArrayList<String> printCardsInConsole = new ArrayList<>();
    Card card = new Card();

    public PrintCards(){

    }

    public boolean cardsToPrint(){

        for (int i = 0; i < card.getCardValue().size(); i++) {
            if(card.getCardValue().contains(2)  ||card.getCardValue().contains(3)){
                facesCards(card.getCardValue().get(0), card.getCardValue().get(1), card.getSuits().get(0), card.getSuits().get(1));
            } else {
                numberCards(card.getCardValue().get(0), card.getCardValue().get(1), card.getSuits().get(0), card.getSuits().get(1));
            }
            return false;
        }
        return true;
    }

    public void facesCards(int value1, int value2, String suit1, String suit2){
        int sum = value1+value2;

        this.printCardsInConsole.add(" ___________   ___________");
        this.printCardsInConsole.add("| "+ value1 +"         | | "+ value2 +"         |");
        this.printCardsInConsole.add("|           | |           |");
        this.printCardsInConsole.add("|  "+ suit1 +"   | | "+ suit2 +"  |");
        this.printCardsInConsole.add("|           | |           |");
        this.printCardsInConsole.add("|        "+ value1 +"  | |         "+ value2 +" |");
        this.printCardsInConsole.add("|___________| |___________|");

        for (String v : printCardsInConsole) {
            System.out.println(v);
        }
        System.out.println("You have: "+value1+" of "+suit1+" and "+value2+" of "+suit2);
        System.out.println("(Your total is "+sum+")");
    }
    public void numberCards(int value1, int value2, String suit1, String suit2){
        int sum = value1+value2;
        this.printCardsInConsole.add(" ___________   ___________");
        this.printCardsInConsole.add("| "+ value1 +"         | | "+ value2 +"         |");
        this.printCardsInConsole.add("|           | |           |");
        this.printCardsInConsole.add("|  "+ suit1 +"   | | "+ suit2 +"  |");
        this.printCardsInConsole.add("|           | |           |");
        this.printCardsInConsole.add("|        "+ value1 +"  | |         "+ value2 +" |");
        this.printCardsInConsole.add("|___________| |___________|");

        for (String v : printCardsInConsole) {
            System.out.println(v);
        }
        System.out.println("You have: "+value1+" of "+suit1+" and "+value2+" of "+suit2);
        System.out.println("(Your total is "+sum+")");
    }
}

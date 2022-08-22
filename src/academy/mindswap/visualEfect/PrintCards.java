package academy.mindswap.visualEfect;

import java.util.ArrayList;
import java.util.Arrays;


public class PrintCards {
    ArrayList<Integer> value;
    ArrayList<String> printCardsInConsole = new ArrayList<>();
    Card card = new Card();
    private int[] newValue = new int[6];
    private String[] suit = {"Spades","Diamonds","Hearts","Clubs","Clubs"};

    String [] val ={"A","4","2","K"};

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


    public void printCards(){
        for (String v : printCardsInConsole) {
            System.out.println(v);
        }
    }
}

package BlackJack.game.commands;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PrintCard {

    static File file ;
    private static String AS = "Ace of Spades";

    private static String[] AA;

    public static void main(String[] args) throws IOException, InterruptedException {

        try {
            printSpecificCard();
            justWhile();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static File printSpecificCard(){
        AA = new String[]{"Ace of Clubs", "Three of Diamonds", "Seven of Hearts", "King of Spades"};
        String card = "";
        for (int i = 0; i < AA.length; i++) {
                card = AA[i];
            if(card.contains("Clubs")) {
                switch (card) {
                    case "Ace of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Ace");
                        break;
                    case "Two of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Two");
                        break;
                    case "Three of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Three");
                        break;
                    case "Four of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Four");
                        break;
                    case "Five of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Five");
                        break;
                    case "Six of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Six");
                        break;
                    case "Seven of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Seven");
                        break;
                    case "Eight of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Eight");
                        break;
                    case "Nine of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Nine");
                        break;
                    case "Ten of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Ten");
                        break;
                    case "Jack of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Jack");
                        break;
                    case "Queen of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\Queen");
                        break;
                    case "King of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Clubs\\King");
                        break;
                }
                return file;
            }
            if(card.contains("Diamonds")) {
                switch (card) {
                    case "Ace of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Ace");
                        break;
                    case "Two of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Two");
                        break;
                    case "Three of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Three");
                        break;
                    case "Four of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Four");
                        break;
                    case "Five of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Five");
                        break;
                    case "Six of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Six");
                        break;
                    case "Seven of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Seven");
                        break;
                    case "Eight of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Eight");
                        break;
                    case "Nine of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Nine");
                        break;
                    case "Ten of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Ten");
                        break;
                    case "Jack of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Jack");
                        break;
                    case "Queen of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\Queen");
                        break;
                    case "King of Diamonds":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Diamonds\\King");
                        break;
                }
                return file;
            }
            if(card.contains("Hearts")) {
                switch (card) {
                    case "Ace of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Ace");
                        break;
                    case "Two of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Two");
                        break;
                    case "Three of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Three");
                        break;
                    case "Four of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Four");
                        break;
                    case "Five of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Five");
                        break;
                    case "Six of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Six");
                        break;
                    case "Seven of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Seven");
                        break;
                    case "Eight of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Eight");
                        break;
                    case "Nine of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Nine");
                        break;
                    case "Ten of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Ten");
                        break;
                    case "Jack of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Jack");
                        break;
                    case "Queen of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\Queen");
                        break;
                    case "King of Hearts":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Hearts\\King");
                        break;
                }
                return file;
            }
            if(card.contains("Spades")) {
                switch (card) {
                    case "Ace of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Ace");
                        break;
                    case "Two of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Two");
                        break;
                    case "Three of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Three");
                        break;
                    case "Four of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Four");
                        break;
                    case "Five of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Five");
                        break;
                    case "Six of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Six");
                        break;
                    case "Seven of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Seven");
                        break;
                    case "Eight of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Eight");
                        break;
                    case "Nine of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Nine");
                        break;
                    case "Ten of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Ten");
                        break;
                    case "Jack of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Jack");
                        break;
                    case "Queen of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\Queen");
                        break;
                    case "King of Spades":
                        file = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Spades\\King");
                        break;

                }
                return file;
            }
        }
        return file;
    }


    public static boolean justWhile() throws IOException, InterruptedException {
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                System.out.println(line);
                Thread.sleep(350);
            }

        return false;
    }


}

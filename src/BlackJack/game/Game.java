package BlackJack.game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Game {

    public static  final String yellow = "\u001B[33m";
    public static  final String green= "\u001B[32m";
    public static File chips = new File("C:\\Users\\Mind Swap\\Desktop\\Mindera\\BlackJackgroup\\ASCII_Cards\\Chips");



    public static boolean justWhile() throws IOException, InterruptedException {
        Scanner reader = new Scanner(chips);
        while(reader.hasNextLine()){
            String line = reader.nextLine();
            System.out.println(green+ line);
            System.out.println(yellow+ line);
            Thread.sleep(350);
        }

        return false;
    }
}

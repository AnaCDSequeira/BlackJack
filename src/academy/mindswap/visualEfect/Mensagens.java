package academy.mindswap.visualEfect;

public class Mensagens {



    public static String black = """
 .-----------.  .------------. .--------------. .--------------. .--------------. .-------------.  .--------------. .--------------. .--------------.
|  ______    |  |  _____     | |      __      | |     ______   | |  ___  ____   | |     _____   |  |      __      | |     ______   | |  ___  ____   |
| |_   _ \\   |  | |_   _|    | |     /  \\     | |   .' ___  |  | | |_  ||_  _|  | |    |_   _|  |  |     /  \\     | |   .' ___  |  | | |_  ||_  _|  |
|   | |_) |  |  |   | |      | |    / /\\ \\    | |  / .'   \\_|  | |   | |_/ /    | |      | |    |  |    / /\\ \\    | |  / .'   \\_|  | |   | |_/ /    |
|   |  __'.  |  |   | |   _  | |   / ____ \\   | |  | |         | |   |  __'.    | |   _  | |    |  |   / ____ \\   | |  | |         | |   |  __'.    |
|  _| |__) | |  |  _| |__/ | | | _/ /    \\ \\_ | |  \\ `.___.'\\  | |  _| |  \\ \\_  | |  | |_' |    |  | _/ /    \\ \\_ | |  \\ `.___.'\\  | |  _| |  \\ \\_  |
| |_______/  |  | |________| | ||____|  |____|| |   `._____.'  | | |____||____| | |  `.___.'    |  ||____|  |____|| |   `._____.'  | | |____||____| |
'------------'  '------------' '--------------' '--------------' '--------------' '-------------'  '--------------' '--------------' '--------------'""";

    public final static String CHIPS = """
                \u001B[32m    ,######.    \u001B[33m    ,######.    \u001B[34m   ,######.    \u001B[31m   ,######. \u001B[36m      ,######.    \u001B[35m  ,######.
                \u001B[32m  ,#        #.  \u001B[33m  ,#        #.  \u001B[34m ,#        #.  \u001B[31m ,#        #.  \u001B[36m ,#        #. \u001B[35m ,#        #.
                \u001B[32m #    \u001B[0m 10 \u001B[32m    # \u001B[33m #    \u001B[0m 20   \u001B[33m  #\u001B[34m #   \u001B[0m  50  \u001B[34m   # \u001B[31m#  \u001B[0m  100   \u001B[31m  #\u001B[36m #  \u001B[0m  200   \u001B[36m # \u001B[35m#  \u001B[0m  500   \u001B[35m  # 
                \u001B[32m  `#        #'  \u001B[33m  `#        #' \u001B[34m  `#        #'  \u001B[31m `#        #' \u001B[36m  `#        #' \u001B[35m `#        #'
                \u001B[32m    `######'    \u001B[33m    `######'   \u001B[34m    `######'    \u001B[31m   `######'    \u001B[36m   `######'    \u001B[35m  `######'\u001B[0m
 
               """;
    public static final String HELP = """
                                        BLACKJACK RULES:
                                        
            -Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.
            -Cards are equal to their value with face cards being 10 and an Ace being 1 or 11.
            -The players cards are added up for their total.
            -Players “Hit” to gain another card from the deck. Players “Stay” to keep their current card total.
            -Dealer “Hits” until they equal or exceed 17.
            -The goal is to have a higher card total than the dealer without going over 21.
            -If the player total equals the dealer total, it is a “Push” and the hand ends.
            -Players win their bet if they beat the dealer. Players win 1.5x their bet if they get “Blackjack” which is 21.
            """;

    public static final String HELP1 = """
                                ______________________________________
                    _.---------|.--.        --- RULES ---             |
                 .-'  `       .'/  ``                                 |
              .-'           .' |    /| - Place your bets              |
           .-'         |   /   `.__//  -Each player is dealt 2 cards  |
        .-'           _.--/        /-Pick 'Hit' and you get a new card|
       |        _  .-'   /        / -Pick 'Stand' your score stays    |
       |     ._  \\      /      ` /      and is the dealer's turn      |
       |        ` .    /     `  /                                     |
       |         \\ \\'/         / -If you don't burst and your hand is |
       |        - \\ /         /|   better then the dealer's,          |
       |        '  .'        / |            you Win!                  |
       |                    |  |______________________________________|
       |                    |.'
       |                   /
       )                 /|
    .A/`-.              / |
   AMMMA. `-._         / /
  AMMMMMMMMA. `-.     / /
 AMMMMMMMMMMMMA. `.    /
AMMMMMMMMMMMMMMMMA.`. /""";
}

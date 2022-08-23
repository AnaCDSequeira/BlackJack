package academy.mindswap.server;

public class Messages {

    public static final String OPEN_SERVER = "BlackJack is open and accepting players";
    public static final String PLAYER_CONNECTED = "New player arrived, waiting for other players";
    public static final String WELCOME_PLAYER = "" +
            "                ██████╗ ██╗      █████╗  ██████╗██╗  ██╗ ██╗     ██╗ █████╗  ██████╗██╗  ██╗\n" +
            "                ██╔══██╗██║     ██╔══██╗██╔════╝██║ ██╔╝██╔╝     ██║██╔══██╗██╔════╝██║ ██╔╝\n" +
            "                ██████╔╝██║     ███████║██║     █████╔╝██╔╝      ██║███████║██║     █████╔╝\n" +
            "                ██╔══██╗██║     ██╔══██║██║     ██╔═██╗╚██╗ ██   ██║██╔══██║██║     ██╔═██╗\n" +
            "                ██████╔╝███████╗██║  ██║╚██████╗██║  ██╗╚██╗╚█████╔╝██║  ██║╚██████╗██║  ██╗\n" +
            "                ╚═════╝ ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝ ╚═╝ ╚════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝";
    public static final String CHOOSE_USERNAME = "Please choose your username for the game...";
    public static final String WELCOME_USERNAME = "Welcome, %s!\n";
    public static final String SPENT_AMOUNT = "And how much will you spend? Minimum is 5 euros.";
    public static final String AMOUNT_STARTED = "\nNice, you'll start with %d euros";
    public static final String ERROR_MESSAGE = "The Casino is closed now";
    public static final String SHOW_SCORE = "Your actual score is: %d\n";
    public static final String SHOW_DEALER_FIRST_CARD = "First dealer card is:\n%s - %s points\n";
    public static final String GAME_OVER = "You just busted! The game is over for you.";
    public static final String BLACKJACK = "You got a Blackjack! Congratulations!";
    public static final String BET_AMOUNT = "\nHow much do you want to bet?";
    public static final String NOT_ENOUGH_BUDGET= "There's not enough money to bet. You have: %d";
    public final static String CHIPS = """
                \u001B[32m    ,######.    \u001B[33m    ,######.    \u001B[34m   ,######.    \u001B[31m   ,######. \u001B[36m      ,######.    \u001B[35m  ,######.
                \u001B[32m  ,#        #.  \u001B[33m  ,#        #.  \u001B[34m ,#        #.  \u001B[31m ,#        #.  \u001B[36m ,#        #. \u001B[35m ,#        #.
                \u001B[32m #    \u001B[0m 10 \u001B[32m    # \u001B[33m #    \u001B[0m 20   \u001B[33m  #\u001B[34m #   \u001B[0m  50  \u001B[34m   # \u001B[31m#  \u001B[0m  100   \u001B[31m  #\u001B[36m #  \u001B[0m  200   \u001B[36m # \u001B[35m#  \u001B[0m  500   \u001B[35m  # 
                \u001B[32m  `#        #'  \u001B[33m  `#        #' \u001B[34m  `#        #'  \u001B[31m `#        #' \u001B[36m  `#        #' \u001B[35m `#        #'
                \u001B[32m    `######'    \u001B[33m    `######'   \u001B[34m    `######'    \u001B[31m   `######'    \u001B[36m   `######'    \u001B[35m  `######'\u001B[0m
 
               """;
    public static final String HELP_DESCRIPTION = "Type /help to get the command list";



    public static final String BJ_RULES = """
                                        BLACKJACK RULES:
                                        
            -Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.
            -Cards are equal to their value with face cards being 10 and an Ace being 1 or 11.
            -The players cards are added up for their total.
            -Players "Hit" to gain another card from the deck. Players "Stay" to keep their current card total.
            -Dealer "Hits" until they equal or exceed 17.
            -The goal is to have a higher card total than the dealer without going over 21.
            -If the player total equals the dealer total, it is a "Push" and the hand ends.
            -Players win their bet if they beat the dealer. Players win 1.5x their bet if they get "Blackjack" which is 21.
            """;

    public static final String BJ_CARD_RULES = """
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

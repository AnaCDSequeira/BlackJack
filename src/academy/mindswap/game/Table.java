package academy.mindswap.game;

import academy.mindswap.server.Server;

import java.util.ArrayList;

public class Table {

    private static final int AMOUNT_OF_INITIAL_CARDS = 2;

    private static final double BLACKJACK_MULTIPLIER = 2.5;

    private static final double SIMPLE_WIN_MULTIPLIER = 2;

    private final ArrayList<Server.ClientHandler> players;

    private final Dealer dealer;

    private final DealerShoe dealerShoe;

    private boolean askedForCard; //TODO: associar o HIT

    public Table(ArrayList<Server.ClientHandler> players) {
        this.players = players;
        this.dealer = new Dealer();
        this.dealerShoe = new DealerShoe();
        startGame();
    }

    public void startGame() {
        dealerShoe.populateShoe();
        playBlackJack();
    }

    private void playBlackJack() {
//        askForBets(players);
        dealFirstRound();
        roundCheck();
        while (players.size() > 0) {
            playRound();
        }
    }

    private void playRound() {
        for (Server.ClientHandler player : players) {
            while (player.wantMoreCards()) {
                dealCardTo(player);
                checkPlayer(player);
            }
        }
        while (dealer.canPlay()) {
//            dealCardTo(dealer);
        }
        roundCheck();
    }

    private void roundCheck() {
        for (Server.ClientHandler player : players) {
            checkPlayer(player);
        }
    }

    private void checkPlayer(Server.ClientHandler player) {
        if (player.hasBlackJack() || player.hasBusted()) {
            dealWithBets();
            players.remove(player);
        }
    }

    private void dealWithBets() {
        int dealerScore = dealer.getScore();
        for (Server.ClientHandler player : players) {
            double betMultiplier;
            if (player.hasBlackJack()) {
                betMultiplier = BLACKJACK_MULTIPLIER;
            } else if (player.getScore() > dealerScore || dealer.hasBusted()) {
                betMultiplier = SIMPLE_WIN_MULTIPLIER;
            } else {
                betMultiplier = 0;
            }
            pay(player, betMultiplier);
        }
    }

    private void pay(Server.ClientHandler player, double betMultiplier) {
        player.getPayment(betMultiplier);
    }

    private void askForBets(ArrayList<Server.ClientHandler> players) {
        for (Server.ClientHandler player : players) {
            player.askForBet();
        }
    }

    private void dealFirstRound() {
        for (int i = 0; i < AMOUNT_OF_INITIAL_CARDS; i++) {
            for (Server.ClientHandler player : players) {
                dealCardTo(player);
            }
//            dealCardTo(dealer);
        }
    }

    private void dealCardTo(Server.ClientHandler player) {
        Card card = dealerShoe.askForCard();
        player.addCard(card);

    }

}

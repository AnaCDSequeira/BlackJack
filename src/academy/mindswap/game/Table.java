package academy.mindswap.game;

import academy.mindswap.server.ClientHandler;
import academy.mindswap.server.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static academy.mindswap.server.Server.NUMBER_OF_PLAYERS_PER_GAME;

/**
 * Class containing game funcionality
 */
public class Table implements Runnable {

    private static final int AMOUNT_OF_INITIAL_CARDS = 2;
    private static final double BLACKJACK_MULTIPLIER = 2.5;  // value to multiply with bet
    private static final double SIMPLE_WIN_MULTIPLIER = 2;  // value to multiply with bet

    private final Dealer dealer;
    private final DealerShoe dealerShoe;
    private final List<ClientHandler> clients;
    ExecutorService executorService;

    /**
     * constructor that creates a dealer, a list to accept players, a shoe to draw cards and a thread pool
     */
    public Table() {
        this.clients = new ArrayList<>();
        this.dealer = new Dealer();
        this.dealerShoe = new DealerShoe();
        executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        startGame();
    }

    /**
     * Check if players can have entered the table and set name and budget, and starts game
     */
    private void startGame() {
        if (clients.stream().allMatch(ClientHandler::isReadyToStart)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }

            startGame();
        }

        dealerShoe.populateShoe();
        playBlackJack();
    }

    /**
     * Method to start a new thread and add player to list of players
     *
     * @param clientHandler accepts a player sent by the server
     */

    public void addClient(ClientHandler clientHandler) {
        clients.add(clientHandler);
        executorService.submit(clientHandler);
    }

    /**
     * Checks the list of clients available is bigger than the minimum of players needed to start a game
     *
     * @return
     */
    public boolean canStart() {
        return clients.size() >= NUMBER_OF_PLAYERS_PER_GAME;
    }

    /**
     * Method to start game, gives cards to players and stars a round
     */
    private void playBlackJack() {
        try {
            dealFirstRound();
            playRound();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to checks if player has blackjack, then shows all players cards and asks if player wants to hit cards or stand.
     * Then it checks results
     *
     * @throws InterruptedException
     */

    private void playRound() throws InterruptedException {
        clients.forEach(client -> {
            Player player = client.getPlayer();
            checkBlackjack(client);
            do {
                client.showCards(player);
                showDealerCard(client);
                client.askForNextMove();
                if (player.wantMoreCards()) {
                    dealCardTo(player);
                    checkPlayer(client);
                }
            } while (player.wantMoreCards() && !player.isOutOfGame());
        });
        clients.forEach(client -> client.showCards(dealer));
        while (dealer.shouldDrawCards()) {
            dealCardTo(dealer);
            clients.forEach(client -> {
                client.sendMessageToUser(Messages.DEALER_DRAWING_CARD);
                client.showCards(dealer);
            });
        }
        checkResults(clients);
    }

    // TODO: Fix concurrency issues


    /**
     * Method to check if player has blackjack and sets his property "outOfGame" to true
     *
     * @param client receives a players to be checked
     */
    private void checkBlackjack(ClientHandler client) {
        Player player = client.getPlayer();
        if (player.hasBlackJack()) {
            client.showCards(player);
            player.setIsOutOfGame(true);
        }
    }

    /**
     * Method to show the player the dealers no hidden cards on the first hand
     *
     * @param client receives a client to send message
     */
    private void showDealerCard(ClientHandler client) {
        client.sendMessageToUser(String.format(
                Messages.SHOW_DEALER_FIRST_CARD,
                dealer.firstCard(),
                dealer.firstCard().getValue()
        ));
    }

    /**
     * Method to show cards and send messages to player if has blackjack or bust
     *
     * @param client receives a client to do the explanation above
     */

    private void checkPlayer(ClientHandler client) {
        Player player = client.getPlayer();
        if (player.hasBlackJack() || player.hasBusted()) {
            client.showCards(player);
            player.setIsOutOfGame(true);
        }
        if (player.hasBlackJack()) {
            client.sendMessageToUser(Messages.BLACKJACK);
            return;
        }
        if (player.hasBusted()) {
            client.sendMessageToUser(Messages.BUSTED);
        }
    }

    /**
     * Method to compare results from player and dealer and send messages accordingly
     *
     * @param clients receives the list of players
     */

    private void checkResults(List<ClientHandler> clients) {
        clients.forEach(client -> {
            checkPlayer(client);
            Player player = client.getPlayer();
            if (dealer.hasBusted()) {
                client.sendMessageToUser(Messages.BUSTED_DEALER);
                client.sendMessageToUser(Messages.SIMPLE_WIN);
                dealWithBets();
                return;
            }
            if (dealer.getScore() > player.getScore()) {
                client.sendMessageToUser(Messages.DEALER_WIN);
            } else if (dealer.getScore() == player.getScore()) {
                client.sendMessageToUser(Messages.DRAW);
            } else {
                client.sendMessageToUser(Messages.SIMPLE_WIN);
            }
            dealWithBets();
        });
    }

    /**
     * Method to check how much player will win or not accordingly to winning (blackjack, normal, no win)
     */

    private void dealWithBets() {
        int dealerScore = dealer.getScore();
        clients.forEach(client -> {
            Player player = client.getPlayer();
            double betMultiplier;

            if (player.hasBlackJack()) {
                betMultiplier = BLACKJACK_MULTIPLIER;
            } else if (player.getScore() > dealerScore || dealer.hasBusted()) {
                betMultiplier = SIMPLE_WIN_MULTIPLIER;
            } else {
                betMultiplier = 0;
            }
            pay(client, betMultiplier);
        });
    }

    /**
     * Method to pay the client
     *
     * @param client        client to be paid
     * @param betMultiplier how much to multiply his bet
     */
    private void pay(ClientHandler client, double betMultiplier) {
        client.getPlayer().setPayment(betMultiplier);
    }

    /**
     * Method to give cards to players and dealer
     */
    private void dealFirstRound() {
        for (int i = 0; i < AMOUNT_OF_INITIAL_CARDS; i++) {
            clients.forEach(client -> dealCardTo(client.getPlayer()));
            dealCardTo(dealer);
        }
    }

    /**
     * Methos to ask a card from the shoe to give to players
     *
     * @param person the player receiving the card
     */

    private void dealCardTo(Person person) {
        Card card = dealerShoe.askForCard();
        person.addCard(card);
    }
}

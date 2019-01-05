import java.util.Scanner;

/**
* Creates Table object that controls game play
* @author Tyrell
*/
public class Table {

    // Constant that represents amount for ante
    public final static int ANTE = 10;

    // Instance variables
    private int pot;
    private Player[] players = new Player[2];

    /**
    * Overloaded Table constructor
    * @param startPlayer Player object
    * @param startDealer Dealer object
    */
    public Table(Player startPlayer, Dealer startDealer) {

        players[0] = startPlayer;
        players[1] = startDealer;
        pot = 0;

        // clears players' hands
        players[0].fold();
        players[1].fold();

    }

    /**
    * Determines winner
    * @return winning player
    */
    public Player declareWinner() {

        int playerScore = 0;
        int dealerScore = 0;

        // dealer wins if player folds
        if (players[0].getHandSize() == 0) {
            return players[1];
        }

        playerScore = players[0].scoreHand();
        dealerScore = players[1].scoreHand();

        if (playerScore > dealerScore) {
            return players[0];
        } else if (dealerScore < playerScore) {
            return players[1];
        } else {
            return players[1]; // in the event of a tie, dealer wins
        }

    }

    /**
    * Pot accessor method
    * @return amount of money in pot
    */
    protected int getPot() {
        return pot;
    }

    /**
    * Reveals dealer's hidden card
    */
    public void show() {
        Card dealerHiddenCard = players[1].getCard(0);
        dealerHiddenCard.show();
    }

    /**
    * Gets bets from players and adds them to pot
    */
    public void getBets() {

        int playerBet = -1;
        int dealerBet = -1;

        // Validates user's bet
        while (playerBet == -1) {
            playerBet = players[0].bet();
        }

        // Dealer's bet will equal player's bet unless the player bets more than the dealer has, then dealer bets remaining money
        if (playerBet > players[1].getStash()) {
            dealerBet = players[1].getStash();
        } else {
            dealerBet = playerBet;
            players[1].setStash(players[1].getStash() - dealerBet);
        }

        // Bets are added to pot
        pot += (playerBet + dealerBet);

    }

    /**
    * Collects ante from both players
    */
    protected void getAnte() {

        System.out.println("\nAnte up $" + ANTE);

        // Player is out of the game if they do not have enough money for ante
        if (players[0].getStash() < ANTE) {
            players[0].setCanAnte(false);
        } 

        // Dealer is out of the game if they do not have enough money for ante
        else if (players[1].getStash() < ANTE) {
            players[1].setCanAnte(false);
        } 

        // Ante is subtracted from players' stashes and added to pot
        else {
            players[0].setStash(players[0].getStash() - ANTE);
            players[1].setStash(players[1].getStash() - ANTE);
            pot += ANTE * 2;
        }

    }

    /**
    * Controls game play
    */
    public void play() {

        @SuppressWarnings("resource")
        Scanner scnr = new Scanner(System.in);
        Player player = players[0];
        Dealer dealer = (Dealer) players[1];
        char betOption = ' ';

        // Collect ante before cards are dealt
        getAnte();

        // Game is over if one or both players are unable to meet the ante, otherwise proceeds as normal
        if (!player.getCanAnte()) {
            System.out.println("Player is out of money!");
        } else if (!dealer.getCanAnte()) {
            System.out.println("Dealer is out of money!");
        } else {

            System.out.println("\n******************************************************");
            System.out.println("Dealing first and second cards...\n");

            // First two cards dealt to both players
            for (int i = 0; i < 2; i++) {
                dealer.dealToPlayer(player);
                dealer.dealToPlayer(dealer);
            }

            System.out.println(player.toString());
            System.out.println(dealer.toString());
            System.out.println("Total money in pot: $" + getPot());
            System.out.println("******************************************************\n");

            // Initiates betting rounds and deals remaining cards
            for (int i = 0; i < 3; i++) {

                betOption = ' ';

                // Asks user if they would like to bet or fold
                while (betOption != 'b' && betOption != 'f') {
                    System.out.print("Enter b for bet or f for fold: ");
                    betOption = scnr.next().charAt(0);
                }

                if (betOption == 'b') {
                    getBets();
                } else if (betOption == 'f') {
                    player.fold();
                    break;
                }

                System.out.println("\n******************************************************");
                System.out.println("Dealing card #" + (i + 3) + "...\n");
                dealer.dealToPlayer(player);
                dealer.dealToPlayer(dealer);
                System.out.println(player.toString());

                // After final card is dealt, reveal dealer's hidden card
                if (i == 2) {
                    show();
                }

                System.out.println(dealer.toString());
                System.out.println("Total money in pot: $" + getPot());
                System.out.println("******************************************************\n");

            }

            // Declares winner and adds pot to winning player's stash
            Player winningPlayer = declareWinner();
            winningPlayer.setStash(winningPlayer.getStash() + pot);
            System.out.println(winningPlayer.getName() + " has won!");

        }

    }

}

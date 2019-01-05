import java.util.InputMismatchException;
import java.util.Scanner;

/**
* 5 Card Stud Poker game
* @author Tyrell
*/
public class PokerClient {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        char runAgain = 'y';
        char modeInput = ' ';

        // Prompts user to run test mode or play game
        while (runAgain == 'y') {

            runAgain = ' ';
            modeInput = ' ';

            while (modeInput != 't' && modeInput != 'g') {
                System.out.print("Enter 't' to enter test mode or 'g' to play game: ");
                modeInput = scnr.next().charAt(0);

                switch (modeInput) {
                    case 't': testMode();
                        break;
                    case 'g': gameMode(scnr);
                        break;
                    default: System.out.println("Invalid input!");
                }

            }

            while (runAgain != 'y' && runAgain != 'n') {
                System.out.println("Would you like to run again ('y' for yes, 'n' for no)? ");
                runAgain = scnr.next().charAt(0);
            }
        }

        scnr.close();

    }

    /**
    * Runs tests
    */
    public static void testMode() {
        TestDriver.runTests();
    }

    /**
    * Starts game of poker
    * @param scnr Scanner object
    */
    public static void gameMode(Scanner scnr) {

        String playerName = " ";
        int playerStash = -1;
        char runAgain = 'y';

        // Prompts for player's name
        System.out.print("Enter player's name: ");
        playerName = scnr.next();

        // Prompts and validates player's starting stash
        while (playerStash < 0) {

            System.out.print("Enter player's starting amount of money: ");
            try {
                playerStash = scnr.nextInt();
            } 
            catch (InputMismatchException e) {
                System.err.println("Not an integer!\n");
                scnr.next();
            }

        }

        // Creates Player object with user specified name and starting stash
        Player player = new Player(playerName, playerStash);

        // Creates Dealer object with name 'Dealer' and starting stash 5 times higher than player
        Dealer dealer = new Dealer("Dealer", playerStash * 5);

        // Runs game until user quits or one of the players cannot make the ante
        while(runAgain == 'y' && player.getCanAnte() && dealer.getCanAnte()) {

            runAgain = ' ';
            Table table = new Table(player, dealer);
            table.play();

            while (runAgain != 'y' && runAgain != 'n') {
                System.out.print("Play Again? (y for yes or n for no) ");
                runAgain = scnr.next().charAt(0);
            }

        }

        System.out.println("Goodbye!");

    }

}

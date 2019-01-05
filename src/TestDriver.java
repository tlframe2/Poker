/**
* Initiates tests for every class
* @author Tyrell
*/
public class TestDriver {

    /**
    * Runs all tests
    */
    public static void runTests() {

    testCardClass();
    testPlayerClass();
    testDealerClass();
    printDeck();
    dealCard();
    testCheckRoyalFlush();
    testCheckStraightFlush();
    testCheckFourOfAKind();
    testCheckFullHouse();
    testCheckFlush();
    testCheckStraight();
    testCheckThreeOfAKind();
    testCheckTwoPairs();
    testCheckPair();
    testBets();

    }

    /**
    * Creates Card Objects and tests methods
    */
    private static void testCardClass() {

        System.out.println("******************************************************");
        System.out.println("Beginning Card class test...\n");

        System.out.println("Creating Card object using default constructor...");
        Card blank = new Card();
        System.out.println("Card value equals 0: " + (blank.getValue() == 0));
        System.out.println("Card suit is null: " + (blank.getSuit() == null));
        System.out.println("Card name is null: " + (blank.getName() == null));
        System.out.println("Card is hidden: " + (blank.isVisible() == false));
        System.out.println("Setting Card value to 9 and suit to Hearts...");
        blank.setValue(9);
        blank.setSuit(Suit.Hearts);
        System.out.println("Card value equals 9: " + (blank.getValue() == 9));
        System.out.println("Card suit equals Hearts: " + (blank.getSuit() == Suit.Hearts));
        System.out.println("Card name equals 9: " + blank.getName().equals("9"));
        System.out.println("toString method returns Hidden Card: " + blank.toString().equals("Hidden Card"));
        System.out.println("Changing Card visibility to true...");
        blank.show();
        System.out.println("Card is shown: " + blank.isVisible());
        System.out.println("toString method returns 9 of Hearts: " + blank.toString().equals("9 of Hearts"));

        System.out.println("\nCreating Card object using overloaded constructor with value = 13 and suit = Spades...");
        Card kingSpades = new Card(13, Suit.Spades);
        System.out.println("Card value equals 13: " + (kingSpades.getValue() == 13));
        System.out.println("Card suit equals Spades: " + (kingSpades.getSuit() == Suit.Spades));
        System.out.println("Card name equals King: " + kingSpades.getName().equals("King"));
        System.out.println("Card is shown: " + kingSpades.isVisible());
        System.out.println("toString method returns King of Spades: " + kingSpades.toString().equals("King of Spades"));
        System.out.println("Changing Card visibility to false...");
        kingSpades.hide();
        System.out.println("Card is hidden: " + (kingSpades.isVisible() == false));
        System.out.println("toString method returns Hidden Card: " + kingSpades.toString().equals("Hidden Card"));

        System.out.println("\nCard class test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Creates Player Objects and tests methods
    */
    private static void testPlayerClass() {

        System.out.println("******************************************************");
        System.out.println("Beginning Card class test...\n");

        System.out.println("Creating Player object using default constructor...");
        Player blank = new Player();
        System.out.println("Player name set to null: " + (blank.getName() == null));
        System.out.println("Player stash set to 0: " + (blank.getStash() == 0));
        System.out.println("Setting Player name to Tyrell and stash to 1000...");
        blank.setName("Tyrell");
        blank.setStash(1000);
        System.out.println("Player name set to Tyrell: " + blank.getName().equals("Tyrell"));
        System.out.println("Player stash set to 1000: " + (blank.getStash() == 1000));
        System.out.println("toString method:\n" + blank.toString());

        System.out.println("Creating Player object using overloaded constructor with name = Gary and stash = 1337...");
        Player gary = new Player("Gary", 1337);
        System.out.println("Player name set to Gary: " + gary.getName().equals("Gary"));
        System.out.println("Player stash set to 1337: " + (gary.getStash() == 1337));
        System.out.println("toString method:\n" + gary.toString());

        System.out.println("Player class test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Creates Dealer Objects and tests methods
    */
    private static void testDealerClass() {

        System.out.println("******************************************************");
        System.out.println("Beginning Dealer class test...\n");

        System.out.println("Creating Dealer object using default constructor...");
        Dealer blank = new Dealer();
        System.out.println("Dealer name set to null " + (blank.getName() == null));
        System.out.println("Dealer stash set to 0: " + (blank.getStash() == 0));

        System.out.println("\nCreating Dealer object using overloaded constructor with name = Dealer and stash = 5000...");
        Dealer dealer = new Dealer("Dealer", 5000);
        System.out.println("Dealer name set to Dealer: " + dealer.getName().equals("Dealer"));
        System.out.println("Dealer stash set to 5000: " + (dealer.getStash() == 5000));
        System.out.println("toString method:\n" + dealer.toString());

        System.out.println("Dealer class test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Creates Deck object and fills it with 52 Card objects and prints them
    */
    private static void printDeck() {

        Deck deck = new Deck();

        System.out.println("******************************************************");
        System.out.println("Printing contents of Deck...\n");

        System.out.println("The deck contains: ");

        for (int i = 1; i <= 52; i++) {
        System.out.println(deck.deal().toString());
        }

        System.out.println("Finished printing deck!");
        System.out.println("******************************************************\n");

    }

    /**
    * Creates Player and Dealer objects and tests dealToPlayer method
    */
    private static void dealCard() {

        Player player = new Player("Tyrell", 2000);
        Dealer dealer = new Dealer("Dealer", 10000);

        System.out.println("******************************************************");
        System.out.println("Dealing cards to players...\n");

        for (int i = 1; i <= 5; i++) {
        dealer.dealToPlayer(player);
        dealer.dealToPlayer(dealer);
        }

        System.out.println(player.toString());
        System.out.println(dealer.toString());

        System.out.println("Finished dealing cards!");
        System.out.println("******************************************************\n");

    }

    /**
    * Tests checkRoyalFlush method
    */
    private static void testCheckRoyalFlush() {

        System.out.println("******************************************************");
        System.out.println("Beginning checkRoyalFlush test...\n");

        Player winner = new Player("Winner", 0);

        winner.addToHand(new Card(12, Suit.Hearts));
        winner.addToHand(new Card(11, Suit.Hearts));
        winner.addToHand(new Card(10, Suit.Hearts));
        winner.addToHand(new Card(14, Suit.Hearts));
        winner.addToHand(new Card(13, Suit.Hearts));

        System.out.println(winner.toString());

        System.out.println("Has Royal Flush: " + (winner.scoreHand() == 9000000));

        Player loser = new Player("Loser", 0);

        loser.addToHand(new Card(12, Suit.Hearts));
        loser.addToHand(new Card(11, Suit.Hearts));
        loser.addToHand(new Card(10, Suit.Diamonds));
        loser.addToHand(new Card(14, Suit.Hearts));
        loser.addToHand(new Card(13, Suit.Hearts));

        System.out.println(loser.toString());

        System.out.println("Does Not Have Royal Flush: " + (loser.scoreHand() == 4576012));

        System.out.println("\ncheckRoyalFlush test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Tests checkStraightFlush method
    */
    private static void testCheckStraightFlush() {

        System.out.println("******************************************************");
        System.out.println("Beginning checkStraightFlush test...\n");

        Player winner = new Player("Winner", 0);

        winner.addToHand(new Card(7, Suit.Hearts));
        winner.addToHand(new Card(9, Suit.Hearts));
        winner.addToHand(new Card(6, Suit.Hearts));
        winner.addToHand(new Card(8, Suit.Hearts));
        winner.addToHand(new Card(5, Suit.Hearts));

        System.out.println(winner.toString());

        System.out.println("Has Straight Flush: " + (winner.scoreHand() == 8369157));

        Player loser = new Player("Loser", 0);

        loser.addToHand(new Card(7, Suit.Hearts));
        loser.addToHand(new Card(9, Suit.Spades));
        loser.addToHand(new Card(6, Suit.Hearts));
        loser.addToHand(new Card(8, Suit.Hearts));
        loser.addToHand(new Card(5, Suit.Hearts));

        System.out.println(loser.toString());

        System.out.println("Does Not Have Straight Flush: " + (loser.scoreHand() == 4369157));

        System.out.println("\ncheckStraightFlush test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Tests checkFourOfAKind method
    */
    private static void testCheckFourOfAKind() {

        System.out.println("******************************************************");
        System.out.println("Beginning checkFourOfAKind test...\n");

        Player player = new Player("Tyrell", 0);

        player.addToHand(new Card(11, Suit.Hearts));
        player.addToHand(new Card(11, Suit.Spades));
        player.addToHand(new Card(6, Suit.Hearts));
        player.addToHand(new Card(11, Suit.Diamonds));
        player.addToHand(new Card(11, Suit.Clubs));

        System.out.println(player.toString());

        System.out.println("Has Four of a Kind: " + (player.scoreHand() == 7000011));

        System.out.println("\ncheckFourOfAKind test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Tests checkFullHouse method
    */
    private static void testCheckFullHouse() {

        System.out.println("******************************************************");
        System.out.println("Beginning checkFullHouse test...\n");

        Player player = new Player("Tyrell", 0);

        player.addToHand(new Card(6, Suit.Hearts));
        player.addToHand(new Card(9, Suit.Spades));
        player.addToHand(new Card(6, Suit.Diamonds));
        player.addToHand(new Card(9, Suit.Diamonds));
        player.addToHand(new Card(6, Suit.Clubs));

        System.out.println(player.toString());

        System.out.println("Has Full House: " + (player.scoreHand() == 6000006));

        System.out.println("\ncheckFullHouse test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Tests checkFlush method
    */
    private static void testCheckFlush() {

        System.out.println("******************************************************");
        System.out.println("Beginning checkFlush test...\n");

        Player player = new Player("Tyrell", 0);

        player.addToHand(new Card(7, Suit.Hearts));
        player.addToHand(new Card(12, Suit.Hearts));
        player.addToHand(new Card(4, Suit.Hearts));
        player.addToHand(new Card(6, Suit.Hearts));
        player.addToHand(new Card(10, Suit.Hearts));

        System.out.println(player.toString());

        System.out.println("Has Flush: " + (player.scoreHand() == 5489892));

        System.out.println("\ncheckFlush test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Tests checkStraight method
    */
    private static void testCheckStraight() {

        System.out.println("******************************************************");
        System.out.println("Beginning checkStraight test...\n");

        Player player = new Player("Tyrell", 0);

        player.addToHand(new Card(6, Suit.Hearts));
        player.addToHand(new Card(7, Suit.Hearts));
        player.addToHand(new Card(8, Suit.Spades));
        player.addToHand(new Card(9, Suit.Hearts));
        player.addToHand(new Card(10, Suit.Hearts));

        System.out.println(player.toString());

        System.out.println("Has Straight: " + (player.scoreHand() == 4410528));

        System.out.println("\ncheckStraight test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Tests checkThreeOfAKind method
    */
    private static void testCheckThreeOfAKind() {

        System.out.println("******************************************************");
        System.out.println("Beginning checkThreeOfAKind test...\n");

        Player player = new Player("Tyrell", 0);

        player.addToHand(new Card(6, Suit.Hearts));
        player.addToHand(new Card(6, Suit.Diamonds));
        player.addToHand(new Card(6, Suit.Spades));
        player.addToHand(new Card(9, Suit.Hearts));
        player.addToHand(new Card(10, Suit.Hearts));

        System.out.println(player.toString());

        System.out.println("Has Three of a Kind: " + (player.scoreHand() == 3000006));

        System.out.println("\ncheckThreeOfAKind test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Tests checkTwoPairs method
    */
    private static void testCheckTwoPairs() {

        System.out.println("******************************************************");
        System.out.println("Beginning checkTwoPairs test...\n");

        Player player = new Player("Tyrell", 0);

        player.addToHand(new Card(6, Suit.Hearts));
        player.addToHand(new Card(6, Suit.Diamonds));
        player.addToHand(new Card(9, Suit.Spades));
        player.addToHand(new Card(9, Suit.Hearts));
        player.addToHand(new Card(10, Suit.Hearts));

        System.out.println(player.toString());

        System.out.println("Has Two Pairs: " + (player.scoreHand() == 2001858));

        System.out.println("\ncheckTwoPairs test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Tests checkPair method
    */
    private static void testCheckPair() {

        System.out.println("******************************************************");
        System.out.println("Beginning checkPair test...\n");

        Player player = new Player("Tyrell", 0);

        player.addToHand(new Card(12, Suit.Hearts));
        player.addToHand(new Card(6, Suit.Diamonds));
        player.addToHand(new Card(9, Suit.Spades));
        player.addToHand(new Card(12, Suit.Clubs));
        player.addToHand(new Card(10, Suit.Hearts));

        System.out.println(player.toString());

        System.out.println("Has Pair: " + (player.scoreHand() == 1035020));

        System.out.println("\ncheckPair test finished!");
        System.out.println("******************************************************\n");

    }

    /**
    * Tests getting bets
    */
    private static void testBets() {

        Player player = new Player("Tyrell", 1000);
        Dealer dealer = new Dealer("Dealer", 5000);
        Table table = new Table(player, dealer);

        System.out.println("Round One Betting...");
        table.getBets();
        System.out.println("Pot total after round one: " + table.getPot());

        System.out.println("Round Two Betting...");
        table.getBets();
        System.out.println("Pot total after round two: " + table.getPot());

    }

}

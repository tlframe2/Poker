import java.util.*;
import java.lang.Math;

/**
* Creates Player object that represents user
* @author Tyrell
*/
public class Player {

    // instance variables
    private String name;
    private int stash;
    private boolean canAnte; // determines if player has enough money to ante

    // represents player's hand
    private ArrayList<Card> playerHand = new ArrayList<Card>();

    /**
    * Default Player constructor; initializes instance variables with default values
    */
    public Player() {
        name = null;
        stash = 0;
        canAnte = false;
    }

    /**
    * Overloaded Player constructor; initializes instance variables with user defined values
    * @param startName name of player
    * @param startStash starting amount of money for player
    */
    public Player(String startName, int startStash) {
        name = startName;

        // Sets stash to 0 if user provides negative int
        stash = startStash > 0 ? startStash : 0;

        // true or false depending on if starting stash is greater than ante
        canAnte = startStash >= Table.ANTE ? true : false;
    }

    /**
    * Adds card to playerHand ArrayList
    * @param cardDealt card dealt to player
    */
    protected void addToHand(Card cardDealt) {
        playerHand.add(cardDealt);
    }

    /**
    * Name accessor method
    * @return player's name
    */
    protected String getName() {
        return name;
    }

    /**
    * Stash accessor method
    * @return player's stash
    */
    protected int getStash() {
        return stash;
    }

    /**
    * CanAnte accessor method
    * @return player's ability to ante
    */
    protected boolean getCanAnte() {
        return canAnte;
    }

    /**
    * Calculates hand size
    * @return number of cards in player's hand
    */
    protected int getHandSize() {
        return playerHand.size();
    }

    /**
    * Retrieves card in player's hand
    * @param index position of card in hand
    * @return card at specified index
    */
    protected Card getCard(int index) {
        return playerHand.get(index);
    }

    /**
    * Name mutator method
    * @param newName name of player
    */
    protected void setName(String newName) {
        name = newName;
    }

    /**
    * Stash mutator
    * @param newStash player's stash
    */
    protected void setStash(int newStash) {
        if (newStash >= 0) {
            stash = newStash;
        } else {
            System.out.println("Stash cannot be negative!");
        }
    }

    /**
    * CanAnte mutator method
    * @param anteStatus boolean representing player's ability to meet ante
    */
    protected void setCanAnte(boolean anteStatus) {
        canAnte = anteStatus;
    }

    /**
    * Prompts user to enter amount of money to bet
    * @return amount bet or -1 if bet is invalid
    */
    public int bet() {

        @SuppressWarnings("resource")
        Scanner scnr = new Scanner(System.in);
        int betAmount = -1;

        // Validates bet to ensure that it is positive and an integer
        while (betAmount < 0) {
            System.out.print("Enter bet amount: ");

            try {
                betAmount = scnr.nextInt();
            } 
            catch (InputMismatchException e) {
                System.err.println("Not an integer!\n");
                scnr.next();
            }

        }

        if (betAmount > stash) {
            System.out.println("Cannot bet more money than you have!");
            return -1;
        } else {
            stash -= betAmount;
            return betAmount;
        }

    }

    /**
    * Clears player's hand
    */
    public void fold() {
        playerHand.clear();
    }

    /**
    * Returns string of Player attributes
    * @return player's name, hand, and stash
    */
    public String toString() {

        String returnValue = "";

        returnValue += "Name: " + name + "\n";
        returnValue += "Stash: $" + stash + "\n";
        returnValue += "Hand: \n";

        for (Card currCard: playerHand) {
            returnValue += currCard + "\n";
        }

        return returnValue;

    }

    /**
    * Returns score based on hand ranking
    * @return player's score
    */
    public int scoreHand() {

        TreeMap<String, Integer> freq = valueFreq();

        // royal flush
        if (checkRoyalFlush()) { 
            return 9000000;
        } 

        // straight flush
        else if (checkStraightFlush()) { 
            return 8000000 + valueHighCard();
        } 

        // four of a kind
        else if (checkXOfAKind(freq, 4)) { 
            return 7000000 + valueSet();
        } 

        // full house
        else if (checkXOfAKind(freq, 3) && checkXOfAKind(freq, 2)) { 
            return 6000000 + valueSet();
        } 

        // flush
        else if (checkFlush()) { 
            return 5000000 + valueHighCard();
        } 

        // straight
        else if (checkStraight()) { 
            return 4000000 + valueHighCard();
        } 

        // three of a kind
        else if (checkXOfAKind(freq, 3)) { 
            return 3000000 + valueSet(); 
        } 

        // two pairs
        else if (checkTwoPairs(freq)) { 
            return 2000000 + valueTwoPairs();
        } 

        // pairs
        else if (checkXOfAKind(freq, 2)) { 
            return 1000000 + valueOnePair();
        } 

        // high card
        else {
            return 0 + valueHighCard();
        }

    }

    /**
    * Sorts cards by value in ascending order
    */
    private void sortByValue() {

        int minIndex;
        Card temp;

        for (Card currCard: playerHand) {
            if (!currCard.isVisible()) {
                currCard.show();
            }
        }

        for (int i = 0; i < playerHand.size(); i++) {
            minIndex = i;
            for (int j = i + 1; j < playerHand.size(); j++) {
                if (playerHand.get(j).getValue() < playerHand.get(minIndex).getValue()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                temp = playerHand.get(minIndex);
                playerHand.set(minIndex, playerHand.get(i));
                playerHand.set(i, temp);
            }
        }

    }

    /**
    * Sorts cards by suit following order in Suit enum
    */
    private void sortBySuit() {

        int minIndex;
        Card temp;

        for (Card currCard: playerHand) {
            if (!currCard.isVisible()) {
                currCard.show();
            }
        }

        for (int i = 0; i < playerHand.size(); i++) {
            minIndex = i;
            for (int j = i + 1; j < playerHand.size(); j++) {
                if (playerHand.get(j).getSuit().ordinal() < playerHand.get(minIndex).getSuit().ordinal()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                temp = playerHand.get(minIndex);
                playerHand.set(minIndex, playerHand.get(i));
                playerHand.set(i, temp);
            }
        }

    }

    /**
    * Checks hand for flush
    * @return boolean of whether hand is a flush or not
    */
    private boolean checkFlush() {

        sortBySuit();

        for (int i = 0; i < playerHand.size() - 1; i++) {
            if (playerHand.get(i).getSuit() != playerHand.get(i + 1).getSuit()) {
                return false;
            }
        }

        return true;

    }

    /**
    * Checks hand for straight
    * @return boolean of whether hand is a straight or not
    */
    private boolean checkStraight() {

        sortByValue();

        if (playerHand.get(0).getValue() == 2 && playerHand.get(1).getValue() == 3 && playerHand.get(2).getValue() == 4 && 
        playerHand.get(3).getValue() == 5 && playerHand.get(4).getValue() == 14) {
            return true;
        }

        for (int i = 0; i < playerHand.size() - 1; i++) {
            if (playerHand.get(i).getValue() != playerHand.get(i + 1).getValue() - 1) {
                return false;
            }
        }

        return true;

    }

    /**
    * Checks hand for straight flush
    * @return boolean of whether hand is both a straight and a flush or not
    */
    private boolean checkStraightFlush() {

        if (checkFlush() && checkStraight()) {
            return true;
        } else {
            return false;
        }

    }

    /**
    * Checks hand for royal flush
    * @return boolean of whether hand is a royal flush or not
    */
    private boolean checkRoyalFlush() {

        if (checkStraightFlush() && playerHand.get(0).getValue() == 10) {
            return true;
        } else {
            return false;
        }

    }

    /**
    * Counts number of times card appears in hand
    * @return map of card values and frequencies
    */
    private TreeMap<String, Integer> valueFreq() {

        TreeMap<String, Integer> count = new TreeMap<String, Integer>();
        String cardName;
        Integer valueCount;

        for (Card currCard: playerHand) {
            cardName = currCard.getName();
            valueCount = count.get(cardName);
            if (valueCount == null) {
                valueCount = 0;
            }
            count.put(cardName, ++valueCount);
        }

        return count;

    }

    /**
    * Checks pairs, 3s of a kind, and 4s of a kind
    * @param freq map of value occurrences
    * @param numSameCard number of identical cards
    * @return boolean of whether hand contains the same card of specified frequency 
    */
    private boolean checkXOfAKind(TreeMap<String, Integer> freq, Integer numSameCard) {
        if (freq.containsValue(numSameCard)) {
            return true;
        } else {
            return false;
        }
    }

    /**
    * Check for two pairs
    * @param freq map of value occurrences
    * @return boolean of whether hand has two pairs or not
    */
    private boolean checkTwoPairs(TreeMap<String, Integer> freq) {

        int pairCount = 0;

        for (String key: freq.keySet()) {
            if (freq.get(key) == 2) {
                pairCount++;
            }
        }

        if (pairCount == 2) {
            return true;
        } else {
            return false;
        }

    }

    /**
    * Weighs value of hand based on highest card
    * @return hand value
    */
    private int valueHighCard() {

        sortByValue();

        int value = 0;

        value = playerHand.get(0).getValue() + 14 * playerHand.get(1).getValue() + (int) Math.pow(14, 2) * playerHand.get(2).getValue()
        + (int) Math.pow(14, 3) * playerHand.get(3).getValue() + (int) Math.pow(14, 4) * playerHand.get(4).getValue();

        return value;

    }

    /**
    * Weighs value of hand based on value of cards that make up pair
    * @return value of hand
    */
    private int valueOnePair() {

        sortByValue();

        int value = 0;

        // first and second cards are pair
        if (playerHand.get(0).getValue() == playerHand.get(1).getValue()) {
            value = (int) Math.pow(14, 3) * playerHand.get(0).getValue() + playerHand.get(2).getValue() + 14 * playerHand.get(3).getValue() 
            + (int) Math.pow(14, 2) * playerHand.get(4).getValue();
        } 

        // second and third cards are pair
        else if (playerHand.get(1).getValue() == playerHand.get(2).getValue()) {
            value = (int) Math.pow(14, 3) * playerHand.get(1).getValue() + playerHand.get(0).getValue() + 14 * playerHand.get(3).getValue() 
            + (int) Math.pow(14, 2) * playerHand.get(4).getValue();
        } 

        // third and fourth cards are pair
        else if (playerHand.get(2).getValue() == playerHand.get(3).getValue()) {
            value = (int) Math.pow(14, 3) * playerHand.get(2).getValue() + playerHand.get(0).getValue() + 14 * playerHand.get(1).getValue() 
            + (int) Math.pow(14, 2) * playerHand.get(4).getValue();
        } 

        // fourth and fifth cards are pair
        else {
            value = (int) Math.pow(14, 3) * playerHand.get(3).getValue() + playerHand.get(0).getValue() + 14 * playerHand.get(1).getValue() 
            + (int) Math.pow(14, 2) * playerHand.get(2).getValue();
        }

        return value;

    }


    /**
    * Weighs value of hand based on value of cards that make up both pairs
    * @return value of hand
    */
    private int valueTwoPairs() {

        sortByValue();

        int value = 0;

        // last card is not in a pair
        if (playerHand.get(0).getValue() == playerHand.get(1).getValue() && playerHand.get(2).getValue() == playerHand.get(3).getValue()) {
            value = (int) Math.pow(14, 2) * playerHand.get(2).getValue() + playerHand.get(4).getValue() + 14 * playerHand.get(0).getValue();
        } 

        // middle card is not in a pair
        else if (playerHand.get(0).getValue() == playerHand.get(1).getValue() && playerHand.get(2).getValue() == playerHand.get(3).getValue()) {
            value = (int) Math.pow(14, 2) * playerHand.get(3).getValue() + playerHand.get(2).getValue() + 14 * playerHand.get(0).getValue();
        } 

        // first card is not in pair
        else {
            value = (int) Math.pow(14, 2) * playerHand.get(3).getValue() + playerHand.get(0).getValue() + 14 * playerHand.get(1).getValue();
        }

        return value;

    }

    /**
    * Weighs value of hand based on value of cards for 3 of a kinds, 4 of a kinds, and full houses
    * @return value of hand
    */
    private int valueSet() {

        sortByValue();

        // card in set will always be in the middle
        return playerHand.get(2).getValue();

    }

}

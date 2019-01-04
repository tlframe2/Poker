import java.util.*;

/**
 * Creates Deck object that represents a deck of playing cards
 * @author Tyrell
 */
public class Deck {

    // Holds 52 unique playing cards
    private ArrayList<Card> cards = new ArrayList<Card>();

    /**
    * Default Deck constructor; initializes ArrayList with 52 cards
    */
    public Deck() {

    // creates cards with values 2-14 for each of the four suits
    for (int i = 2; i <= 14; i++) {
        cards.add(new Card(i, Suit.Hearts));
        cards.add(new Card(i, Suit.Diamonds));
        cards.add(new Card(i, Suit.Spades));
        cards.add(new Card(i, Suit.Clubs));
    }

    // shuffles Deck
    shuffle();

    }

    /**
    * Randomizes order of cards in Deck
    */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
    * Removes card from "top" of Deck
    * @return first Card object in ArrayList cards
    */
    public Card deal() {
        return cards.remove(0);
    }
	
}

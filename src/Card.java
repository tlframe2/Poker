/**
 * Creates Card object that represents standard playing card
 * @author Tyrell
 */
public class Card {

    // Instance variables
    private int value;
    private Suit suit;
    private boolean visible;
    private String name;

    /**
    * Default Card constructor; initializes with default values
    */
    public Card() {
        value = 0;
        suit = null;
        visible = false;
        name = null;
    }

    /**
    * Overloaded Card constructor; initializes with defined values
    * @param startValue numerical value of card
    * @param startSuit suit of card
    */
    public Card(int startValue, Suit startSuit) {
        value = startValue;
        suit = startSuit;
        visible = true;

        setName();
    }

    /**
    * Value accessor method
    * @return value of card
    */
    protected int getValue() {
        return value;
    }

    /**
    * Suit accessor method
    * @return suit of card
    */
    protected Suit getSuit() {
        return suit;
    }

    /**
    * Name accessor method
    * @return name of card
    */
    protected String getName() {
        return name;
    }

    /**
    * Value mutator method
    * @param newValue value of card
    */
    protected void setValue(int newValue) {
        value = newValue;
        setName(); // renames card
    }

    /** 
    * Suit mutator method
    * @param newSuit suit of card
    */
    protected void setSuit(Suit newSuit) {
        suit = newSuit;
    }

    /**
    * Sets name based on value
    */
    private void setName() {

        if (value == 14) {
            name = "Ace";
        } else if (value == 11) {
            name = "Jack";
        } else if (value == 12) {
            name = "Queen";
        } else if (value == 13) {
            name = "King";
        } else {
            name = String.valueOf(value);
        }

    }

    /**
    * Returns card's visibility
    * @return boolean representing card's visibility
    */
    protected boolean isVisible() {
        return visible;
    }

    /**
    * Changes card's visibility to true
    */
    public void show() {
        visible = true;
    }

    /**
    * Changes card's visibility to false
    */
    public void hide() {
        visible = false;
    }

    /**
    * Returns String of Card attributes
    * @return card's suit and value if visible or 'Hidden Card' if not visible
    */
    public String toString() {
        if (visible) {
            return name + " of " + suit;
        } else {
            return "Hidden Card";
        }
    }

}

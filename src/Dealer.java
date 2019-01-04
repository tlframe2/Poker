/**
* Creates Dealer object that inherits from Player Class
* @author Tyrell
*/
public class Dealer extends Player {

    // Creates new deck of cards
    private Deck deck = new Deck();

    /**
    * Default Dealer constructor; initializes instance variables with default values
    */
    public Dealer() {
        super.setName(null);
        super.setStash(0);
        super.setCanAnte(false);
    }

    /**
    * Overloaded Dealer constructor; initializes instance variables with user defined values
    * @param startName name of dealer
    * @param startStash starting amount of money
    */
    public Dealer(String startName, int startStash) {
        super(startName, startStash);
    }

    /**
    * Transfer card from deck to player's hand
    * @param player player being dealt to
    */
    public void dealToPlayer(Player player) {

        // deals first card to dealer as hidden
        if (player.getHandSize() == 0 && player.getName().equals("Dealer")) {
            Card hiddenCard = deck.deal();
            hiddenCard.hide();
            player.addToHand(hiddenCard);
        } else {
            player.addToHand(deck.deal());
        }

    }

}

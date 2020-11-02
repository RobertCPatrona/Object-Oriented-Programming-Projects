package cardGame.model;

/**
 * A deck that contains two complete decks. Usable for games requiring two decks
 * of cards.
 * <p>
 * Basically added to the codebase for illustrative purposes. Shows how to make 
 * a custom deck for different games.
 */
public class DoubleDeck extends AbstractDeck {

    /**
     * Add all possible cards twice
     */
    protected void addCards() {
        for(Card card : Card.values())
        {
            addOnTop(card);
            addOnTop(card);
        }   
    }
}

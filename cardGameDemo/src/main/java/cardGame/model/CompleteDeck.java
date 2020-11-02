package cardGame.model;

/**
 * A deck that has all possible cards
 */
public class CompleteDeck extends AbstractDeck {

    /**
     * Add all possible cards
     */
    protected void addCards() {
        for(Card card : Card.values())
            addOnTop(card);
    }
}

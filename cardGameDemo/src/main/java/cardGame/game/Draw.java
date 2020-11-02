package cardGame.game;

import cardGame.model.AbstractDeck;
import cardGame.model.CompleteDeck;
import cardGame.model.Card;
import cardGame.model.DiscardPile;

import java.util.Observable;
import java.util.Observer;

/**
 * Represents a silly 'game' (if you can even call it that) that draws cards
 * until the deck is empty, then shuffles them all back in.
 */
public class Draw extends Observable implements Observer {

    private AbstractDeck deck;
    private DiscardPile discardPile;
    private MovableCard movable;
    
    /**
     * Create a deck with all cards in Card
     */
    private static AbstractDeck makeDeck() {
        AbstractDeck deck = new CompleteDeck();
        deck.shuffle();
        return deck;
    }
    
    /**
     *
     */
    private void createMovableCard() {
        if(movable != null) {
            movable.deleteObserver(this);
            movable = null;
        }
        if(!deck.isEmpty()) {
            movable = new MovableCard(deck.draw());
            movable.addObserver(this);
        }
    }
    
    /**
     * Create a new Draw with all 54 different cards in the deck once
     */
    public Draw() {
        deck = makeDeck();
        discardPile = new DiscardPile();
        createMovableCard();
    }
    
    /**
     * Getter for deck so it may be looked at without being changed
     */
    public AbstractDeck getDeck() {
        return deck;
    }
     
    /**
     * Observe the state of the discard pile without allowing other classes
     * access
     */
    public DiscardPile getDiscardPile() {
        return discardPile;
    }
    
    /**
     * Look at which card is movable
     */
    public MovableCard getMovableCard() {
        return movable;
    }
    
    
    /**
     * Draw a card and put it on the discard pile
     */
    public void move() {
        if(movable != null)
            discardPile.put(movable.getCard());
        createMovableCard();
        setChanged();
        notifyObservers();
    }
    
    /**
     * Put all cards back into the deck and shuffle it
     */
    public void reset() {
        deck = makeDeck();
        discardPile = new DiscardPile();
        createMovableCard();
        setChanged();
        notifyObservers();
    }
    
    /** 
     * If the movable card updates this updates too
     */
    @Override
    public void update(Observable observable, Object message) {
        setChanged();
        notifyObservers();
    }

}

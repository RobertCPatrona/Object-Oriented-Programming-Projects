package cardGame.game;

import cardGame.model.Card;

import java.util.Observable;
import java.util.Observer;

/**
 * Represents a card that may be moved between piles
 */
public class MovableCard extends Observable {
    
    private Card card;
    private int relativeX;
    private int relativeY;
    
    /**
     * Create a new movable card
     */
    public MovableCard(Card card) {
        this.card = card;
        relativeX = 0;
        relativeY = 0;
    }
    
    /**
     * Get the card this card is based on
     */    
    public Card getCard() {
        return card;
    }
    
    /**
     * Change the number of pixels this card has been moved
     */
    public void setRelativeX(int relativeX) {
        this.relativeX = relativeX;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Change the number of pixels this card has been moved
     */
    public void setRelativeY(int relativeY) {
        this.relativeY = relativeY;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Get the number of pixels in X this card has been moved
     */
    public int getRelativeX() {
        return relativeX;
    }
    
    /**
     * Get the number of pixels in Y this card has been moved
     */
    public int getRelativeY() {
        return relativeY;
    }
    
}

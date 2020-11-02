package cardGame.model;

import cardGame.util.Emptiable;
import cardGame.util.Sized;

import java.util.Stack;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Pile of cards which has all cards open
 */
public class DiscardPile implements Emptiable, Sized, Iterable<Card>  {

    private Stack<Card> pile;
    
    /**
     * Create a new empty discard pile
     */
    public DiscardPile() {
        pile = new Stack<>();
    }
    
    /**
     * Put a card on the discard pile
     */
    public void put(Card card) {
        pile.push(card);
    }
    
    /**
     * Remove all cards from this discard pile
     */
    public Stack<Card> emptyPile() {
        Stack<Card> retVal = pile;
        pile = new Stack<>();
        return retVal;
    }
    
    /**
     * Returns the top card of the discard pile, or null if none is present
     */
    public Card top() {
        if(!isEmpty())
            return pile.peek();
        return null;
    }
    
    /**
     * Allows iterating over this discard pile without changing it
     * Does not support remove, so will throw an UnsuportedOperationException
     */
    private class ConcreteDiscardPileIterator implements Iterator<Card> {
        
        private ListIterator<Card> backing;
        
        /**
         * Create an iterator for this immutable discard pile using the 
         * iterator of the DiscardPile it protects
         */
        public ConcreteDiscardPileIterator() {
            backing = pile.listIterator(0);
        }
        
        /**
         * Find the next card in this discard pile
         */
        @Override
        public Card next() {
            return backing.next();
        }
        
        /**
         * Check if all cards have been looked at.
         */
        @Override
        public boolean hasNext() {
            return backing.hasNext();
        }
        
        /**
         * Removes a card from this discard pile
         */
        @Override
        public void remove() {
            backing.remove();
        }
    }
    
    /**
     * Returns an iterator which visits the cards in this discard pile
     * from BOTTOM to TOP, not from top to bottom.
     */
    @Override
    public Iterator<Card> iterator() {
        return new ConcreteDiscardPileIterator();
    }
    
    /**
     * Check the number of cards in this pile
     */
    @Override
    public int size() {
        return pile.size();
    }
    
    /**
     * Check if there are any cards left in this pile.
     */
    @Override
    public boolean isEmpty() {
        return pile.isEmpty();
    }
    
    /**
     * Remove all the cards in this pile
     */
    @Override
    public void empty() {
        pile.clear();
    }

}

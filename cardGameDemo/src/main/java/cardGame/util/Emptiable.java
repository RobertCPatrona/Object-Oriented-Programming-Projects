package cardGame.util;

/**
 * Represents types that can possibly be empty
 */
public interface Emptiable {
    /**
     * Check if there are any items in this Emptiable.
     */
    public boolean isEmpty();
    
    /**
     * Make this structure empty such that isEmpty() return true
     */
    public void empty();
}

package cardGame.model;

/**
 * Represents the kinds of playing cards found in ubiquitous 54-card decks
 *
 * WARNING: enum types implement toString by default. This information is
 * used to load textures in view. If you override toString you may break
 * the texture loading mechanism.
 */
public enum Card {
    
    //Hearts
    ACE_HEARTS      (Face.ACE,   Suit.HEARTS),
    TWO_HEARTS      (Face.TWO,   Suit.HEARTS),
    THREE_HEARTS    (Face.THREE, Suit.HEARTS),
    FOUR_HEARTS     (Face.FOUR,  Suit.HEARTS),
    FIVE_HEARTS     (Face.FIVE,  Suit.HEARTS),
    SIX_HEARTS      (Face.SIX,   Suit.HEARTS),
    SEVEN_HEARTS    (Face.SEVEN, Suit.HEARTS),
    EIGHT_HEARTS    (Face.EIGHT, Suit.HEARTS),
    NINE_HEARTS     (Face.NINE,  Suit.HEARTS),
    TEN_HEARTS      (Face.TEN,   Suit.HEARTS),
    JACK_HEARTS     (Face.JACK,  Suit.HEARTS),
    QUEEN_HEARTS    (Face.QUEEN, Suit.HEARTS),
    KING_HEARTS     (Face.KING,  Suit.HEARTS),
    
    //Diamonds
    ACE_DIAMONDS    (Face.ACE,   Suit.DIAMONDS),
    TWO_DIAMONDS    (Face.TWO,   Suit.DIAMONDS),
    THREE_DIAMONDS  (Face.THREE, Suit.DIAMONDS),
    FOUR_DIAMONDS   (Face.FOUR,  Suit.DIAMONDS),
    FIVE_DIAMONDS   (Face.FIVE,  Suit.DIAMONDS),
    SIX_DIAMONDS    (Face.SIX,   Suit.DIAMONDS),
    SEVEN_DIAMONDS  (Face.SEVEN, Suit.DIAMONDS),
    EIGHT_DIAMONDS  (Face.EIGHT, Suit.DIAMONDS),
    NINE_DIAMONDS   (Face.NINE,  Suit.DIAMONDS),
    TEN_DIAMONDS    (Face.TEN,   Suit.DIAMONDS),
    JACK_DIAMONDS   (Face.JACK,  Suit.DIAMONDS),
    QUEEN_DIAMONDS  (Face.QUEEN, Suit.DIAMONDS),
    KING_DIAMONDS   (Face.KING,  Suit.DIAMONDS),
    
    //Clubs
    ACE_CLUBS       (Face.ACE,   Suit.CLUBS),
    TWO_CLUBS       (Face.TWO,   Suit.CLUBS),
    THREE_CLUBS     (Face.THREE, Suit.CLUBS),
    FOUR_CLUBS      (Face.FOUR,  Suit.CLUBS),
    FIVE_CLUBS      (Face.FIVE,  Suit.CLUBS),
    SIX_CLUBS       (Face.SIX,   Suit.CLUBS),
    SEVEN_CLUBS     (Face.SEVEN, Suit.CLUBS),
    EIGHT_CLUBS     (Face.EIGHT, Suit.CLUBS),
    NINE_CLUBS      (Face.NINE,  Suit.CLUBS),
    TEN_CLUBS       (Face.TEN,   Suit.CLUBS),
    JACK_CLUBS      (Face.JACK,  Suit.CLUBS),
    QUEEN_CLUBS     (Face.QUEEN, Suit.CLUBS),
    KING_CLUBS      (Face.KING,  Suit.CLUBS),
    
    //Spades
    ACE_SPADES      (Face.ACE,   Suit.SPADES),
    TWO_SPADES      (Face.TWO,   Suit.SPADES),
    THREE_SPADES    (Face.THREE, Suit.SPADES),
    FOUR_SPADES     (Face.FOUR,  Suit.SPADES),
    FIVE_SPADES     (Face.FIVE,  Suit.SPADES),
    SIX_SPADES      (Face.SIX,   Suit.SPADES),
    SEVEN_SPADES    (Face.SEVEN, Suit.SPADES),
    EIGHT_SPADES    (Face.EIGHT, Suit.SPADES),
    NINE_SPADES     (Face.NINE,  Suit.SPADES),
    TEN_SPADES      (Face.TEN,   Suit.SPADES),
    JACK_SPADES     (Face.JACK,  Suit.SPADES),
    QUEEN_SPADES    (Face.QUEEN, Suit.SPADES),
    KING_SPADES     (Face.KING,  Suit.SPADES),
    
    //Jokers
    BLACK_JOKER     (Face.JOKER, Colour.RED),
    RED_JOKER       (Face.JOKER, Colour.BLACK);
    
    /**
     * Represents the faces a card can have
     */
    public enum Face {
        ACE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        JOKER;
    }
    
    /**
     * The colours a card can have
     */
    public enum Colour {
        RED,
        BLACK;
    }
    
    /**
     * The suits a card can be in. Each of them has a colour.
     */
    public enum Suit {
        HEARTS (Colour.RED),
        DIAMONDS (Colour.RED),
        CLUBS (Colour.BLACK),
        SPADES (Colour.BLACK);
        
        private final Colour colour;
        
        /**
         * Create a new suit with the appropriate colour
         */
        private Suit(Colour colour) {
            this.colour = colour;
        }
        
        /**
         * Get the colour of this suit.
         */
        public Colour getColour() {
            return colour;
        }
    }
    
    private final Face face;
    private final Suit suit;
    private final Colour colour;
    
    /**
     * Create a new card with the given face and suit
     */
    private Card(Face face, Suit suit) {
        this.face = face;
        this.suit = suit;
        this.colour = suit.getColour();
    }
    
    /**
     * Joker constructor since Jokers don't belong to a suit
     */
    private Card(Face face, Colour colour) {
        this.face = face;
        this.suit = null;
        this.colour = colour;
    }
    
    /**
     * Get the face of this card
     */
    public Face getFace() {
        return face;
    }
    
    /**
     * Get the suit of this card (which might be null if getFace() is JOKER)
     */
    public Suit getSuit() {
        return suit;
    }
    
    /**
     * The colour of this card
     */
    public Colour getColour() {
        return colour;
    }

}

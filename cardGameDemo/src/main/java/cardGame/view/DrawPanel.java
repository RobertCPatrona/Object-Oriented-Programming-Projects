package cardGame.view;

import cardGame.model.Card;

import cardGame.game.Draw;
import cardGame.game.MovableCard;

import javax.swing.JPanel;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.util.Observer;
import java.util.Observable;

/**
 * View of Draw
 */
public class DrawPanel extends JPanel implements Observer {

    private static final int CARD_SPACING = 2; //pixels
    private static final int Y_OFFSET = Card.values().length * CARD_SPACING;
    
    private Draw draw;
    
    private int movableX;
    private int movableY;
    
    /**
     * Get the number of pixels in X this card has been moved
     */
    public int getMovableX() {
        return movableX;
    }
    
    /**
     * Get the number of pixels in Y this card has been moved
     */
    public int getMovableY() {
        return movableY;
    }
    
    /**
     * Create a new DrawPanel
     */
    public DrawPanel(Draw draw) {
        this.draw = draw;
        draw.addObserver(this);
        setBackground(new Color(0x7E, 0x35, 0x4D));
        setVisible(true);
        setOpaque(true);
    }
    
    public boolean inDiscardArea(Point point) {
        return point.getX() > getWidth() / 2;
    }
    
    /**
     * Paint the areas in which deck and discard pile can be found
     */
    private void paintAreas(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawRect(0, 0, getWidth() / 2, getHeight() - 1);
        g.drawString("Deck Area", getWidth() / 4, 10);
        g.drawRect(getWidth() / 2, 0, getWidth() / 2 - 1, getHeight() - 1);
        g.drawString("Discard Area", 3 * (getWidth() / 4), 10);
        g.setColor(Color.BLACK);
    }
    
    /**
     * Get the scaled spacing between edges and cards
     */
    private int getSpacing() {
        return (int) ((getHeight() * 20) / 600.0);
    }
    
    /**
     * Get the scaled width of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */
    public int cardWidth() {
        if((getHeight() * 600.0) / (getWidth() * 436.0) <= 1.0)
            return (int) ((cardHeight() * 436.0) / 600.0);
        return (getWidth() - getSpacing() * 3 - 2 * Card.values().length) / 2;
    }
    
    /**
     * Get the scaled height of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */
    public int cardHeight() {
        if((getHeight() * 600.0) / (getWidth() * 436.0) > 1.0)
            return (int) ((cardWidth() * 600.0) / 436.0);
        return (getHeight() - getSpacing() * 2 - 2 * Card.values().length);
    }
    
    /**
     * Draw the deck
     */
    private void paintDeck(Graphics g) {
        int depth;
        for(depth = 0; depth < draw.getDeck().size(); ++depth) {
            int posX = getSpacing() + depth;
            int posY = getSpacing() + Y_OFFSET - CARD_SPACING * depth;
            g.drawImage( CardBackTextures.getTexture(CardBack.CARD_BACK_BLUE)
                       , posX, posY, cardWidth(), cardHeight(), this);
            g.drawRect(posX, posY, cardWidth(), cardHeight());
        }
        MovableCard dependency = draw.getMovableCard();
        if(dependency != null) {
            movableX = getSpacing() + depth + dependency.getRelativeX();
            movableY = getSpacing() + Y_OFFSET - CARD_SPACING * depth 
                     + dependency.getRelativeY();
            g.drawImage( CardBackTextures.getTexture(CardBack.CARD_BACK_BLUE)
                       , movableX, movableY, cardWidth(), cardHeight(), this);
            g.drawRect(movableX, movableY, cardWidth(), cardHeight());
        }
    }
    
    /**
     * Draw the discard pile
     */
    private void paintDiscardPile(Graphics g) {
        int depth = 0;
        for(Card card : draw.getDiscardPile()) {
            int posX = getWidth() - getSpacing() - cardWidth() 
                     + depth - Card.values().length;
            int posY = getSpacing() + Y_OFFSET - CARD_SPACING * depth;
            g.drawImage( CardTextures.getTexture(card)
                       , posX, posY, cardWidth(), cardHeight(), this);
            g.drawRect(posX, posY, cardWidth(), cardHeight());
            ++depth;
        }
    }
    
    /**
     * Paint the items that this class alone is responsible for.
     * 
     * This method is part of a template method that calls  
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintAreas(g);
        paintDiscardPile(g);
        paintDeck(g);
    }
    
    /**
     * Tell this DrawPanel that the object it displays has changed
     */
    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }

}

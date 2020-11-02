package cardGame.controller;

import cardGame.game.Draw;
import cardGame.view.DrawPanel;

import javax.swing.event.MouseInputAdapter;

import java.awt.event.MouseEvent;

/**
 * Implements the ability to drag the top card of the deck to the discard
 * area of a drawpanel
 */
public class CardDragger extends MouseInputAdapter {

    private Draw draw;
    private DrawPanel panel;
    
    private boolean selected;
    private int startX;
    private int startY;
    
    /**
     * Create a new card dragger that receives mouse events from the DrawPanel
     * supplied to this constructor
     */
    public CardDragger(Draw draw, DrawPanel panel) {
        this.draw = draw;
        this.panel = panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        selected = false;
    }
    
    /**
     * If the mouse button is pressed in the area where the top card is
     * drawn (obviously a lack of drawable cards makes this impossible)
     * that card is 'selected' so it can be dragged.
     */
    @Override
    public void mousePressed(MouseEvent event) {
        if(draw.getMovableCard() != null) {
            if( event.getX() > panel.getMovableX() &&
                event.getX() < panel.getMovableX() + panel.cardWidth() &&
                event.getY() > panel.getMovableY() &&
                event.getY() < panel.getMovableY() + panel.cardHeight()
              ) {
                selected = true;
                startX = event.getX();
                startY = event.getY();
            }
        }
    }
    
    /**
     * When the top card is released with the mouse in the discard square,
     * the card is moved.
     *
     * TODO: detect action
     * TODO: fire action
     */
    @Override
    public void mouseReleased(MouseEvent event) {
        if(selected) {
            if(panel.inDiscardArea(event.getPoint()))
                draw.move();
            else {
                draw.getMovableCard().setRelativeX(0);
                draw.getMovableCard().setRelativeY(0);
            }
        }
        selected = false;
    }
    
    /**
     * If a card is selected it is moved relative to the positions the mouse
     * was first pressed.
     */
    @Override
    public void mouseDragged(MouseEvent event) {
        if(selected) {
            draw.getMovableCard().setRelativeX(event.getX() - startX);
            draw.getMovableCard().setRelativeY(event.getY() - startY);
        }
    }

}

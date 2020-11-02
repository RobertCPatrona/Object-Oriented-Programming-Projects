package controller;

import model.Player;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import java.util.Observer;
import java.util.Observable;

/**
 * Represents an action made to draw a card.
 */
public class HitAction extends AbstractAction implements Observer {

    private Player player;
    private ButtonBar bar;

    /**
     * Makes sure the availability of the action reflects the availability of
     * the resource it acts on, namely, draw.
     */
    private void fixEnabled() {
        if (player.getCurrentDeck().getDeckSum()>100) {
            bar.setVisible(false);
        }
    }

    /**
     * Creates a new action to draw a card.
     */
    public HitAction(Player player, ButtonBar bar) {
        super("Draw a card");
        this.player = player;
        this.bar = bar;
        player.addObserver(this);
        fixEnabled();
    }

    /**
     * Draws a card
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Action is called");
        player.move();
    }
    
    /**
     * Since availability of this action depends on the state of the 
     * resources it itself depends on, this action verifies
     * after every update of draw if it can still be performed.
     */
    @Override
    public void update(Observable observed, Object message) {
        fixEnabled();
    }
    
}

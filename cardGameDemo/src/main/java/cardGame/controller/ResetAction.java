package cardGame.controller;

import cardGame.game.Draw;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Represents an action made to shuffle all cards back into the deck. Although
 * useless on an empty discard pile, this action is always available.
 */
public class ResetAction extends AbstractAction {

    private Draw draw;

    /**
     * Creates a new action to shuffle all cards back into the deck
     */
    public ResetAction(Draw draw) {
        super("Shuffle");
        this.draw = draw;
    }

    /**
     * Draws a card
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        draw.reset();
    }
    
}

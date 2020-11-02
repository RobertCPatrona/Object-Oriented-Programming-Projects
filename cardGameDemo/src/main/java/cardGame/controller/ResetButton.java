package cardGame.controller;

import cardGame.game.Draw;

import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;

/**
 * Button that shuffles all cards into the deck. It uses the Action API to 
 * perform its action which means that this is merely a default configuration 
 * for this button.
 */
public class ResetButton extends JButton {
    
    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
        setToolTipText("Shuffle all cards back into the deck");
    }
    
    /**
     * Create a reset button
     */
    public ResetButton(Draw draw) {
        super(new ResetAction(draw));
        setButtonProperties();
    }

}

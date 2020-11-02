package controller;

import model.Bank;

import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;

/**
 * Button that shuffles all cards into the deck. It uses the Action API to 
 * perform its action which means that this is merely a default configuration 
 * for this button.
 */
public class PassButton extends JButton {

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
        setToolTipText("End the turn of the player.");
    }
    
    /**
     * Create a reset button
     */
    public PassButton(Bank bank, ButtonBar bar) {
        super(new PassAction(bank, bar));
        setButtonProperties();
    }

}

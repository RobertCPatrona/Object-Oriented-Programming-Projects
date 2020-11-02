package grapheditor.controller;

import grapheditor.model.GraphModel;
import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * NewNodeButton is a button used for making a new node. It uses the action
 * defined in the 'NewNodeAction' AbstractAction to make the node.
 * */

public class NewNodeButton extends JButton {

    /* Sets basic button properties when the button is constructed.
     * 'ALT + N' can be used as a shortcut for this button.
     * */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_N);
        setToolTipText("Create Node");
    }

    /* The button is constructed with 'NewNodeAction' and its properties are set. */
    public NewNodeButton(GraphModel graph) {
        super(new NewNodeAction(graph));
        setButtonProperties();
    }
}

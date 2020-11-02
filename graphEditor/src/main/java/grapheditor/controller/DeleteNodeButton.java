package grapheditor.controller;

import grapheditor.model.GraphModel;
import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * DeleteNodeButton is a button used for deleting a node. It uses the action
 * defined in the 'DeleteNodeAction' AbstractAction.
 * */
public class DeleteNodeButton extends JButton {

    /* Sets basic button properties when the button is constructed.
     * 'ALT + D' can be used as a shortcut for this button.
     * */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("Delete Node");
    }

    /* The button is constructed with 'DeleteNodeAction' and its properties are set. */
    public DeleteNodeButton(GraphModel graph) {
        super(new DeleteNodeAction(graph));
        setButtonProperties();
    }
}

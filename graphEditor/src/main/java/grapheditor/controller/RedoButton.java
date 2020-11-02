package grapheditor.controller;

import grapheditor.model.GraphModel;
import grapheditor.view.GraphPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * RedoButton is a button used for redo-ing the last edit. It uses the action
 * defined in the 'RedoAction' AbstractAction to redo the last edit. The UndoManager
 * in the main model is used to redo edits.
 * */

public class RedoButton extends JButton {

    /* Sets basic button properties when the button is constructed.
     * 'ALT + Y' can be used as a shortcut for this button.
     * */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_Y);
        setToolTipText("Redo action");
    }

    /* The button is constructed with 'RedoAction' and its properties are set. */
    public RedoButton(GraphModel graph, GraphPanel panel) {
        super(new RedoAction(graph,panel));
        setButtonProperties();
    }
}

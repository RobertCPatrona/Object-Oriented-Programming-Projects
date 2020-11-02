package grapheditor.controller;

import grapheditor.model.GraphModel;
import grapheditor.view.GraphPanel;
import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * UndoButton is a button used for undo-ing the last edit. It uses the action
 * defined in the 'UndoAction' AbstractAction to undo the last edit. The UndoManager
 * in the main model is used to undo edits.
 * */

public class UndoButton extends JButton {

    /* Sets basic button properties when the button is constructed.
     * 'ALT + Z' can be used as a shortcut for this button.
     * */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_Z);
        setToolTipText("Undo action");
    }
    /* The button is constructed with 'UndoAction' and its properties are set. */
    public UndoButton(GraphModel graph, GraphPanel panel) {
        super(new UndoAction(graph,panel));
        setButtonProperties();
    }
}

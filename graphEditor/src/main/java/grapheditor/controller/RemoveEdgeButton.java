package grapheditor.controller;

import grapheditor.model.GraphModel;
import grapheditor.view.GraphPanel;
import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * RemoveEdgeButton is a button used for removing an edge that is specified by user.
 * It uses the action defined in the 'RemoveEdgeAction' AbstractAction to remove
 * the edge.
 * */

public class RemoveEdgeButton extends JButton {

    /* Sets basic button properties when the button is constructed.
     * 'ALT + R' can be used as a shortcut for this button.
     * */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_R);
        setToolTipText("Remove Edge");
    }

    /* The button is constructed with 'RemoveEdgeButton' and its properties are set. */
    public RemoveEdgeButton(GraphModel graph, GraphPanel panel) {
        super(new RemoveEdgeAction(graph, panel));
        setButtonProperties();
    }

}

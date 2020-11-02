package grapheditor.controller;

import grapheditor.model.GraphModel;
import grapheditor.view.GraphPanel;
import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * This AddEdgeButton is a button for adding new edges.
 * It is constructed by using the 'AddEdgeAction' AbstractAction class.
 * */
public class AddEdgeButton extends JButton{

    /* Sets basic button properties when the button is constructed.
    * 'ALT + A' can be used as a shortcut for this button.
    * */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_A);
        setToolTipText("Add Edge");
    }

    /*The button is constructed via the JButton (parent class) using the
    * 'AddEdgeAction' abstract action class.
    * */
    public AddEdgeButton(GraphModel graph, GraphPanel panel) {
        super(new AddEdgeAction(graph,panel));
        setButtonProperties();
    }
}

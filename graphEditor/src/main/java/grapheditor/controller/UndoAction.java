package grapheditor.controller;

import grapheditor.model.GraphModel;
import grapheditor.view.GraphPanel;
import javax.swing.*;
import javax.swing.undo.CannotUndoException;
import java.awt.event.ActionEvent;

/**
 *  UndoAction is an abstract action that performs the action
 *  of undo-ing a series of edits.
 *  */

public class UndoAction extends AbstractAction {

    private GraphModel graph;
    private GraphPanel panel;

    /*The 'UndoAction' is constructed using 'AbstractAction' parent class.
     * The main model is used to access the UndoManager to undo edits.
     * The panel is used to display a warning if there are no more edits that
     * can be undone.
     * */
    public UndoAction(GraphModel graph, GraphPanel panel) {
        super("undo");
        this.graph = graph;
        this.panel = panel;
    }

    /*The actionPerformed is simply calling the main model's UndoManager
     * to undo the last edit. If there are no more edits that can be undone
     * the program gives a warning for the user.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            graph.getManager().undo();
        } catch (CannotUndoException cue) {
            JOptionPane.showMessageDialog(panel,"Nothing to undo","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
}

package grapheditor.controller;

import grapheditor.model.GraphModel;
import grapheditor.view.GraphPanel;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import java.awt.event.ActionEvent;

/**
 *  RedoAction is an abstract action that performs the action
 *  of redo-ing a series of edits.
 *  */
public class RedoAction extends AbstractAction {
    private GraphModel graph;
    private GraphPanel panel;

    /*The 'RedoAction' is constructed using 'AbstractAction' parent class.
    * The main model is used to access the UndoManager to redo edits.
    * The panel is used to display a warning if there are no more edits that
    * can be redone.
    * */
    public RedoAction(GraphModel graph, GraphPanel panel) {
        super("redo");
        this.graph = graph;
        this.panel = panel;
    }

    /*The actionPerformed is simply calling the main model's UndoManager
    * to redo the last edit. If there are no more edits that can be redone
    * the program gives a warning for the user.
    * */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            graph.getManager().redo();
        } catch (CannotRedoException cre) {
            JOptionPane.showMessageDialog(panel,"Nothing to redo","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
}

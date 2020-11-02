package grapheditor.controller;

import grapheditor.model.GraphModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * DeleteNodeAction is an AbstractAction that performs the
 * action of deleting a selected vertex.
 * */
public class DeleteNodeAction extends AbstractAction {

    private GraphModel graph;

    public DeleteNodeAction(GraphModel graph) {
        super("delete node");
        this.graph = graph;
    }


    /*The actionPerformed simply checks if there is a selected vertex and
    * removes that vertex from the ArrayList of vertices from the main model.
    * The selected vertex is set to null, after its removed from the ArrayList.
    * */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getSelected() != null){
            graph.removeVertex(graph.getSelected());
            graph.setSelected(null);
        }
        if(graph.getSelected() == null) {
            setEnabled(false);
        }

    }
}

package grapheditor.controller;

import grapheditor.model.GraphModel;
import grapheditor.model.GraphVertex;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *  NewNodeAction is an abstract action that performs the action
 *  of adding a new vertex into the graph.
 *  */
public class NewNodeAction extends AbstractAction {

    private GraphModel graph;

    public NewNodeAction(GraphModel graph) {
        super("new node");
        this.graph = graph;
    }

    /*The actionPerformed for this button involves creating a node and
    * receiving its name. Then the node is added to the main model. The size of
    * the node is defined by the length of its name.
    * */
    @Override
    public void actionPerformed(ActionEvent e) {
        String vertexName = JOptionPane.showInputDialog("Enter node name");
        if (vertexName != null) {
            GraphVertex v = new GraphVertex(vertexName, 80, 80, 70 + vertexName.length() * 6, 30);
            graph.addVertex(v);
        }
    }
}

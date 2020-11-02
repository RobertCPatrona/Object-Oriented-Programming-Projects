package grapheditor.controller;
import grapheditor.model.GraphEdge;
import grapheditor.model.GraphModel;
import grapheditor.view.GraphPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *  AddEdge action is an abstract action that performs the action
 *  of adding a new edge into the graph.
 *  */
public class AddEdgeAction extends AbstractAction {

    private GraphModel graph;
    private GraphPanel panel;

    /* Constructs the abstract action that is used for adding edges
     * Uses the graph model to add edges and panel is used to display
     * information regarding the action.
     * */
    public AddEdgeAction(GraphModel graph, GraphPanel panel) {
        super("add edge");
        this.graph = graph;
        this.panel = panel;
    }

    /* The actionPerformed method inherited from the AbstractAction parent
    *  class describes a method for adding an edge for two vertices.
    *  Since adding an edge requires two vertices, we use a place holder edge
    *  until the user selects the second vertex. The a place holder edge
    *  is made by the vertex selected by the user. The edge is fully made when
    *  the user specifies the second vertex. Panel is used to notify (show dialog)
    *  the user when there is no selected vertex from the edge.
    * */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getSelected() != null){
            GraphEdge tempEdge = new GraphEdge(graph.indexOfV(graph.getSelected()), graph.indexOfV(graph.getSelected()));
            graph.addEdge(tempEdge);
            graph.setAddEdgeBoolean(true);
        }
        if(graph.getSelected() == null) {
            JOptionPane.showMessageDialog(panel,"No selected node");
            setEnabled(false);
        }
        panel.repaint();
    }
}

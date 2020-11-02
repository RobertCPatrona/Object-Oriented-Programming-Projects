package grapheditor.controller;

import grapheditor.model.GraphEdge;
import grapheditor.model.GraphModel;
import grapheditor.model.GraphVertex;
import grapheditor.view.GraphPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *  RemoveEdgeAction is an abstract action that performs the action
 *  of removing a specified edge from the graph.
 *  */
public class RemoveEdgeAction extends AbstractAction {

    private GraphModel graph;
    private GraphPanel panel;

    /*The 'RemoveEdgeAction' is constructed using 'AbstractAction' parent class.
     * The main model is used to find and remove the specified edge.
     * The panel is used to specify the second vertex of the edge that is to be removed.
     * */
    public RemoveEdgeAction(GraphModel graph, GraphPanel panel) {
        super("remove edge");
        this.graph = graph;
        this.panel = panel;
    }

    /* To remove an edge, at least one node must be selected first and from that
    *  the user specifies the second vertex by using its name.
    *  The edge is defined by two vertex indices, the vertices are found inside the
    *  for loops and the edge is removed once the two vertices are found. The button
    *  is disabled if there is no selected node. The removed edge is kept inside the
    *  the 'removeEdgeEdit' that will be later used to Undo a removal of an edge.
    * */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graph.getSelected() != null) {
            String nameVertex = JOptionPane.showInputDialog("An edge connects two vertices. To delete an edge, write the name " +
                    "of the vertex that is connected to " + graph.getSelected().getName() + ".\n" + "The edge connecting the selected vertex " +
                    "and the inputted vertex will be deleted.\n" + "The input name is case sensitive.");

            for (GraphVertex v : graph.getVertices()) {
                if (v.getName().equals(nameVertex)) {
                    for (GraphEdge edge : graph.getEdges()) {

                        int firstVertexIndex = graph.indexOfV(graph.getSelected());
                        int secondVertexIndex = graph.indexOfV(v);

                        if (edge.getVertexBIndex() == secondVertexIndex && edge.getVertexAIndex() == firstVertexIndex) {
                            graph.removeEdgeEdit(edge);
                            graph.removeEdge(edge);
                            return;
                        }
                        if (edge.getVertexAIndex() == secondVertexIndex && edge.getVertexBIndex() == firstVertexIndex) {
                            graph.removeEdgeEdit(edge);
                            graph.removeEdge(edge);
                            return;
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(panel, "Please enter the name of a valid vertex that has an edge with " + graph.getSelected().getName());
        } else {
            JOptionPane.showMessageDialog(panel,"No vertex selected","Warning",JOptionPane.WARNING_MESSAGE);
            setEnabled(false);
        }
    }
}
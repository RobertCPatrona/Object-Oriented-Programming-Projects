package grapheditor.controller;

import grapheditor.model.GraphModel;
import grapheditor.model.GraphVertex;
import grapheditor.view.GraphFrame;
import javax.swing.*;

public class GraphController {

    private GraphModel graph;

    public GraphController(GraphModel graph) {
        this.graph = graph;
    }

    public void saveModel(String filepath) {
        GraphModel.save(graph, filepath);
    }

    public void loadModel(String filepath) {
        this.graph.loadInPlace(filepath);
    }

    public void renameVertex(String name) {
        String oldName =graph.getSelected().getName();
        graph.getSelected().setName(name);
        graph.getSelected().setSize(graph.getSelected().getIntWidth() + name.length() * 5, graph.getSelected().getIntHeight());
        graph.changed();
        this.createRenameEdit(graph.getSelected(), oldName, name);
    }

    public void resizeVertex(JTextField xField, JTextField yField) {
        int x = Integer.parseInt(xField.getText());
        int y = Integer.parseInt(yField.getText());
        graph.resizeEdit(graph.getSelected(), graph.getSelected().getIntWidth(), graph.getSelected().getIntHeight(), x, y);
        graph.getSelected().setSize(x, y);
        graph.changed();
    }


    public void copyVertex() {
        GraphVertex copiedV = new GraphVertex(graph.getSelected().getName(), 110, 110, graph.getSelected().getIntWidth(), graph.getSelected().getIntHeight());
        graph.setCopyVertex(copiedV);
    }

    public void pasteVertex(int pasteOffset) {
        GraphVertex pastedV = new GraphVertex(graph.getCopyVertex().getName(), graph.getCopyVertex().getIntX()+pasteOffset*10, graph.getCopyVertex().getIntY()+pasteOffset*10, graph.getCopyVertex().getIntWidth(), graph.getCopyVertex().getIntHeight());
        graph.addVertex(pastedV);
    }

    public void setSelected(GraphVertex v) {
        graph.setSelected(v);
    }

    public void setVertexLocation(int Dx, int Dy) {
        graph.getSelected().setLocation(Dx, Dy);
        graph.changed();
    }

    public void setSecondVertex() {
        graph.getEdges().get(graph.getEdges().size() - 1).setVertexBIndex(graph.getVertices().indexOf(graph.getSelected()));
        graph.addEdgeEdit(graph.getEdges().get(graph.getEdges().size()-1));
        graph.setAddEdgeBoolean(false);
    }

    public void stopEdgeAdding() {
        this.graph.getEdges().remove(this.graph.getEdges().size() - 1);
        this.graph.setAddEdgeBoolean(false);

    }

    public void addWindowsFunction() {
        GraphFrame frame2 = new GraphFrame(graph, this);
        frame2.setVisible(true);
        graph.addObserver(frame2);
    }

    public void createMoveEdit(int x, int y, GraphVertex selected) {
        graph.moveVertex(graph.indexOfV(selected), x, y);
    }

    public void createRenameEdit(GraphVertex vertex, String oldName, String newName) {
        graph.renameEdit(vertex, oldName, newName);
    }
}

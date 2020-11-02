package grapheditor.model;

import javax.swing.undo.AbstractUndoableEdit;
import java.util.ArrayList;

public class RemoveVEdit extends AbstractUndoableEdit {
    private GraphModel model;
    private GraphVertex vertex;
    private ArrayList<GraphVertex> connections;

    public RemoveVEdit(GraphModel model, GraphVertex vertex, ArrayList<GraphVertex> others) {
        this.connections = others;
        this.vertex = vertex;
        this.model = model;
    }

    @Override
    public void undo() {
        model.addVertex(vertex, false);
        int index = model.indexOfV(vertex);
        for (GraphVertex other : this.connections) {
            int iOther = model.indexOfV(other);
            model.addEdge(new GraphEdge(index, iOther));
        }
    }

    @Override
    public void redo() {
        model.removeVertex(vertex, false);
    }
}

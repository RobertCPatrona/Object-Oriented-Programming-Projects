package grapheditor.model;

import javax.swing.undo.AbstractUndoableEdit;
import java.util.ArrayList;

/**
 * RemoveVertexEdit is AbstractUndoableEdit that is used for undo-ing
 * or redo-ing edits that involve the removal of nodes. This edit is added
 * onto the UndoManager that is present in the main model.
 * */
public class RemoveVertexEdit extends AbstractUndoableEdit {
    private GraphModel model;
    private GraphVertex removedVertex;
    private ArrayList<GraphVertex> connectedVertices;

    /*RemoveVertexEdit is constructed from the graph and removed vertex.
    * Since the removed vertex can have edges which are removed with
    * the vertex, an array list of connected vertices is required when
    * undo/redo action is performed. */
    public RemoveVertexEdit(GraphModel model, GraphVertex removedVertex, ArrayList<GraphVertex> connectedVertices) {
        this.removedVertex = removedVertex;
        this.model = model;
        this.connectedVertices = connectedVertices;
    }

    /*The removed vertex is added back to the graph. A false flag is sent
    * since we do not want to have this 'addVertex' to be undoable. The
    * addition of this vertex should not be added onto the UndoManager.
    * The edges removed along with the node are added back within the
    * for-loop.
    * */
    @Override
    public void undo() {
        model.addVertex(removedVertex, false);
        int index = model.indexOfV(removedVertex);
        for (GraphVertex otherVertex : this.connectedVertices) {
            int otherVertexIndex = model.indexOfV(otherVertex);
            model.addEdge(new GraphEdge(index, otherVertexIndex));
        }
    }

    /*With redo the vertex is just simply removed. A false flag is sent
    * again to avoid putting this redo edit onto the UndoManager.
    * */
    @Override
    public void redo() {
        model.removeVertex(removedVertex, false);
    }
}

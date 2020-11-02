package grapheditor.model;

import javax.swing.undo.AbstractUndoableEdit;

/**
 * RemoveEdgeEdit is AbstractUndoableEdit that is used for undo-ing
 * or redo-ing edits that involve the removal of edges. This edit is
 * added onto the UndoManager that is present in the main model.
 * */
public class RemoveEdgeEdit extends AbstractUndoableEdit {

    private GraphModel model;
    private GraphVertex a;
    private GraphVertex b;
    private GraphEdge removedEdge;

    /* The undoable edit is constructed by the removed edge and main model.*/
    public RemoveEdgeEdit(GraphEdge e, GraphModel model) {
        this.model = model;
        this.a = model.getVertices().get(e.getVertexAIndex());
        this.b = model.getVertices().get(e.getVertexBIndex());
    }

    /* 'undo()' adds the removed edge back to the graph.
     * The 'removedEdge' edge is made from the two vertex indices of the
     * removed edge.*/
    @Override
    public void undo() {
        removedEdge = new GraphEdge(model.indexOfV(a), model.indexOfV(b));
        this.model.addEdge(removedEdge);
    }

    /* 'redo()' just removes the removed vertex from before. */
    @Override
    public void redo() {
        this.model.removeEdge(removedEdge);
    }

}

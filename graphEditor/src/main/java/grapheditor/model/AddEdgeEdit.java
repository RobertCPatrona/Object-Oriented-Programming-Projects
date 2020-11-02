package grapheditor.model;

import javax.swing.undo.AbstractUndoableEdit;

/**
 * AddEdgeEdit is AbstractUndoableEdit that is used for undo-ing
 * or redo-ing edits that involve adding edges. This edit is added
 * onto the UndoManager that is present in the main model.
 * */

public class AddEdgeEdit extends AbstractUndoableEdit {

    private GraphModel model;
    private GraphVertex a;
    private GraphVertex b;
    private GraphEdge addedEdge;

    /*The 'addedEdge' holds the edge that is removed during the
    * undo action and is added back to the graph when redo is
    * called.
    * */
    public AddEdgeEdit(GraphEdge e, GraphModel model) {
        this.model = model;
        this.addedEdge = e;
        this.a = model.getVertices().get(e.getVertexAIndex());
        this.b = model.getVertices().get(e.getVertexBIndex());
    }

    /* Undo removes the added edge from the main model*/
    @Override
    public void undo() {
        this.model.removeEdge(addedEdge);
    }

    /* Redo adds the edge back to the main model*/
    @Override
    public void redo() {
        addedEdge = new GraphEdge(model.indexOfV(a), model.indexOfV(b));
        this.model.addEdge(addedEdge);
    }

}

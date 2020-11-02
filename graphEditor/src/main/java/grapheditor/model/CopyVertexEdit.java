package grapheditor.model;

import javax.swing.undo.AbstractUndoableEdit;

/**
 * CopyVertexEdit is AbstractUndoableEdit that is used for undo-ing
 * or redo-ing edits that involve pasting copied nodes. In addition,
 * this class is used for undo-ing and redo-ing the creation of new
 * nodes. This edit is added onto the UndoManager that is present
 * in the main model.
 * */
public class CopyVertexEdit extends AbstractUndoableEdit {

    private GraphVertex v;
    private GraphModel model;

    public CopyVertexEdit(GraphVertex v, GraphModel model) {
        this.v = v;
        this.model = model;
    }

    /*The undo function just removes the pasted vertex that is  or
    * the new vertex that has been created by the user. When removing
    * this vertex, a false flag is used because we do not want an undo
    * edit for an undo edit. This false flag stops the removal of vertex
    * from being added to the UndoManager.
    * */
    @Override
    public void undo() {
        model.removeVertex(v, false);
        model.changed();
    }

    /*The redo function simply adds the vertex back to the main model.
    * We do not want to generate a redo for a redone action and therefore
    * we use a false flag to indicate this edit should not be added to
    * the UndoManager in the main model.
    * */
    @Override
    public void redo() {
        model.addVertex(v, false);
        model.changed();
    }
}

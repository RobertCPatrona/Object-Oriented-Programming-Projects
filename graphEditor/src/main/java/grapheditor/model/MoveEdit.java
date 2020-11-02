package grapheditor.model;

import javax.swing.undo.AbstractUndoableEdit;

/**
 * MoveEdit is AbstractUndoableEdit that is used for undo-ing or redo-ing edits
 * that involve the location of nodes. This edit is added onto the UndoManager
 * that is present in the main model.
 * */
public class MoveEdit extends AbstractUndoableEdit {

    private int oldX, oldY, newX, newY;
    private GraphModel model;
    private GraphVertex vertex;

    /* The old and new positions of vertex is used for undo-ing and redo-ing
    * the node's change in location.
    * */
    public MoveEdit(GraphVertex v, int oldX, int oldY, int newX, int newY, GraphModel model) {
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
        this.model = model;
        this.vertex = v;
    }

    /* The node is moved back to its old position */
    @Override
    public void undo() {
        vertex.setLocation(oldX, oldY);
        model.changed();
    }

    /* The node is moved into its new position */
    @Override
    public void redo() {
        vertex.setLocation(newX, newY);
        model.changed();
    }
}

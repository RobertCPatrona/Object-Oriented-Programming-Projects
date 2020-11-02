package grapheditor.model;

import javax.swing.undo.AbstractUndoableEdit;

/**
 * RenameVertexEdit is AbstractUndoableEdit that is used for undo-ing
 * or redo-ing edits that involve renaming nodes. This edit is added
 * onto the UndoManager that is present in the main model.
 * */
public class RenameVertexEdit extends AbstractUndoableEdit {

    private String oldName, newName;
    private GraphModel model;
    private GraphVertex vertex;

    /*RenameVertexEdit is constructed using the renamed vertex with its old
    * and new name and the graph. */
    public RenameVertexEdit(GraphVertex vertex, String oldName, String newName, GraphModel model) {
        this.vertex = vertex;
        this.oldName = oldName;
        this.newName = newName;
        this.model = model;
    }

    /*'oldName' is used to rename the vertex back to its original name.
    * Then, the graph notifies its observers because it has been changed.*/
    @Override
    public void undo() {
        System.out.println(oldName);
        vertex.setName(oldName);
        model.changed();
    }

    /*'newName' is used to redo the renaming action.
    * Then, the graph notifies its observers because it has been changed.*/
    @Override
    public void redo() {
        System.out.println(newName);
        vertex.setName(newName);
        model.changed();
    }
}

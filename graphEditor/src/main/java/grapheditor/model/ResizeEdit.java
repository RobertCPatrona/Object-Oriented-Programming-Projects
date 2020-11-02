package grapheditor.model;

import javax.swing.undo.AbstractUndoableEdit;

/**
 * ResizeEdit is AbstractUndoableEdit that is used for undo-ing
 * or redo-ing edits that involve resizing nodes. This edit is added
 * onto the UndoManager that is present in the main model.
 * */
public class ResizeEdit extends AbstractUndoableEdit {

    private int oldW, oldH, newW, newH;
    private GraphModel model;
    private GraphVertex vertex;

    /*The old and new widths and heights are used to construct this edit. */
    public ResizeEdit(GraphModel model, GraphVertex vertex, int oldW, int oldH, int newW, int newH) {
        this.oldW = oldW;
        this.oldH = oldH;
        this.newW = newW;
        this.newH = newH;
        this.model = model;
        this.vertex = vertex;
    }

    /*'undo()' simply sets the vertex back to its old size and all
    * the observers of graph are notified for these new changes. */
    @Override
    public void undo() {
        vertex.setSize(oldW, oldH);
        model.changed();
    }

    /*'redo()' simply sets the vertex to its new size and all
     * the observers of graph are notified for these new changes. */
    @Override
    public void redo() {
        vertex.setSize(newW, newH);
        model.changed();
    }
}

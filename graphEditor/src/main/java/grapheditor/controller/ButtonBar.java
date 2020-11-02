
package grapheditor.controller;

import grapheditor.model.GraphModel;
import grapheditor.view.GraphPanel;
import javax.swing.*;
import java.awt.*;
/**
 * This JPanel serves as a button bar for presenting all the
 * buttons in an organized manner. This bar is presented at the
 * top of the main panel below the menu bar.
 * */
public class ButtonBar extends JPanel {

    private DeleteNodeButton deleteButton;
    private AddEdgeButton addEdgeButton;
    private RemoveEdgeButton removeEdgeButton;


    /* The ButtonBar is constructed out of several buttons
    *  that are used for different actions. This bar contains 'deleteButton',
    *  'addEdgeButton' and 'removeEdgeButton' as private fields because
    *  these buttons need to disabled and enabled based on action events that
    *  involve 'mousePressed'. When mouse is pressed and a vertex is selected,
    *  these buttons are enabled and disabled otherwise.
    * */
    public ButtonBar(GraphModel graph, GraphPanel panel) {

        super(new FlowLayout(FlowLayout.LEFT));
        add(new NewNodeButton(graph));

        deleteButton = new DeleteNodeButton(graph);
        deleteButton.setEnabled(false);

        addEdgeButton = new AddEdgeButton(graph, panel);
        addEdgeButton.setEnabled(false);

        removeEdgeButton = new RemoveEdgeButton(graph, panel);
        removeEdgeButton.setEnabled(false);

        add(deleteButton);
        add(addEdgeButton);
        add(removeEdgeButton);
        add(new UndoButton(graph, panel));
        add(new RedoButton(graph,panel));

        setPreferredSize(new Dimension(30,30));
    }

    public DeleteNodeButton getDeleteButton() {
        return deleteButton;
    }

    public AddEdgeButton getAddEdgeButton() {
        return addEdgeButton;
    }

    public RemoveEdgeButton getRemoveEdgeButton() {
        return removeEdgeButton;
    }
}

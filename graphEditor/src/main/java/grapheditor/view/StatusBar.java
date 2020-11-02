package grapheditor.view;

import grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * StatusBar is a JLabel that presents status that is involved with the selected vertex and
 * the copied vertex.
 * */
public class StatusBar extends JLabel implements Observer {

    public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 16));
        setText("Nothing to report");
    }

    @Override
    public void update(Observable o, Object arg) {
        GraphModel graph = (GraphModel) arg;
        String toSet = "";
        if (graph.getSelected() != null) {
            toSet += "Name of selected vertex: " + graph.getSelected().getName();
        }
        if (graph.getCopyVertex() != null) {
            toSet +=  " Name of copied vertex: " + graph.getCopyVertex().getName();
        }
        if(toSet.equals("")) {
            toSet = "Nothing to report";
        }
        setText(toSet);
    }
}

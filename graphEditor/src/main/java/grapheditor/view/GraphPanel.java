package grapheditor.view;

import grapheditor.model.GraphEdge;
import grapheditor.model.GraphModel;
import grapheditor.model.GraphVertex;
import javax.swing.*;
import java.awt.*;

/**
 * GraphPanel is a JPanel that represents the main model in the view.
 * */
public class GraphPanel extends JPanel {

    private GraphModel model;

    /*The panel is constructed from the model it presents.*/
    public GraphPanel(GraphModel model) {
        this.model = model;
        this.setBackground(Color.lightGray);
    }

    public void setModel(GraphModel model) {
        this.model = model;
    }

    public GraphModel getModel() {
        return model;
    }

    /*Inherited from JPanel parent class and is used to paint the graph in the view.
    * If AddEdgeBoolean is true, the user is adding a new edge and therefore a place
    * holder line that follows the cursor is drawn. Once the user makes a selection
    * of the second vertex, the edge is drawn.
     * */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setStroke(new BasicStroke(2));

        if(model.getAddEdgeBoolean() && getMousePosition() != null && model.getSelected() != null) {
            try {
                g.drawLine((int) model.getSelected().getCenterX(), (int) model.getSelected().getCenterY(), (int) getMousePosition().getX(), (int) getMousePosition().getY());
            } catch (NullPointerException e) {
                /* The above code throws a NullPointer exception if the mouse is outside the panel, but does not crash.
                We catch this and continue once the user gets back into a panel. */
                g.drawString("Edge is out of bounds",this.getWidth()-125,this.getHeight()-10);
            }
        }

        /* Edges are drawn as lines */
        for (GraphEdge e : model.getEdges()) {
            g.setColor(Color.BLACK);
            GraphVertex vertexA = model.getVertices().get(e.getVertexAIndex());
            GraphVertex vertexB = model.getVertices().get(e.getVertexBIndex());

            int vertexAX = (int) vertexA.getCenterX();
            int vertexAY = (int) vertexA.getCenterY();
            int vertexBX = (int) vertexB.getCenterX();
            int vertexBY = (int) vertexB.getCenterY();
            g.drawLine(vertexAX, vertexAY, vertexBX, vertexBY);
        }

        /* Vertices are drawn as rectangles */
        for(GraphVertex v : model.getVertices()) {
            if(v == model.getSelected()) {
                g.setColor(Color.magenta);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(v.getIntX(), v.getIntY(), v.getIntWidth(), v.getIntHeight());
            g.setColor(Color.BLACK);
            g.drawRect(v.getIntX(), v.getIntY(), v.getIntWidth(), v.getIntHeight());
            Font f = new Font("serif", Font.PLAIN, 20);
            g.setFont(f);
            g.drawString(v.getName(), (int) v.getCenterX() - 12*(v.getName().length()/2), (int) v.getCenterY()+5);
        }

    }
}

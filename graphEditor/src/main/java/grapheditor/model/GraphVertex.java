package grapheditor.model;
import java.awt.Rectangle;

/**
 * GraphVertex is a rectangle that is presented as a node inside
 * the graph. GraphVertex has name, x, y position and height and
 * width.
 * */
public class GraphVertex extends Rectangle {

    private String name;

    /* Default constructor vertex is constructed at a default location and size.*/
    public GraphVertex() {
        super(80, 80, 70, 30);
        this.name = "";
    }

    /* Vertex constructor with all the required properties.*/
    public GraphVertex(String name, int x, int y, int width, int height) {
        super(x, y, width, height);
        name = name.replace(" ", "_");
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getIntX() { return (int) this.getX(); }
    public int getIntY() { return (int) this.getY(); }
    public int getIntWidth() { return (int) this.getWidth(); }
    public int getIntHeight() { return (int) this.getHeight(); }

    public String getName() {
        return name;
    }

    /* Vertex is written on to a file with format defined below. */
    public String toSaveString() {
        return this.name + " " + this.x + " " + this.y + " " + this.width + " " + this.height;
    }
}

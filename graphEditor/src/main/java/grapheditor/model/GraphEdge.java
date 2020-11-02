package grapheditor.model;

/**
 * GraphEdge is a class that defines an edge between two
 * vertices. This vertices are represented in terms of
 * their respective indices in the ArrayList of vertices
 * in the main model.
 * */
public class GraphEdge {

    private int vertexAIndex;
    private int vertexBIndex;

    /* GraphEdge is constructed from two vertex indices. */
    public GraphEdge(int vertexAIndex, int vertexBIndex) {
        this.vertexAIndex = vertexAIndex;
        this.vertexBIndex = vertexBIndex;
    }

    /* 'toSaveString' is used for writing the edge onto a file. */
    public String toSaveString() {
        return this.vertexAIndex + " " + this.vertexBIndex;
    }

    public void setVertexAIndex(int vertexAIndex) {
        this.vertexAIndex = vertexAIndex;
    }

    public void setVertexBIndex(int vertexBIndex) {
        this.vertexBIndex = vertexBIndex;
    }

    public int getVertexAIndex() {
        return vertexAIndex;
    }

    public int getVertexBIndex() {
        return vertexBIndex;
    }

}

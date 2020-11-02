package grapheditor.model;

import java.util.Observable;

import javax.swing.undo.UndoManager;
import java.io.*;
import java.util.ArrayList;

/**GraphModel is the main model that holds data from which the graph is drawn.
 * The main model holds an ArrayList of vertices and edges along with their
 * getters and setters. It also holds the node that has been copied and also
 * the selected node. All node manipulations are done using the selected node.
 * The 'addEdgeBoolean' is a flag that is used to indicate if an edge is in the
 * process of being added by the user. The UndoManager holds all the undoable
 * and redo-able edits performed on the model.
 * */
public class GraphModel extends Observable {

    private ArrayList<GraphVertex> vertices;
    private ArrayList<GraphEdge> edges;
    private GraphVertex selected;
    private UndoManager manager;
    private Boolean addEdgeBoolean;
    private GraphVertex copyVertex;

    /*The default 'GraphModel' constructor initializes all the required fields.
    * The 'addEdgeBoolean' is set to false by default since the user will not be
    * in a process adding an edge during the main model construction.*/
    public GraphModel() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.addEdgeBoolean = false;
        this.manager = new UndoManager();
    }

    /* 'GraphModel' is constructed from a file in 'graphToLoad'.
    * 'graphToLoad' holds the directory and filename for the graph to be
    *  loaded. */
    public GraphModel(String graphToLoad) {
        GraphModel loaded = GraphModel.load(graphToLoad);
        this.vertices = loaded.vertices;
        this.edges = loaded.edges;
        this.addEdgeBoolean = false;
        this.manager = new UndoManager();
    }

    /*'loadInPlace' loads the given graph inside the file path
    * Activated from JMenuItem 'load'*/
    public void loadInPlace(String filepath) {
        GraphModel newModel = GraphModel.load(filepath);
        this.vertices = newModel.getVertices();
        this.edges = newModel.getEdges();
        this.selected = newModel.getSelected();
        this.copyVertex = newModel.getCopyVertex();
        this.manager = new UndoManager();
        this.addEdgeBoolean = newModel.getAddEdgeBoolean();
        changed();
    }

    public void setCopyVertex(GraphVertex copyVertex) {
        this.copyVertex = copyVertex;
        changed();
    }

    public void setAddEdgeBoolean(Boolean newEdgeBoolean) {
        this.addEdgeBoolean = newEdgeBoolean;
        changed();
    }

    public void setSelected(GraphVertex selected) {
        this.selected = selected;
        changed();
    }

    public UndoManager getManager() {
        return manager;
    }

    public GraphVertex getCopyVertex() {
        return copyVertex;
    }

    public Boolean getAddEdgeBoolean() {
        return this.addEdgeBoolean;
    }

    public GraphVertex getSelected() {
        return this.selected;
    }

    public ArrayList<GraphVertex> getVertices() {
        return vertices;
    }

    public ArrayList<GraphEdge> getEdges() {
        return edges;
    }

    /*The main model is an observable class and the observers are notified whenever
    * the main model is changed inside the controller. */
    public void changed() {
        setChanged();
        notifyObservers(this);
    }

    public void addVertex(GraphVertex v) {this.addVertex(v, true);}

    public void addVertex (GraphVertex v, boolean addEdit) {
        this.vertices.add(v);
        if(addEdit) this.manager.addEdit(new CopyVertexEdit(v, this));
        changed();
    }

    public void addEdge (GraphEdge e) {
        this.edges.add(e);
        changed();
    }

    public void removeEdge (GraphEdge e) {
        this.edges.remove(e);
        changed();
    }

    /*Adds an abstract undoable edit called 'RemoveEdgeEdit' onto the UndoManager
    * of the main model. Used for undo-ing and redo-ing edits that involve removal
    * of edges.
    * */
    public void removeEdgeEdit(GraphEdge e) {
        this.manager.addEdit(new RemoveEdgeEdit(e, this));
    }

    /*Adds an abstract undoable edit called 'resizeEdit' onto the UndoManager
     * of the main model. Used for undo-ing and redo-ing edits that resize
     * the selected node.
     * */
    public void resizeEdit(GraphVertex selected, int oldX, int oldY, int x, int y) {
        this.manager.addEdit(new ResizeEdit(this, selected, oldX, oldY, x, y));
    }

    /*The 'removeVertex' method is overloaded with 'addEditBoolean' flag. The 'addEdgeBoolean' is
    * is to indicate whether we want to undo/redo removal of a vertex. We do not want to undo, an undo
    * action or a redo, a redo action. To avoid adding an undo-undo or redo-redo edit onto the UndoManager,
    * the 'addEditBoolean' is set to false. If we to undo the removal of a vertex, we set 'addEditBoolean'
    * to true.
    * */
    public void removeVertex(GraphVertex v) { this.removeVertex(v, true); }

    public void removeVertex (GraphVertex v, boolean addEditBoolean) {
        int index = this.indexOfV(v);
        ArrayList<GraphVertex> connected = new ArrayList<>();

        for(int i = 0; i < edges.size(); i++){
            GraphEdge e = edges.get(i);
            if(e.getVertexAIndex()==index || e.getVertexBIndex()==index) {
                /* edges we remove now, but should reappear on undo. The edge
                 * is represented by vertices added onto the 'connected' list*/
                if (e.getVertexBIndex() == index) {
                    connected.add(vertices.get(e.getVertexAIndex()));
                } else {
                    connected.add(vertices.get(e.getVertexBIndex()));
                }
                removeEdge(e);
                i--;
            }
        }
        vertices.remove(v);

        for (GraphEdge e : edges) {
            if (e.getVertexAIndex() > index) e.setVertexAIndex(e.getVertexAIndex()-1);
            if (e.getVertexBIndex() > index) e.setVertexBIndex(e.getVertexBIndex()-1);
        }

        /* if 'addEditBoolean' is true, we want this edit to be undoable */
        if(addEditBoolean) {
            this.manager.addEdit(new RemoveVertexEdit(this, v, connected));
        }
        changed();
    }

    public int indexOfV(GraphVertex v) {
        return this.vertices.indexOf(v);
    }

    /* Adds an abstract undoable edit called 'RenameVertexEdit' onto the UndoManager
     * of the main model. Used for undo-ing and redo-ing edits that rename
     * the selected node.
     * */
    public void renameEdit(GraphVertex vertex, String oldName, String newName) {
        this.manager.addEdit(new RenameVertexEdit(vertex, oldName, newName, this));
    }

    /* Adds an abstract undoable edit called 'addEdgeEdit' onto the UndoManager
     * of the main model. Used for undo-ing and redo-ing edits that add a new
     * edge into the graph.
     * */
    public void addEdgeEdit(GraphEdge e) {
        this.manager.addEdit(new AddEdgeEdit(e, this));
    }

    /* Adds an abstract undoable edit called 'MoveEdit' onto the UndoManager
     * of the main model. Used for undo-ing and redo-ing edits that move
     * a selected node inside the graph.
     * */
    public void moveVertex(int index, int oldX, int oldY) {
        GraphVertex v = this.vertices.get(index);
        this.manager.addEdit(new MoveEdit(v, oldX, oldY, v.getIntX(), v.getIntY(), this));
    }

    /*'save' is a static method for writing the graph on a file with '.graph'
    * All the vertices and edges are written onto the file with the name
    * 'graphName'
    * */
    public static void save(GraphModel g, String graphName) {
        try {
            PrintWriter writer = new PrintWriter(graphName+".graph");
            writer.println(g.vertices.size() + " " + g.edges.size());

            for (GraphVertex vertex : g.vertices) {
                writer.println(vertex.toSaveString());
            }

            for (GraphEdge edge : g.edges) {
                writer.println(edge.toSaveString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*The contents of the file in the 'filepath' are loaded into the main model.
    * The lines from the buffered reader are split into the data that constructs
    * the graph.*/
    public static GraphModel load(String filepath) {
        GraphModel graph = new GraphModel();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            String firstLine = br.readLine();

            String[] split = firstLine.split(" ");
            int numOfVertices = Integer.parseInt(split[0]);
            int numOfEdges = Integer.parseInt(split[1]);

            for(int i = 0; i < numOfVertices; i++) {

                line = br.readLine();
                split = line.split(" ");
                String name = split[0];
                int x = Integer.parseInt(split[1]);
                int y = Integer.parseInt(split[2]);
                int width = Integer.parseInt(split[3]);
                int height = Integer.parseInt(split[4]);

                GraphVertex v = new GraphVertex(name, x, y, width, height);
                graph.addVertex(v, false);
            }

            for (int i = 0; i < numOfEdges; i++) {
                line = br.readLine();
                split = line.split(" ");
                String name1 = split[0];
                String name2 = split[1];
                int v1 = Integer.parseInt(name1);
                int v2 = Integer.parseInt(name2);

                GraphEdge e = new GraphEdge(v1, v2);
                graph.addEdge(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }


}

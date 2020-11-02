package grapheditor.controller;

import grapheditor.model.GraphModel;
import grapheditor.view.GraphFrame;

/**GraphEditor holds the main function from which the program
 * begins execution.
 * */
public class GraphEditor {

    public static void main(String[] args) {
        GraphModel graph;

        /*The program loads a <targetSave> file if it is given in the command line
        * argument. The <targetSave> contains the filename (and directory if necessary)
        * of the file to be loaded. The files use '.graph' extention by default.
        * If there is no <targetSave> given, the graph starts with just an empty graph.
        * */
        if(args.length == 1) {
            String targetSave = args[0];
            graph = new GraphModel(targetSave);
        } else {
            graph = new GraphModel();
        }

        /*MVC pattern can be seen here below, where the main controller 'GraphController'
        * controls the main model. The view 'GraphFrame' displays the main model and the
        * main model is manipulated by the controller via the view. The main model 'graph'
        * is observable and the main view 'frame' observers the main model. The frame gets
        * updated when the main model sends a signal that it has been changed.
        * */
        GraphController controller = new GraphController(graph);
        GraphFrame frame = new GraphFrame(graph, controller);
        graph.addObserver(frame);

    }


}

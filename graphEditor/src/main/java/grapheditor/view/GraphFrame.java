package grapheditor.view;

import grapheditor.controller.*;
import grapheditor.model.GraphEdge;
import grapheditor.model.GraphModel;
import grapheditor.model.GraphVertex;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

/**
 * GraphFrame is a JFrame that implements all the necessary listeners for
 * the users actions. MenuListener is used for the menu options in JMenu and
 * JMenuItems. ActionListener is used to set the all the menu items to perform
 * their respective actions when they are called by the user. MouseListener and
 * MouseMotionListener is used for selecting, moving nodes and added edges using
 * the cursor. This frame (view in MVC) is an observer to the main model which is
 * the GraphModel. The frame is updated whenever the main model is changed.
 * */
public class GraphFrame extends JFrame implements MenuListener, ActionListener, MouseListener, MouseMotionListener, Observer {

    private JMenuBar menuBar;
    private JMenu file, edit, window;
    private JMenuItem save, load, rename, resize, copy, paste, addWindow, exit;
    private GraphModel graph;
    private GraphPanel panel;
    private int x;
    private int y;
    private ButtonBar buttonBar;
    private StatusBar statusBar;
    private GraphController controller;
    private int pasteOffset;
    private boolean dragging;

    /*GraphFrame is constructed from the main model and the controller. It has three bars: menu bar,
    * button bar and status bar. The JMenus that are present on the menu bar add this frame as a
    * menuListener while the JMenus items are action listeners. The JMenuItems that appear within
    * the menu are assigned shortcuts with the 'setAccelerator' and 'KeyStroke' methods. The main
    * panel of this frame is added onto the frame and this panel is instance of the GraphPanel class.
    * This panel adds this JFrame as a MouseListener and MouseMotionListener to the events that involve
    * the inputs from the mouse. 'pasteOffset' is used to paste a node with a certain offset from the
    * previously pasted node. The JFrame is 'DISPOSE_ON_CLOSE' because we have multiple window support.
    * */
    public GraphFrame(GraphModel graph, GraphController controller) throws HeadlessException {
        setLayout(new BorderLayout());
        setSize(700, 500);
        setTitle("Graph Editor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        menuBar = new JMenuBar();

        file = new JMenu("File");
        file.addMenuListener(this);

        edit = new JMenu("Edit");
        edit.addMenuListener(this);

        window = new JMenu("Window");
        window.addMenuListener(this);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(window);

        save = new JMenuItem("Save the file",new ImageIcon("saveIcon.png"));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        save.addActionListener(this);

        load = new JMenuItem("Load a file",new ImageIcon("loadIcon.png"));
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        load.addActionListener(this);

        file.add(save);
        file.add(load);

        rename = new JMenuItem("Rename a vertex",new ImageIcon("renameIcon.png"));
        rename.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        rename.addActionListener(this);

        resize = new JMenuItem("Resize a vertex",new ImageIcon("resizeIcon.png"));
        resize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        resize.addActionListener(this);

        copy = new JMenuItem("Copy a vertex",new ImageIcon("copyIcon.png"));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        copy.addActionListener(this);

        paste = new JMenuItem("Paste a vertex",new ImageIcon("pasteIcon.png"));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        paste.addActionListener(this);

        edit.add(rename);
        edit.add(resize);
        edit.add(copy);
        edit.add(paste);

        addWindow = new JMenuItem("Add a window",new ImageIcon("newWindowIcon.png"));
        addWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        addWindow.addActionListener(this);

        exit = new JMenuItem("Exit",new ImageIcon("exitIcon.png"));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        exit.addActionListener(this);

        window.add(addWindow);
        window.add(exit);

        panel = new GraphPanel(graph);
        this.add(panel, BorderLayout.CENTER);

        this.controller = controller;
        this.graph = graph;
        this.setJMenuBar(menuBar);

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        buttonBar = new ButtonBar(graph, panel);
        statusBar = new StatusBar();
        graph.addObserver(statusBar);

        this.add(buttonBar,BorderLayout.NORTH);
        this.add(statusBar, BorderLayout.SOUTH);

        graph.setAddEdgeBoolean(false);

        this.pasteOffset = 0;
        this.dragging = false;

        setVisible(true);
    }

    /*The actions that must be performed when menu items are selected are defined here.
    * For saving and loading we use JFileChooser which enables user to navigate
    * and select the desired directory. For inputs involving new nodes, 'showInputDialog' is
    * used. All the actions performed that manipulate the model are done through the controller
    * to maintain the MVC pattern. */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(save)) {
            JFileChooser c = new JFileChooser();
            int rVal = c.showSaveDialog(this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                this.controller.saveModel(c.getSelectedFile().getAbsolutePath());
            }
        }

        if (e.getSource().equals(load)) {
            JFileChooser c = new JFileChooser();
            int rVal = c.showOpenDialog(this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                this.controller.loadModel(c.getSelectedFile().getAbsolutePath());
            }
        }
        if (e.getSource().equals(rename)) {
            if (graph.getSelected() != null) {
                String newName = JOptionPane.showInputDialog("Type the new name of the vertex:");
                if (newName != null) {
                    this.controller.renameVertex(newName);
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a vertex.");
            }
        }
        if (e.getSource().equals(resize)) {
            if (graph.getSelected() != null) {
                JTextField xField = new JTextField(5);
                JTextField yField = new JTextField(5);

                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("width value:"));
                myPanel.add(xField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("height value:"));
                myPanel.add(yField);

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Please enter the width and height:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    if (Integer.parseInt(xField.getText()) >= 0 && Integer.parseInt(yField.getText()) >= 0 && Integer.parseInt(xField.getText()) <= 250 && Integer.parseInt(yField.getText()) <= 250) {
                        this.controller.resizeVertex(xField, yField);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Please insert a valid size.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a vertex.");
            }
        }

        if (e.getSource().equals(copy)) {
            if (graph.getSelected() != null) {
                this.controller.copyVertex();
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a vertex.");
            }
        }

        if (e.getSource().equals(paste)) {

            if (graph.getCopyVertex() != null) {
                this.controller.pasteVertex(pasteOffset);
                pasteOffset++;

            } else {
                JOptionPane.showMessageDialog(panel, "Please copy a vertex.");
            }
        }
        if (e.getSource().equals(addWindow)) {
            this.controller.addWindowsFunction();
        }
        if (e.getSource().equals(exit)) {
            System.exit(0);
        }
    }

    @Override
    public void menuSelected(MenuEvent e) {

    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /*A node is selected when a mouse is pressed. The node is selected by looping through the graph vertices
    * from index: 'size-1' to 0 because the vertices are painted from 0 to size-1. The last vertex is painted
    * on top of all of the vertices. The node is selected in reverse order to that of the order in which it is
    * painted. */

    /*The x and y positions of the MousePressed are private fields in the frame because they are used for the creation
    * of AbstractUndoableEdit of MoveEdit. The x and y store the initial (old) positions of the mouse when a vertex is
    * dragged. The deleteNodeButton, addEdgeButton, removeEdgeButton are enabled when a node is selected. */
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        for (int i = graph.getVertices().size()-1; i>=0; i--) {
            GraphVertex v = graph.getVertices().get(i);
            if (v.contains(x, y)) {
                this.controller.setSelected(v);
                x = v.getIntX();
                y = v.getIntY();
                buttonBar.getDeleteButton().setEnabled(true);
                buttonBar.getAddEdgeButton().setEnabled(true);
                buttonBar.getRemoveEdgeButton().setEnabled(true);
                return;
            }
        }
        if(graph.getAddEdgeBoolean()) controller.stopEdgeAdding();
        this.controller.setSelected(null);
        buttonBar.getDeleteButton().setEnabled(false);
        buttonBar.getAddEdgeButton().setEnabled(false);
        buttonBar.getRemoveEdgeButton().setEnabled(false);
    }


    /* When the mouse is released and if the AddEdgeBoolean is true meaning the user
    * has selected a second vertex and the last added edge (the new edge) receives its
    * second vertex. This second vertex is set in the controller following the MVC pattern.
    * The boolean 'dragging' is used for the case, where a node is dragged into a new location.
    * This edit (moveEdit) must be undo-able and thus added into the UndoManager through the
    * controller. */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (graph.getAddEdgeBoolean()) {
            if (graph.getSelected() != null) {
                GraphEdge lastEdge = graph.getEdges().get(graph.getEdges().size() - 1);
                GraphVertex lastEdgeVertexA = graph.getVertices().get(lastEdge.getVertexAIndex());
                if (!graph.getSelected().equals(lastEdgeVertexA)) {
                    controller.setSecondVertex();

                }
            }
        }
        if(graph.getSelected()!=null && dragging) {
            this.controller.createMoveEdit(x, y, graph.getSelected());
            dragging = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /* mouseDragged simply sets a new location for the selected node.
    * The dragged flag is set to be true so in MouseReleased, the undoable
    * edit, MoveEdit, can be added into the UndoManager of the main model.
    * */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(graph.getSelected()!=null) {
            dragging = true;
            int Dx = e.getX() - (int) (0.5 * graph.getSelected().getIntWidth());
            int Dy = e.getY() - (int) (0.5 * graph.getSelected().getIntHeight());
            this.controller.setVertexLocation(Dx, Dy);
        }
    }

    /*panel must be repainted whenever the mouse moves to paint the edge that
    * follows the cursor(during an edge creation)*/
    @Override
    public void mouseMoved(MouseEvent e) {
        panel.repaint();
    }

    /*The frame is updated whenever the graph is changed. This frame is an observer
    * to the graph model and the graph model is observable.*/
    @Override
    public void update(Observable o, Object arg) {
        GraphModel model = (GraphModel) arg;
        this.graph = model;
        panel.setModel(model);
        panel.repaint();
    }
}

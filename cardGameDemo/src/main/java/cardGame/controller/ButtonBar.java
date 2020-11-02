package cardGame.controller;

import cardGame.game.Draw;

import javax.swing.JMenuBar;

import java.awt.Color;

/**
 * Panel with the buttons for the draw-class controllers
 */
public class ButtonBar extends JMenuBar {

    /**
     * Create a new buttonpanel with all the necessary buttons
     */
    public ButtonBar(Draw draw) {
        add(new DrawButton(draw));
        add(new ResetButton(draw));
    }

}

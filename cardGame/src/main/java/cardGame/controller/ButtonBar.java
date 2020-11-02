package controller;

import model.Bank;
import model.Player;

import javax.swing.JMenuBar;

/**
 * Panel with the buttons for the draw-class controllers
 */
public class ButtonBar extends JMenuBar {

    private HitButton hitbutton;
    private PassButton passbutton;


    /**
     * Create a new buttonpanel with all the necessary buttons
     */
    public ButtonBar(Player player, Bank bank) {

        hitbutton = new HitButton(player, this);
        passbutton = new PassButton(bank, this);

        add(hitbutton);
        add(passbutton);
    }

}

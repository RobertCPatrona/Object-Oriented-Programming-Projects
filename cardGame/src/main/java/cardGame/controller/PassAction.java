package controller;

import model.Bank;

import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;

/**
 * Represents an action made to shuffle all cards back into the deck. Although
 * useless on an empty discard pile, this action is always available.
 */
public class PassAction extends AbstractAction {

    private Bank bank;
    private ButtonBar bar;
    /**
     * Creates a new action to shuffle all cards back into the deck
     */
    public PassAction(Bank bank, ButtonBar bar) {
        super("Pass turn.");
        this.bank = bank;
        this.bar = bar;
    }

    /**
     * Draws a card
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.bar.setVisible(false);

        while(bank.getSum()<16) {
            bank.move();
        }
    }
}

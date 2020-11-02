package controller;

import model.Bank;
import model.BlackJack;
import model.Deck;
import model.Player;
import view.DrawPanelBlackJack;

import javax.swing.*;
import java.awt.*;

public class Execute {
    ButtonBar buttonbar;

    public void begin() {
        Deck mainDeck = new Deck();
        mainDeck.addCompleteDeck();
        mainDeck.shuffle();
        Player player = new Player(mainDeck);
        Bank bank = new Bank(mainDeck);
        BlackJack model = new BlackJack(mainDeck);
        JFrame frame = new JFrame("Black Jack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonbar = new ButtonBar(player, bank);
        frame.setJMenuBar(buttonbar);
        DrawPanelBlackJack panel = new DrawPanelBlackJack(model, player, bank, buttonbar);
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo (null);
        frame.setVisible(true);

        player.move();
        player.move();

        bank.move();
        bank.move();

    }
}


package view;

import controller.ButtonBar;
import model.Bank;
import model.BlackJack;
import model.Card;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class DrawPanelBlackJack extends JPanel implements Observer {
    private static final int CARD_SPACING = 2; //pixels
    private static final int Y_OFFSET = Card.values().length * CARD_SPACING;

    private BlackJack model;
    private Bank bank;
    private Player player;
    private ButtonBar bar;


    /**
     * Create a new DrawPanel
     */
    public DrawPanelBlackJack(BlackJack model, Player player, Bank bank, ButtonBar bar) {
        this.model = model;
        this.player = player;
        this.bank = bank;
        this.bar = bar;
        model.addObserver(this);
        bank.addObserver(this);
        player.addObserver(this);
        setBackground(new Color(60, 60, 60));
        setVisible(true);
        setOpaque(true);
    }

    /**
     * Paint the areas in which deck and discard pile can be found
     */
    private void paintAreas(Graphics g) {

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 20));

        g.setColor(Color.YELLOW);
        g.drawRect(0, 0, getWidth() / 3, getHeight() - 1);
        g.drawString("Your Cards", 1, 20);

        int sumPlayer = player.getSum();
        String messagePlayer = "Your Sum : " + sumPlayer;
        g.drawString(messagePlayer, 1, 45);

        g.drawRect(getWidth() / 3, 0, getWidth() / 3, getHeight() - 1);
        g.drawString("Main Deck", (getWidth() / 3), 20);

        if (!bar.isVisible()) {
            int sumBank = bank.getSum();
            String messageBank = "Bank's Sum : " + sumBank;
            g.drawString(messageBank, 2 * (getWidth() / 3), 45);
        }

        g.drawRect(getWidth() / 3 + getWidth() / 3, 0, getWidth() / 3, getHeight() - 1);
        g.drawString("Bank's Cards", 2 * (getWidth() / 3), 20);
        g.setColor(Color.BLACK);
    }

    /**
     * Get the scaled spacing between edges and cards
     */
    private int getSpacing() {
        return (int) ((getHeight() * 20) / 600.0);
    }

    /**
     * Get the scaled width of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */
    public int cardWidth() {
        //return 150;
        if((getHeight() * 600) / (getWidth() * 436) <= 1.0)
            return (int) ((cardHeight() * 436) / 600);
        return (getWidth() - getSpacing() * 3 - 2 * Card.values().length) / 2;
    }

    /**
     * Get the scaled height of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */
    public int cardHeight() {
        //return 250;
        if((getHeight() * 600) / (getWidth() * 436) > 1.0) {
            return (int) (((cardWidth() * 600) / 436)/2);
        }
        return ((getHeight() - getSpacing() * 2 - 2 * Card.values().length)/2);
    }

    /**
     * Draw the deck
     */
    private void paintDeck(Graphics g) {
        int depth = 0, offset = 0;

        for (Card card : player.getCurrentDeck().getCards()) {
            int posX = getSpacing() + depth / 2 + offset;
            int posY = getSpacing() + Y_OFFSET - CARD_SPACING * depth / 2;
            g.drawImage(CardTextures.getTexture(card)
                    , posX, posY, cardWidth(), cardHeight(), this);
            g.drawRect(posX, posY, cardWidth(), cardHeight());
            depth++;
            offset += 25;
        }
        depth = 0;
        offset = 0;
        for (depth = 0; depth < model.getMainDeck().size(); ++depth) {
            int posX = getSpacing() + depth / 2 + getWidth() / 3 + 1;
            int posY = getSpacing() + Y_OFFSET - CARD_SPACING * depth / 2;
            g.drawImage(CardBackTextures.getTexture(CardBack.CARD_BACK_BLUE)
                    , posX, posY, cardWidth(), cardHeight(), this);
            g.drawRect(posX, posY, cardWidth(), cardHeight());
        }
        depth = 0;
        offset = 0;
        /*  if player's turn: keep one card closed
         *   else open all cards */
        if (bar.isVisible()) {
            //Draw open
            Card cardOpen = bank.getCurrentDeck().getCards().get(0);

            int posX = getSpacing() + depth / 2 + 2 * (getWidth() / 3) + offset;
            int posY = getSpacing() + Y_OFFSET - CARD_SPACING * depth / 2;
            g.drawImage(CardTextures.getTexture(cardOpen)
                    , posX, posY, cardWidth(), cardHeight(), this);
            g.drawRect(posX, posY, cardWidth(), cardHeight());
            depth++;
            offset += 25;

            //Draw closed
            posX = getSpacing() + depth / 2 + 2 * (getWidth() / 3) + offset;
            posY = getSpacing() + Y_OFFSET - CARD_SPACING * depth / 2;
            g.drawImage(CardBackTextures.getTexture(CardBack.CARD_BACK_BLUE)
                    , posX, posY, cardWidth(), cardHeight(), this);
            g.drawRect(posX, posY, cardWidth(), cardHeight());
            depth++;
            offset += 25;

        } else {
            for (Card card : bank.getCurrentDeck().getCards()) {
                int posX = getSpacing() + depth / 2 + 2 * (getWidth() / 3) + offset;
                int posY = getSpacing() + Y_OFFSET - CARD_SPACING * depth / 2;
                g.drawImage(CardTextures.getTexture(card)
                        , posX, posY, cardWidth(), cardHeight(), this);
                g.drawRect(posX, posY, cardWidth(), cardHeight());
                depth++;
                offset += 25;
            }
        }
        if ((player.getSum()==21 && bank.getSum()==21) || (bank.getSum()>=16 && !bar.isVisible() && bank.getSum()==player.getSum())) {
            decideWin(g, 1);
        } else if(player.getSum()==21 || bank.getSum() > 21) {
            decideWin(g, 2);
        } else if (player.getSum() > 21 || (!bar.isVisible() && player.getSum() < bank.getSum() && bank.getSum()>=16) || bank.getSum()==21) {
            decideWin(g, 0);
        } else if(!bar.isVisible() && (player.getSum() > bank.getSum() && bank.getSum()>=16)) {
            decideWin(g, 2);
        }
    }

    /**
     * Draw the discard pile
     */
    void decideWin(Graphics g, int playerWins)  {
        switch (playerWins) {
            case 0:
                g.setColor(Color.RED);
                g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 110));
                g.drawString("YOU LOSE!", getWidth() / 10, getHeight() / 2 + 190);
                bar.setVisible(false);
                break;
            case 1:
                g.setColor(Color.GREEN);
                g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 100));
                g.drawString("IT'S A DRAW!", getWidth()/15, getHeight()/2 + 190);
                bar.setVisible(false);
                break;
            case 2:
                g.setColor(Color.GREEN);
                g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 110));
                g.drawString("YOU WIN!", getWidth() / 8, getHeight() / 2 + 190);
                bar.setVisible(false);
                break;
        }
    }

    /**
     * Paint the items that this class alone is responsible for.
     *
     * This method is part of a template method that calls
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintAreas(g);
        paintDeck(g);
    }

    /**
     * Tell this DrawPanel that the object it displays has changed
     */
    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }

}

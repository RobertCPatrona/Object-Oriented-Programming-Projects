package model;

import java.util.Observable;

import static model.Card.Face.ACE;

public class BlackJack extends Observable {
    protected Deck mainDeck;
    protected Deck currentDeck;
    protected int sum;

    public BlackJack(Deck mainDeck) {
        this.mainDeck = mainDeck;
        this.currentDeck = new Deck();
        this.sum = sum;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public Deck getCurrentDeck() {
        return currentDeck;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void move() {

        Card draw = mainDeck.draw(mainDeck);

       if (draw.getFace() == ACE) {
            this.currentDeck.setHasAces(currentDeck.getHasAces()+1);
        }

        if (this.getSum()+draw.getValue() > 21 && this.currentDeck.getHasAces() > 0) {
            this.setSum(this.getSum() + draw.getValue() - 10);
            this.currentDeck.setHasAces(this.currentDeck.getHasAces() - 1);
            this.currentDeck.addOnTop(draw);
            notifyObservers();
            setChanged();
        } else {
            this.currentDeck.addOnTop(draw);
            this.setSum(this.getSum() + draw.getValue());
            notifyObservers();
            setChanged();
        }
    }
}


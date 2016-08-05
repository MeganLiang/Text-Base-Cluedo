package model;

import java.util.List;

/**
 * Hand of player
 */
public class Hand {
    private List<Card> cards;


    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public void addToHand(Card c) {
        cards.add(c);
    }

    public List<Card> getCards() {
        return cards;
    }
}



package model;

import java.util.List;

/**
 * Created by megan on 19/07/16.
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



package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class Hand {
    private final List<Card> handList = new ArrayList<>();

    public void dealOneRound(Deck deck) {
        this.handList.add(deck.draw());
    }

    public void addOneCard(Card card) {
        this.handList.add(card);
    }

    public int handValueOf() {
        int handValue = this.handList
                .stream()
                .mapToInt(Card::rankValue)
                .sum();

        // does the hand contain at least 1 Ace?
        boolean hasAce = this.handList
                .stream()
                .anyMatch(card -> card.rankValue() == 1);

        // if the total hand value <= 11, then count the Ace as 11 by adding 10
        if (hasAce && handValue < 11) {
            handValue += 10;
        }

        return handValue;
    }

    public boolean isPlayerBusted() {
        return handValueOf() > 21;
    }

    public void displayHand() {
        System.out.println(handList.stream()
                               .map(Card::display)
                               .collect(Collectors.joining(
                                       ansi().cursorUp(6).cursorRight(1).toString())));
    }

    public void printFirstCard() {
        System.out.println(this.handList.get(0).display()); // first card is Face Up
    }
}

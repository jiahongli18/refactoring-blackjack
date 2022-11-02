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

    public int value() {
        int handValue = this.handList
                .stream()
                .mapToInt(Card::rankValue)
                .sum();

        // does the hand contain at least 1 Ace?
        boolean hasAce = this.handList
                .stream()
                .anyMatch(card -> card.rankValue() == 1);

        // if the total hand value <= 11, then count the Ace as 11 by adding 10
        if (hasAce && handValue <= 11) {
            handValue += 10;
        }

        return handValue;
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

    public boolean isBust() {
        return value() > 21;
    }

    public int compareTo(Hand playerHand) {
        return Integer.compare(this.value(), playerHand.value());
    }

    void displayHandFormatted(String person) {
        System.out.println(person + " has: ");
        this.displayHand();
        System.out.println(" (" + this.value() + ")");
    }

    public boolean shouldDealerHit(Game game) {
        return value() <= Game.DEALER_STOPPING_VALUE;
    }
}

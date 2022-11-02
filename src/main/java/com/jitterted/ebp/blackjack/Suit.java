package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

public enum Suit {
    HEART("♥", true),
    CLUB("♣", false),
    SPADE("♠", false),
    DIAMOND("♦", true);

    private final String symbol;
    private final boolean isRed;

    Suit(String symbol, Boolean isRed) {
        this.symbol = symbol;
        this.isRed = isRed;
    }

    public static Suit valueOfSymbol(String symbol) {
        for (Suit suit : values()) {
            if (suit.symbol.equals(symbol)) {
                return suit;
            }
        }
        return null;
    }

    public Ansi.Color getCardColor() {
        return this.isRed ? Ansi.Color.RED : Ansi.Color.BLACK;
    }

    public String getSymbol() {
        return this.symbol;
    }
}

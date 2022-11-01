package com.jitterted.ebp.blackjack;

public enum Suit {
    HEART,
    CLUB,
    SPADE,
    DIAMOND;

    public String getChar() {
        switch(this) {
            case SPADE:
                return "♠";
            case DIAMOND:
                return "♦";
            case CLUB:
                return "♣";
            case HEART:
                return "♥";
            default:
                return "";
        }
    }
}

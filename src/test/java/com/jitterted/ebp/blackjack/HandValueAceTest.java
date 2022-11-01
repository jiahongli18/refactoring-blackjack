package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandValueAceTest {

    Suit dummySuit = Suit.DIAMOND;

    @Test
    public void handWithOneAceTwoCardsIsValuedAt11() throws Exception {
        Game game = new Game();
        List<Card> cards = List.of(new Card(dummySuit, "A"),
                                   new Card(dummySuit, "5"));

        assertThat(game.handValueOf(cards))
                .isEqualTo(11 + 5);
    }

    @Test
    public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() throws Exception {
        Game game = new Game();
        List<Card> cards = List.of(new Card(dummySuit, "A"),
                                   new Card(dummySuit, "8"),
                                   new Card(dummySuit, "3"));

        assertThat(game.handValueOf(cards))
                .isEqualTo(1 + 8 + 3);
    }

}

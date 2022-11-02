package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandValueAceTest {

    Suit dummySuit = Suit.DIAMOND;

    @Test
    public void handWithOneAceTwoCardsIsValuedAt11() throws Exception {
        Hand hand = new Hand();

        hand.addOneCard(new Card(dummySuit, "A"));
        hand.addOneCard(new Card(dummySuit, "5"));

        assertThat(hand.handValueOf())
                .isEqualTo(11 + 5);
    }

    @Test
    public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() throws Exception {
        Hand hand = new Hand();

        hand.addOneCard(new Card(dummySuit, "A"));
        hand.addOneCard(new Card(dummySuit, "8"));
        hand.addOneCard(new Card(dummySuit, "3"));

        assertThat(hand.handValueOf())
                .isEqualTo(1 + 8 + 3);
    }
}

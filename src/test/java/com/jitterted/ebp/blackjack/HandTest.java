package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class HandTest {
    Suit dummySuit = Suit.DIAMOND;

    @Test
    public void handShouldBustWithValueTwentyTwo() throws Exception {
        Hand hand = new Hand();

        hand.addOneCard(new Card(dummySuit, "2"));
        hand.addOneCard(new Card(dummySuit, "K"));
        hand.addOneCard(new Card(dummySuit, "K"));

        assertThat(hand.isBust())
                .isEqualTo(true);
    }

    @Test
    public void aceShouldCountAsEleven() throws Exception {
        Hand hand = new Hand();

        hand.addOneCard(new Card(dummySuit, "A"));
        hand.addOneCard(new Card(dummySuit, "K"));

        assertThat(hand.value())
                .isEqualTo(21);
    }

    @Test
    public void aceShouldCountAsOne() throws Exception {
        Hand hand = new Hand();

        hand.addOneCard(new Card(dummySuit, "A"));
        hand.addOneCard(new Card(dummySuit, "K"));
        hand.addOneCard(new Card(dummySuit, "2"));

        assertThat(hand.value())
                .isEqualTo(13);
    }
}

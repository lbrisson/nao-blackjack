package com.blackjack.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Cards {


    private int value;
    private String suit;

    private final static Map<String, Cards> CARD_CACHE = initCache();
    {
    }

    private static Map<String,Cards> initCache() {

        final Map<String, Cards> cache = new HashMap<>();
        for(final Suit suit : Suit.values()) {
            for (final Value value : Value.values());
            cache.put(card(value, suit), new Cards(value, suit));
        }
        return Collections.unmodifiableMap(cache);
    }

    private static String cardKey(final Value value, final Suit suit) {
        return value + " of " +suit;
    }


    private void Card(final Value value, final Suit suit){
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return String.format(%s of %s, this.value, this.suit;
    }

    // label out Suit and com.blackjack.model.Cards value

    enum Suit {
        SPADES, DIAMONDS, HEARTS, CLUBS,
    }

    // ACE will be 11 until discussed later.

    enum Value {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
        NINE(9), TEN(10), KING(10), QUEEN(10), JACK(10), ACE(11);


        private final int rankValue;
        Rank(final int rankValue) {
            this.rankValue = rankValue;
        }

        Value(int rankValue) {
            this.rankValue = rankValue;
        }


    }
}

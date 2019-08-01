package com.test;

public enum Strike {
    STRIKE(1), MULTI_STRIKE(2), RED_STRIKE(3), STRIKER_STRIKE(4), DEFUNCT_RED_COIN(5), DEFUNCT_BLACK_COIN(6), NONE(7);

    int strike;
    Strike(int strike) {
        this.strike = strike;
    }

    int getStrike() {
        return strike;
    }

    static Strike valueOf(int strike) {
        for(Strike s : values()) {
            if(s.getStrike() == strike) {
                return s;
            }
        }
        throw new RuntimeException("Option does not exists.");
    }
}

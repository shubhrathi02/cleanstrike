package com.test;

class Scorer {
    static final int STRIKE_POINTS = 1;
    static final int MULTI_STRIKE_POINTS = 2;
    static final int RED_STRIKE_POINTS = 3;
    private static final int STRIKER_STRIKE_POINTS = -1;
    private static final int DEFUNCT_COIN_POINTS = -2;
    static final int ZERO_POINTS = 0;

    private Player[] players = new Player[2];

    void initialize() {
        players[0] = new Player();
        players[1] = new Player();
    }

    int getPlayerScore(int playerNo) {
        return players[playerNo].getPoints();
    }

    void strike(int playerNo) {
        players[playerNo].updatePoints(STRIKE_POINTS);
    }

    void multiStrike(int playerNo) {
        players[playerNo].updatePoints(MULTI_STRIKE_POINTS);
    }

    void redStrike(int playerNo) {
        players[playerNo].updatePoints(RED_STRIKE_POINTS);
    }

    void strikerStrike(int playerNo) {
        players[playerNo].updatePoints(STRIKER_STRIKE_POINTS);
    }

    void defunctCoin(int playerNo) {
        players[playerNo].updatePoints(DEFUNCT_COIN_POINTS);
    }

    void strkeNone(int playerNo) {
        players[playerNo].updatePoints(ZERO_POINTS);
    }
}

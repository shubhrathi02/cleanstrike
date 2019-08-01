package com.test;

class CarromBoard {

    private int blackCount, redCount;

    void initialize() {
        this.blackCount = 9;
        this.redCount = 1;
    }

    int getRedCoinsCount() {
        return this.redCount;
    }

    int getBlackCoinsCount() {
        return this.blackCount;
    }

    void strike() {
        updateBlackCount(-1);
    }

    private void updateBlackCount(int changeInCount) {
        this.blackCount += changeInCount;
        if(this.blackCount < 0) {
            this.blackCount -= changeInCount;
            throw new RuntimeException("No. of Black coins Available on Board: " + this.blackCount);
        }
    }

    void multiStrike() {
        updateBlackCount(-2);
    }

    void defunctBlackCoin() {
        updateBlackCount(-1);
    }

    boolean hasAllCoinsExhausted() {
        return this.blackCount <= 0 && this.redCount <= 0;
    }

    void redStrike() {
        updateRedCount(-1);
    }

    void defunctRedCoin() {
        updateRedCount(-1);
    }

    private void updateRedCount(int changeInCount) {
        this.redCount += changeInCount;
        if(this.redCount < 0) {
            this.redCount -= changeInCount;
            throw new RuntimeException("No. of Red coins Available on Board: " + this.redCount);
        }
    }
}

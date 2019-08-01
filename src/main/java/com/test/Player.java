package com.test;

import static com.test.TurnHistory.SUCCESSFUL;
import static com.test.TurnHistory.UNSUCCESSFUL;

class Player {

    private int points;
    private TurnHistory[] history;
    private int noOfFouls;

    Player(){
        this.points = 0;
        this.noOfFouls = 0;
        this.history = new TurnHistory[2];
        resetHistory();
    }

    int getPoints() {
        return points;
    }

    void updatePoints(int changeInPoints) {
        changePointsBy(changeInPoints);
        checkAndUpdate3FoulsScenario(changeInPoints);
        checkAndUpdate3SuccessiveUnSuccessfulScenarios(changeInPoints);
    }

    private void checkAndUpdate3SuccessiveUnSuccessfulScenarios(int changeInPoints) {
        if(changeInPoints <= 0) {
            if(isItThirdUnsuccessful()){
                changePointsBy(-1);
                resetHistory();
            } else {
                updateHistory(UNSUCCESSFUL);
            }
        } else {
            updateHistory(SUCCESSFUL);
        }

    }

    private void checkAndUpdate3FoulsScenario(int changeInPoints) {
        if(changeInPoints < 0){
            noOfFouls++;
            if(noOfFouls >= 3) {
                changePointsBy(-1);
                noOfFouls = 0;
            }
        }
    }

    private void changePointsBy(int changeInPoints) {
        this.points += changeInPoints;
    }

    public TurnHistory[] getHistory() {
        return this.history;
    }

    private void updateHistory(TurnHistory turnHistory) {
        if(this.history[0] == null) {
            this.history[0] = turnHistory;
        } else if(this.history[1] == null) {
            this.history[1] = turnHistory;
        } else {
            this.history[0] = this.history[1];
            this.history[1] = turnHistory;
        }
    }

    private boolean isItThirdUnsuccessful() {
        return this.history[0] != null && this.history[1] != null
                && this.history[0] == UNSUCCESSFUL && this.history[1] == UNSUCCESSFUL;
    }

    private void resetHistory() {
        this.history[0] = null;
        this.history[1] = null;
    }
}

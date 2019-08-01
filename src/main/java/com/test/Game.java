package com.test;


class Game {

    private static final int PLAYER_1 = 0;
    private static final int PLAYER_2 = 1;

    private Scorer scorer;
    private CarromBoard carromBoard;

    void initialize() {
        scorer = new Scorer();
        scorer.initialize();
        carromBoard = new CarromBoard();
        carromBoard.initialize();
    }

    String getGameResult() {
        int player1Score = scorer.getPlayerScore(PLAYER_1);
        int player2Score = scorer.getPlayerScore(PLAYER_2);
        String gameResult = null;
        int scoreDiff = player2Score - player1Score;

        if(carromBoard.hasAllCoinsExhausted()){
            gameResult = getResultWhenAllCoinExhausted(player1Score, player2Score, scoreDiff);
        } else {
            gameResult = getResultWhenCoinsArePresentOnBoard(player1Score, player2Score, scoreDiff);
        }
        return gameResult;
    }

    private String getResultWhenCoinsArePresentOnBoard(int player1Score, int player2Score, int scoreDiff) {
        String gameResult = null;
        if (player2Score >= 5 && scoreDiff >= 3) {
            gameResult = buildResultString(2, player1Score, player2Score);
        } else if (player1Score >= 5 && scoreDiff <= -3) {
            gameResult = buildResultString(1, player1Score, player2Score);
        }
        return gameResult;
    }

    private String getResultWhenAllCoinExhausted(int player1Score, int player2Score, int scoreDiff) {
        String gameResult;
        if(doesPlayer2Win(player2Score, scoreDiff)) {
            gameResult = buildResultString(2, player1Score, player2Score);
        } else if(doesPlayer1Win(player1Score, scoreDiff)){
                gameResult = buildResultString(1, player1Score, player2Score);
        } else {
            gameResult = "Game is draw. " + "Final Score: " + player1Score + " - " + player2Score;;
        }
        return gameResult;
    }

    private boolean doesPlayer1Win(int player1Score, int scoreDiff) {
        return (scoreDiff < 0 && player1Score >= 5) || (scoreDiff <= -3);
    }

    private boolean doesPlayer2Win(int player2Score, int scoreDiff) {
        return (scoreDiff > 0 && player2Score >= 5) || (scoreDiff >= 3);
    }

    static String buildResultString(int playerNo, int player1Score, int player2Score) {
        return "Player "+ playerNo + " won the game. Final Score: " + player1Score + " - " + player2Score;
    }

    void strike(int playerNo) {
        carromBoard.strike();
        scorer.strike(playerNo);
    }

    void multiStrike(int playerNo) {
        carromBoard.multiStrike();
        scorer.multiStrike(playerNo);
    }

    void redStrike(int playerNo) {
        carromBoard.redStrike();
        scorer.redStrike(playerNo);
    }

    void strikerStrike(int playerNo) {
        scorer.strikerStrike(playerNo);
    }

    void defunctBlackCoin(int playerNo) {
        carromBoard.defunctBlackCoin();
        scorer.defunctCoin(playerNo);
    }

    void defunctRedCoin(int playerNo) {
        carromBoard.defunctRedCoin();
        scorer.defunctCoin(playerNo);
    }

    void strikeNone(int playerNo) {
        scorer.strkeNone(playerNo);
    }

    void play(int player, int input) {
        Strike s = Strike.valueOf(input);
        switch (s) {
            case STRIKE: strike(player); break;
            case MULTI_STRIKE: multiStrike(player); break;
            case RED_STRIKE: redStrike(player); break;
            case STRIKER_STRIKE: strikerStrike(player); break;
            case DEFUNCT_RED_COIN: defunctRedCoin(player); break;
            case DEFUNCT_BLACK_COIN: defunctBlackCoin(player); break;
            case NONE: strikeNone(player); break;
        }

    }
}

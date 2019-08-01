package com.test;

import junit.framework.TestCase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GameTest extends TestCase {

    private static final int PLAYER_1 = 0;
    private static final int PLAYER_2 = 1;
    private Game game;

    public void setUp(){
        game = new Game();
        game.initialize();
    }

    public void testGetGameResultsWhenPlayer1Has5PointsAnd3Diff() {
        game.strike(PLAYER_1);
        game.multiStrike(PLAYER_2);
        game.strikerStrike(PLAYER_1);
        game.strikeNone(PLAYER_2);
        game.redStrike(PLAYER_1);
        game.defunctBlackCoin(PLAYER_2);
        game.multiStrike(PLAYER_1);
        assertThat(game.getGameResult(), is(equalTo(Game.buildResultString(1, 5, 0))));
    }

    public void testGetGameResultsIfAllCoinsExhaustedAndHigherScorerHas5Points() {
        game.strike(PLAYER_1);
        game.multiStrike(PLAYER_2);
        game.strike(PLAYER_1);
        game.redStrike(PLAYER_2);
        game.multiStrike(PLAYER_1);
        game.multiStrike(PLAYER_2);
        game.strike(PLAYER_1);
        assertThat(game.getGameResult(), is(equalTo(Game.buildResultString(2, 5, 7))));
    }

    public void testGetGameResultsIfAllCoinsExhaustedAndScoreDiffIsMoreThan3PointsButScoreIsLessThan5() {
        game.defunctBlackCoin(PLAYER_1);
        game.multiStrike(PLAYER_2);
        game.defunctBlackCoin(PLAYER_1);
        game.defunctBlackCoin(PLAYER_2);
        game.strike(PLAYER_1);
        game.redStrike(PLAYER_2);
        game.strike(PLAYER_1);
        game.defunctBlackCoin(PLAYER_2);
        game.strikerStrike(PLAYER_1);
        game.strike(PLAYER_2);
        assertThat(game.getGameResult(), is(equalTo(Game.buildResultString(2, -4, 2))));
    }

    public void testGetGameResultsIfAllCoinsExhaustedAndScoreDiffIsMoreThan3PointsForPlayer1ButScoreIsLessThan5() {
        game.multiStrike(PLAYER_1);
        game.defunctBlackCoin(PLAYER_2);
        game.defunctBlackCoin(PLAYER_1);
        game.defunctBlackCoin(PLAYER_2);
        game.redStrike(PLAYER_1);
        game.strike(PLAYER_2);
        game.defunctBlackCoin(PLAYER_1);
        game.strike(PLAYER_2);
        game.strike(PLAYER_1);
        assertThat(game.getGameResult(), is(equalTo(Game.buildResultString(1, 2, -2))));
    }

    public void testGetGameResultsWhenGameIsDraw() {
        game.strike(PLAYER_1);
        game.multiStrike(PLAYER_2);
        game.strike(PLAYER_1);
        game.defunctBlackCoin(PLAYER_2);
        game.defunctRedCoin(PLAYER_1);
        game.strike(PLAYER_2);
        game.strike(PLAYER_1);
        game.strike(PLAYER_2);
        game.strike(PLAYER_1);
        assertThat(game.getGameResult(), is(equalTo("Game is draw. Final Score: 2 - 2")));
    }
}

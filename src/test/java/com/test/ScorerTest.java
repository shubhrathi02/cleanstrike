package com.test;

import junit.framework.TestCase;

import static com.test.Scorer.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ScorerTest extends TestCase {

    private static final int PLAYER_1 = 0;
    private static final int PLAYER_2 = 1;
    private Scorer scorer;

    public void setUp() {
        scorer = new Scorer();
        scorer.initialize();
    }

    public void testInitialize() {
        assertThat(scorer.getPlayerScore(PLAYER_1), is(equalTo(0)));
        assertThat(scorer.getPlayerScore(PLAYER_2), is(equalTo(0)));
    }

    public void testStrike() {
        scorer.strike(PLAYER_1);
        assertThat(scorer.getPlayerScore(PLAYER_1), is(equalTo(STRIKE_POINTS)));
    }

    public void testMultiStrike() {
        scorer.multiStrike(PLAYER_1);
        assertThat(scorer.getPlayerScore(PLAYER_1), is(equalTo(MULTI_STRIKE_POINTS)));
    }

    public void testRedStrike() {
        scorer.redStrike(PLAYER_1);
        assertThat(scorer.getPlayerScore(PLAYER_1), is(equalTo(RED_STRIKE_POINTS)));
    }

    public void testStrikerStrike() {
        scorer.strikerStrike(PLAYER_1);
        assertThat(scorer.getPlayerScore(PLAYER_1), is(equalTo(-1)));
    }

    public void testDefunctCoin() {
        scorer.defunctCoin(PLAYER_1);
        assertThat(scorer.getPlayerScore(PLAYER_1), is(equalTo(-2)));
    }

    public void testScoreNone() {
        scorer.strkeNone(PLAYER_1);
        assertThat(scorer.getPlayerScore(PLAYER_1), is(equalTo(0)));
    }

    public void testWhenPlayerDoesNotPocketFor3SuccessiveTurns() {
        scorer.redStrike(PLAYER_1);
        scorer.strkeNone(PLAYER_1);
        scorer.strikerStrike(PLAYER_1);
        scorer.strkeNone(PLAYER_1);
        assertThat(scorer.getPlayerScore(PLAYER_1), is(equalTo(1)));
    }

    public void testWhenPlayerMakesFoulFor3Times() {
        scorer.strikerStrike(PLAYER_1);
        scorer.strikerStrike(PLAYER_1);
        scorer.redStrike(PLAYER_1);
        scorer.defunctCoin(PLAYER_1);
        assertThat(scorer.getPlayerScore(PLAYER_1), is(equalTo(-2)));
    }

    public void testWhenPlayerMakesFoulForMoreThan3Times() {
        scorer.strikerStrike(PLAYER_1);
        scorer.strikerStrike(PLAYER_1);
        scorer.redStrike(PLAYER_1);
        scorer.defunctCoin(PLAYER_1);
        scorer.strike(PLAYER_1);
        scorer.redStrike(PLAYER_1);
        scorer.multiStrike(PLAYER_1);
        scorer.strikerStrike(PLAYER_1);
        scorer.defunctCoin(PLAYER_1);
        assertThat(scorer.getPlayerScore(PLAYER_1), is(equalTo(1)));
    }

    public void testWhenPlayerMakesFoulForMoreThan3TimesWitheUnsuccessfulScenario() {
        scorer.strikerStrike(PLAYER_1);
        scorer.strikerStrike(PLAYER_1);
        scorer.redStrike(PLAYER_1);
        scorer.defunctCoin(PLAYER_1);
        scorer.strike(PLAYER_1);
        scorer.redStrike(PLAYER_1);
        scorer.multiStrike(PLAYER_1);
        scorer.strikerStrike(PLAYER_1);
        scorer.defunctCoin(PLAYER_1);
        scorer.strikerStrike(PLAYER_1);
        assertThat(scorer.getPlayerScore(PLAYER_1), is(equalTo(-2)));
    }

}

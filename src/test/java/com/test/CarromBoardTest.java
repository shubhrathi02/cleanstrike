package com.test;

import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CarromBoardTest extends TestCase {

    private CarromBoard carromBoard;

    public void setUp() {
        carromBoard = new CarromBoard();
        carromBoard.initialize();
    }

    public void testInitialize(){
        assertThat(carromBoard.getRedCoinsCount(), is(equalTo(1)));
        assertThat(carromBoard.getBlackCoinsCount(), is(equalTo(9)));
    }

    public void testStrike() {
        carromBoard.strike();
        assertThat(carromBoard.getBlackCoinsCount(), is(equalTo(8)));
    }

    public void testMultiStrike() {
        carromBoard.multiStrike();
        assertThat(carromBoard.getBlackCoinsCount(), is(equalTo(7)));
    }

    public void testDefunctCoinBlack() {
        carromBoard.defunctBlackCoin();
        assertThat(carromBoard.getBlackCoinsCount(), is(equalTo(8)));
    }

    public void testDefunctCoinRed() {
        carromBoard.defunctRedCoin();
        assertThat(carromBoard.getRedCoinsCount(), is(equalTo(0)));
    }

    public void testRedStrike() {
        carromBoard.redStrike();
        assertThat(carromBoard.getRedCoinsCount(), is(equalTo(0)));
    }

    public void testHasAllCoinExhausted() {
        carromBoard.multiStrike();
        carromBoard.multiStrike();
        carromBoard.multiStrike();
        carromBoard.multiStrike();
        carromBoard.strike();
        carromBoard.redStrike();
        assertTrue(carromBoard.hasAllCoinsExhausted());
    }

    public void testThrowExceptionWhenRedStrikesAndRedCountIsZero() {
        try {
            carromBoard.redStrike();
            carromBoard.redStrike();
        } catch (Exception e) {
            assertThat(e, is(instanceOf(RuntimeException.class)));
            assertThat(e.getMessage(), is(equalTo("No. of Red coins Available on Board: 0")));
        }
    }

    public void testThrowExceptionWhenBlackStrikesAndBlackCountIsLess() {
        try {
            carromBoard.multiStrike();
            carromBoard.multiStrike();
            carromBoard.multiStrike();
            carromBoard.multiStrike();
            carromBoard.multiStrike();
        } catch (Exception e) {
            assertThat(e, is(instanceOf(RuntimeException.class)));
            assertThat(e.getMessage(), is(equalTo("No. of Black coins Available on Board: 1")));
        }
    }

}

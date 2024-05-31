package org.dtb.oop;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreKeeperTest {
    @Test
    void testOpenFrame() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(7, 2);

        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(9, scoreKeeper.getCurrentScore());
    }

    @Test
    void testOpenFrame2() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(7, 2, 9, 0, 7);

        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(18, scoreKeeper.getCurrentScore());
    }

    @Test
    void testSpareHalfFrame() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(7, 3, 8);

        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(18, scoreKeeper.getCurrentScore());
    }

    @Test
    void testSpareFullFrame() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(7, 3, 8, 1);


        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(27, scoreKeeper.getCurrentScore());
    }

    @Test
    void testStrikesCase1() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(10, 8, 1);

        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(28, scoreKeeper.getCurrentScore());
    }

    @Test
    void testStrikesCase2() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(10, 10, 8);

        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(28, scoreKeeper.getCurrentScore());
    }

    @Test
    void testStrikesCase3() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(10, 10, 8, 1);

        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(56, scoreKeeper.getCurrentScore());
    }

    @Test
    void testPerfectGame() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);

        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(300, scoreKeeper.getCurrentScore());
    }

    @Test
    void testGutterGame() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0);

        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(0, scoreKeeper.getCurrentScore());
    }

    @Test
    void testFullGame3() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(0,0, 0,0, 0,0, 0,0, 7,1, 0,0, 0,0, 0,0, 0,0, 0,0);

        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(8, scoreKeeper.getCurrentScore());
    }

    @Test
    void testHalfPerfect() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5,5);

        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(150, scoreKeeper.getCurrentScore());
    }

    @Test
    void testTypicalGame() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
        List<Integer> game = Arrays.asList(10, 9,1, 5,5, 7,2, 10, 10, 10, 9,1, 8,2, 9,1,10);

        scoreKeeper.scoreGame(game);
        scoreKeeper.printScore();
        assertEquals(197    , scoreKeeper.getCurrentScore());
    }
}

package org.dtb.oop;

import java.util.ArrayList;
import java.util.List;

/**
 * The ScoreKeeper attempts to add a roll it's given to a frame. It does this by passing
 * the roll to the first frame and seeing if it was added to this frame. If it was the
 * ScoreKeeper's job is done. If it was not, the ScoreKeeper must attempt to add the roll
 * to the next frame. This continues until the roll has been added to a frame.

 * This bit of logic to the whole shebang is the result returned from the call to the
 * Frame.addRoll(). When attempting to add a roll to a frame The result is either
 * NOT_ADDED, ADDED_TO_BONUS or ADDED_TO_FRAME. This is important because depending on
 * whether a strike or spare was achieved a roll actually counts in multiple
 * frames. For example, if a strike was rolled on the first throw in frame1 then the next two
 * rolls can live(count) in the first, second and possibly third frames. This quirkiness is
 * what makes this Kata challenging.

 * So if the roll was NOT_ADDED to the frame or if it was ADDED_TO_BONUS the ScoreKeeper
 * must continue on to the next frame and attempt to add the roll there as well.
 */
public class ScoreKeeper {
    private final List<Frame> frames = new ArrayList<>(10);

    public ScoreKeeper() {
        for (int i = 1; i <= 10; i++) {
            frames.add(new Frame(i));
        }
    }

    public void scoreGame(List<Integer> game) {
        game.forEach(this::addRoll);
    }

    public void addRoll(int roll) {
        boolean rollAssigned = false;
        int frameListIdx = 0;

        while (!rollAssigned) {
            Frame refFrame = frames.get(frameListIdx);
            RollResult rollResult = refFrame.addRoll(roll);

            if ((   rollResult.equals(RollResult.ADDED_TO_BONUS) ||
                    rollResult.equals(RollResult.NOT_ADDED)) &&
                    !refFrame.isTenthFrame()) { // process next frame
                frameListIdx++;
            } else { // roll was assigned, we're done
                rollAssigned = true;
            }
        }
    }

    public int getCurrentScore() {
        return frames.stream().mapToInt(Frame::frameScore).sum();
    }

    public void printScore() {
        int gameScore = 0;

        for (Frame frame : frames) {
            gameScore = gameScore + frame.frameScore();
            System.out.print(String.format("[%s %d]", frame.print(), gameScore));
        }

        System.out.println();
    }
}

package org.dtb.oop;

public class Frame {
    private final int number;
    private Integer roll1;
    private Integer roll2;
    private Integer bonus1;
    private Integer bonus2;
    private Integer frameScore;
    private FrameState frameState = FrameState.FIRST_ROLL;


    public Frame(int number) {
        this.number = number;
        this.frameScore = 0;
    }

    /**
     * The purpose of this method is to attempt and add the current roll to this
     * frame. A RoleResult is returned to the ScoreKeeper giving it information
     * to allow it to add the roll in the next frame if necessary.
     *
     * @param roll Number of pins knocked down for a given roll
     * @return RoleResult of ADDED_TO_FRAME, ADDED_TO_BONUS, NOT_ADDED
     */
    public RollResult addRoll(int roll) {
        RollResult rollResult = RollResult.NOT_ADDED;

        /*
            A frame exists in multiple states. The allocation of a roll depends on the current
            state of the frame. Each action taken in a frame's state results in a transition
            to a new state.
         */
        switch (frameState) {
            case FIRST_ROLL:
                roll1 = roll;
                rollResult  = RollResult.ADDED_TO_FRAME;
                frameState = (roll == 10) ? FrameState.STRIKE_BONUS_1 : FrameState.SECOND_ROLL;
                break;
            case SECOND_ROLL:
                roll2 = roll;
                rollResult  = RollResult.ADDED_TO_FRAME;
                frameScore = roll1 + roll2;
                frameState = (frameScore == 10) ? FrameState.SPARE : FrameState.COMPLETE;
                break;
            case SPARE:
                bonus1 = roll;
                rollResult  = RollResult.ADDED_TO_BONUS;
                frameScore = roll1 + roll2 + bonus1;
                frameState = FrameState.COMPLETE;
                break;
            case STRIKE_BONUS_1:
                bonus1 = roll;
                rollResult  = RollResult.ADDED_TO_BONUS;
                frameState = FrameState.STRIKE_BONUS_2;
                break;
            case STRIKE_BONUS_2:
                bonus2 = roll;
                rollResult  = RollResult.ADDED_TO_BONUS;
                frameScore = roll1 + bonus1 + bonus2;
                frameState = FrameState.COMPLETE;
                break;
            case COMPLETE:
                break;
            default:
                System.err.println("Frame is in a undefined state");
                break;
        }

        return rollResult;
    }

    public boolean isTenthFrame() {
        return number == 10;
    }

    public boolean isStrike() {
        return roll1 != null && roll1 == 10;
    }

    public boolean isSpare() {
        return  roll1 != null && roll2 != null && roll1 + roll2 == 10;
    }

    public int frameScore() {
        return frameScore;
    }

    public String print() {
        return String.format("(%d) %s%s%s%s",
                number, printRoll1(), printRoll2(), printBonus1(), printBonus2());
    }

    private String printRoll1() {
        String rtnVal = "";

        if (roll1 != null) {
            rtnVal = isStrike() ? "X" : String.format("%d-", roll1);
        }

        return rtnVal;
    }

    private String printRoll2() {
        String rtnVal = "";

        if (roll2 != null) {
            if (isTenthFrame()) {
                if (isSpare())
                    rtnVal = "/";
                else
                    rtnVal = String.format("%d", roll2);
            } else {
                rtnVal = String.format("%d", roll2);
                if (isSpare())
                    rtnVal = rtnVal + " /";

            }
        }

        return rtnVal;
    }

    private String printBonus1() {
        String rtnVal = "";

        if (isTenthFrame() && (bonus1 != null)) {
                rtnVal = "-";

                if (bonus1 == 10)
                    rtnVal += "X";
                else
                    rtnVal += String.format("%d", bonus1);

        }

        return rtnVal;
    }

    private String printBonus2() {
        String rtnVal = "";

        if (isTenthFrame() && (bonus2 != null)) {
                rtnVal = "-";

                if (bonus2 == 10)
                    rtnVal += "X";
                else
                    rtnVal += String.format("%d", bonus2);

        }

        return rtnVal;
    }

    private enum FrameState {
        FIRST_ROLL, SECOND_ROLL, SPARE, STRIKE_BONUS_1, STRIKE_BONUS_2, COMPLETE
    }
}

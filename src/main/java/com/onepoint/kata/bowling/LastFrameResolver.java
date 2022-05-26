package com.onepoint.kata.bowling;

import com.onepoint.kata.bowling.exception.InvalidFrameException;
import com.onepoint.kata.bowling.model.LastFrame;

public class LastFrameResolver extends FrameResolver {

    private int spareCount = 0;

    @Override
    protected void handleStrike(int attemptsCount) {
        if (attemptsCount > 3) {
            throwInvalidLastFrameException();
        }
        var score = incrementAndGetScore(10);
        if (attemptsCount == 2 && score != 20) {
            // if 2nd is strike, first needs to be a strike as well
            throwInvalidLastFrameException();
        }
        if (attemptsCount == 3) {
            setInstance(new LastFrame(score));
        }
    }

    @Override
    protected void handleSpare(int attemptsCount) {
        var oldScore = getScore();
        spareCount++;
        if (spareCount > 1) {
            // Last frame could only have one spare
            throwInvalidLastFrameException();
        }
        if (attemptsCount == 2 && oldScore < 10) {
            // old score needs to be < 10 : we can not have a Strike then spare
            setScore(10);
            return;
        }
        if (attemptsCount == 3 && oldScore >= 10 && oldScore < 20) {
            // only way to get spare on 3rd attempt is to have first attempt being a Strike X
            // so oldScore needs to be > 10 and less than 20 (first is strike X, second is hit or miss but no strike)
            setInstance(new LastFrame(20));
            return;
        }
        throwInvalidLastFrameException();
    }

    @Override
    protected void handleDigit(int attemptsCount, int pinCount) {
        var score = incrementAndGetScore(pinCount);
        if (attemptsCount > 3 || (attemptsCount == 3 && score - pinCount < 10)) {
            // if 1st and 2nd are less than 10 (no spare no strikes)
            throwInvalidLastFrameException();
        }
        if (attemptsCount == 2) {
            if (score == 10 && pinCount > 0) {
                throwInvalidLastFrameException();
            } else if (score < 10) {
                // first try is not a strike
                setInstance(new LastFrame(score));
            }
        }
        if (attemptsCount == 3) {
            setInstance(new LastFrame(score));
        }
    }

    private void throwInvalidLastFrameException() {
        throw InvalidFrameException.create(InvalidFrameException.InvalidFrameError.BFR103, "Last");
    }
}

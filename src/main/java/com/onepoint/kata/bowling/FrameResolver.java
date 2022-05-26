package com.onepoint.kata.bowling;

import com.onepoint.kata.bowling.exception.InvalidFrameException;
import com.onepoint.kata.bowling.model.BowlingFrame;
import com.onepoint.kata.bowling.model.SimpleFrame;
import com.onepoint.kata.bowling.model.SpareFrame;
import com.onepoint.kata.bowling.model.StrikeFrame;

import static com.onepoint.kata.bowling.BowlingFrameUtils.*;

public class FrameResolver {

    private BowlingFrame instance;

    private int score;

    private int attemptsCount;

    public final boolean isFrameReady() {
        return instance != null;
    }

    public final BowlingFrame getFrame() {
        return instance;
    }

    public final void addAttempt(Character c) {
        if (isFrameReady()) {
            throw InvalidFrameException.create(InvalidFrameException.InvalidFrameError.BFR101, instance.getClass().getSimpleName());
        }
        attemptsCount++;
        switch (c) {
            case STRIKE:
                handleStrike(attemptsCount);
                break;
            case SPARE:
                handleSpare(attemptsCount);
                break;
            case MISS:
                handleDigit(attemptsCount, 0);
                break;
            default:
                handleDigit(attemptsCount, getDigitValue(c));
        }
    }

    protected void handleStrike(int attemptsCount) {
        if (attemptsCount > 1) {
            throw InvalidFrameException.create(InvalidFrameException.InvalidFrameError.BFR103, "Strike");
        }
        setInstance(new StrikeFrame());
    }

    protected void handleSpare(int attemptsCount) {
        if (attemptsCount != 2) {
            throw InvalidFrameException.create(InvalidFrameException.InvalidFrameError.BFR103, "Spare");
        }
        setInstance(new SpareFrame());
    }

    protected void handleDigit(int attemptsCount, int pinCount) {
        if (attemptsCount > 2) {
            throw InvalidFrameException.create(InvalidFrameException.InvalidFrameError.BFR103, "Simple");
        }
        var score = incrementAndGetScore(pinCount);
        if (attemptsCount == 2) {
            setInstance(new SimpleFrame(score));
        }
    }

    protected final int incrementAndGetScore(int count) {
        score += count;
        return score;
    }

    protected final void setInstance(BowlingFrame instance) {
        this.instance = instance;
    }

    protected final int getScore() {
        return score;
    }

    protected final void setScore(int score) {
        this.score = score;
    }
}

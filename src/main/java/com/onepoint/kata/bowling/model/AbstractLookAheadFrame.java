package com.onepoint.kata.bowling.model;

import com.onepoint.kata.bowling.exception.InvalidFrameException;

public abstract class AbstractLookAheadFrame implements BowlingFrame {

    private int lookAheadCountDown;
    private int score;

    public AbstractLookAheadFrame() {
        this.lookAheadCountDown = getLookAheadCountDown();
        this.score = 10;
    }

    protected abstract int getLookAheadCountDown();

    public final void addScore(int pinCount) {
        if (!isFrameScoreIncomplete()) {
            throw InvalidFrameException.create(InvalidFrameException.InvalidFrameError.BFR104);
        }
        this.score += pinCount;
        this.lookAheadCountDown--;
    }

    public final boolean isFrameScoreIncomplete() {
        return lookAheadCountDown > 0;
    }

    @Override
    public final int getScore() {
        return score;
    }
}

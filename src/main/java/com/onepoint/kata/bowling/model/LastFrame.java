package com.onepoint.kata.bowling.model;

import com.onepoint.kata.bowling.exception.InvalidFrameException;

public class LastFrame implements BowlingFrame {

    private final int score;

    public LastFrame(int score) {
        if (score > 30 || score < 0) {
            throw InvalidFrameException.create(InvalidFrameException.InvalidFrameError.BFR100, score, "LAST");
        }
        this.score = score;
    }

    @Override
    public int getScore() {
        return score;
    }
}

package com.onepoint.kata.bowling.model;

import com.onepoint.kata.bowling.exception.InvalidFrameException;

public class SimpleFrame implements BowlingFrame {

    private final int score;

    public SimpleFrame(int score) {
        if (score > 9 || score < 0) {
            throw InvalidFrameException.create(InvalidFrameException.InvalidFrameError.BFR100, score, "SIMPLE");
        }
        this.score = score;
    }

    @Override
    public int getScore() {
        return score;
    }
}

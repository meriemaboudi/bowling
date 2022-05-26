package com.onepoint.kata.bowling.exception;

import lombok.Getter;

public abstract class BowlingException extends RuntimeException {

    @Getter
    private final BowlingError error;

    protected BowlingException(BowlingError error, Object... args) {
        this(null, error, args);
    }

    protected BowlingException(Throwable cause, BowlingError error, Object... args) {
        super(String.format("%s : %s", error.getCode(), extractMessage(error, args)), cause);
        this.error = error;
    }

    private static String extractMessage(BowlingError error, Object... args) {
        return String.format(error.getMessage(), args);
    }
}

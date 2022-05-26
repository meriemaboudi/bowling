package com.onepoint.kata.bowling.exception;

import lombok.RequiredArgsConstructor;

public class InvalidFrameException extends BowlingException {

    protected InvalidFrameException(BowlingError error, Object... args) {
        super(error, args);
    }

    public static InvalidFrameException create(BowlingError error, Object... args) {
        return new InvalidFrameException(error, args);
    }

    @RequiredArgsConstructor
    public enum InvalidFrameError implements BowlingError {

        BFR100("Invalid score %d for %s frame."),
        BFR101("Frame %s is complete, no more attempts allowed."),
        BFR102("Invalid pin symbol %c."),
        BFR103("Invalid %s frame specification."),
        BFR104("Current frame score is complete.");

        private final String messagePattern;

        @Override
        public String getCode() {
            return toString();
        }

        @Override
        public String getMessage() {
            return messagePattern;
        }
    }
}

package com.onepoint.kata.bowling.exception;

import lombok.RequiredArgsConstructor;

public class InvalidGameException extends BowlingException {

    protected InvalidGameException(BowlingError error, Object... args) {
        super(error, args);
    }

    public static InvalidGameException create(BowlingError error, Object... args) {
        return new InvalidGameException(error, args);
    }

    @RequiredArgsConstructor
    public enum InvalidGameError implements BowlingError {

        BGM100("Invalid game description [%s], more than 10 frames detected."),
        BGM101("Invalid game description [%s], less than 10 frames detected."),
        BGM102("Invalid null game");

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

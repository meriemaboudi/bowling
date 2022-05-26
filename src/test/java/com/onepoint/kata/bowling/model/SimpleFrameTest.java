package com.onepoint.kata.bowling.model;

import com.onepoint.kata.bowling.exception.InvalidFrameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SimpleFrameTest {

    @Test
    void validFrameScores() {
        for (int i = 0; i < 10; i++) {
            var frame = new SimpleFrame(i);
            Assertions.assertEquals(i, frame.getScore());
        }
    }

    @ParameterizedTest
    @CsvSource({"10", "-1", "20"})
    void invalidScores(int score) {
        var exception = Assertions.assertThrows(InvalidFrameException.class, () -> new SimpleFrame(score));
        Assertions.assertEquals(InvalidFrameException.InvalidFrameError.BFR100, exception.getError());
    }
}
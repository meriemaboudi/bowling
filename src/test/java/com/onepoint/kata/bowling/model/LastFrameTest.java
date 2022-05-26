package com.onepoint.kata.bowling.model;

import com.onepoint.kata.bowling.exception.InvalidFrameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LastFrameTest {

    @Test
    void validFrameScores() {
        for (int i = 0; i <= 30; i++) {
            var frame = new LastFrame(i);
            Assertions.assertEquals(i, frame.getScore());
        }
    }

    @ParameterizedTest
    @CsvSource({"31", "-1", "40"})
    void invalidScores(int score) {
        var exception = Assertions.assertThrows(InvalidFrameException.class, () -> new SimpleFrame(score));
        Assertions.assertSame(InvalidFrameException.InvalidFrameError.BFR100, exception.getError());
    }
}
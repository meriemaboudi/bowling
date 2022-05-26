package com.onepoint.kata.bowling.model;

import com.onepoint.kata.bowling.exception.InvalidFrameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

abstract class AbstractLookAheadFrameTest<T extends AbstractLookAheadFrame> {

    @Test
    void initialScore() {
        T instance = getInstance();
        Assertions.assertEquals(10, instance.getScore());
    }

    @Test
    void addLookAheadScores() {
        T instance = getInstance();
        var score = 10;
        int count = expectedLookAheadDepth();
        while (instance.isFrameScoreIncomplete() && count > 0) {
            instance.addScore(4);
            score += 4;
            count--;
        }
        Assertions.assertEquals(score, instance.getScore());
        Assertions.assertFalse(instance.isFrameScoreIncomplete());
        Assertions.assertEquals(0, count);
    }

    @Test
    void shouldFailToAddScoreIfScoreComplete() {
        T instance = getInstance();
        int count = expectedLookAheadDepth();
        while (instance.isFrameScoreIncomplete()) {
            instance.addScore(4);
            count--;
        }
        Assertions.assertEquals(0, count);
        var exception = Assertions.assertThrows(InvalidFrameException.class, () -> instance.addScore(4));
        Assertions.assertEquals(InvalidFrameException.InvalidFrameError.BFR104, exception.getError());
    }

    protected abstract int expectedLookAheadDepth();

    protected abstract T getInstance();
}
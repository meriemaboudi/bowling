package com.onepoint.kata.bowling;

import com.onepoint.kata.bowling.exception.InvalidFrameException;
import com.onepoint.kata.bowling.model.BowlingFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FrameResolverTest {

    @ParameterizedTest
    @CsvSource({
            "X, 10", "9/, 10", "42, 6", "-/, 10", "--, 0"
    })
    void validFrames(String frameDescription, int expectedScore) {
        var resolver = new FrameResolver();
        for (Character c : frameDescription.toCharArray()) {
            Assertions.assertFalse(resolver.isFrameReady());
            resolver.addAttempt(c);
        }
        Assertions.assertTrue(resolver.isFrameReady());
        BowlingFrame frame = resolver.getFrame();
        Assertions.assertNotNull(frame);
        Assertions.assertEquals(expectedScore, frame.getScore());
    }

    @ParameterizedTest
    @CsvSource({
            "XX, BFR101", "/, BFR103", "X/, BFR101", "X3, BFR101", "0, BFR102", "91, BFR100", "X/, BFR101"
    })
    void invalidFrames(String frameDescription, String code) {
        var resolver = new FrameResolver();
        var exception = Assertions.assertThrows(InvalidFrameException.class, () -> addAttempts(frameDescription, resolver));
        Assertions.assertEquals(code, exception.getError().getCode());
    }

    @ParameterizedTest
    @CsvSource({
            "4"
    })
    void incompleteFrames(String frameDescription) {
        var resolver = new FrameResolver();
        addAttempts(frameDescription, resolver);
        Assertions.assertFalse(resolver.isFrameReady());
    }

    private void addAttempts(String frameDescription, FrameResolver resolver) {
        for (Character c : frameDescription.toCharArray()) {
            resolver.addAttempt(c);
        }
    }
}

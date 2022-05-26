package com.onepoint.kata.bowling;

import com.onepoint.kata.bowling.exception.InvalidFrameException;
import com.onepoint.kata.bowling.model.LastFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LastFrameResolverTest {

    @ParameterizedTest
    @CsvSource({
            "9/X, 20", "X7/, 20", "XXX, 30", "XX6, 26", "32, 5", "--, 0", "-9, 9", "8-, 8", "X-/, 20",
            "X--, 10"
    })
    void validLastFrames(String lastFrameDescription, int expectedScore) {
        var resolver = new LastFrameResolver();
        for (Character c : lastFrameDescription.toCharArray()) {
            Assertions.assertFalse(resolver.isFrameReady());
            resolver.addAttempt(c);
        }
        Assertions.assertTrue(resolver.isFrameReady());
        Assertions.assertNotNull(resolver.getFrame());
        var lastFrame = Assertions.assertInstanceOf(LastFrame.class, resolver.getFrame());
        Assertions.assertEquals(expectedScore, lastFrame.getScore());
    }

    @ParameterizedTest
    @CsvSource({
            "222, BFR101", "/, BFR103", "82, BFR103", "9//, BFR103", "00, BFR102",
            "---, BFR101", "XX/, BFR103", "X/, BFR103", "XXXX, BFR101", "4/XX, BFR101", "-X, BFR103"
    })
    void invalidLastFrames(String lastFrameDescription, String code) {
        var resolver = new LastFrameResolver();
        var exception = Assertions.assertThrows(InvalidFrameException.class, () -> {
            for (Character c : lastFrameDescription.toCharArray()) {
                resolver.addAttempt(c);
            }
        });
        Assertions.assertEquals(code, exception.getError().getCode());
    }

    @ParameterizedTest
    @CsvSource({
            "XX", "9/", "X5", "X-", "-", "X", "-/"
    })
    void incompleteLastFrames(String lastFrameDescription) {
        var resolver = new LastFrameResolver();
        for (Character c : lastFrameDescription.toCharArray()) {
            resolver.addAttempt(c);
        }
        Assertions.assertFalse(resolver.isFrameReady());
    }
}

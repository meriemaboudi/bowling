package com.onepoint.kata.bowling;

import com.onepoint.kata.bowling.exception.InvalidFrameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BowlingFrameUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "5, 5",
            "X, 10",
            "-, 0",
            "/, 10"
    })
    void validPinValues(char symbol, int expectedValue) {
        var actualValue = BowlingFrameUtils.getDigitValue(symbol);
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "a",
            "x",
            "(",
            "j"
    })
    void invalidPinValues(char symbol) {
        var error = Assertions.assertThrows(InvalidFrameException.class, () -> BowlingFrameUtils.getDigitValue(symbol));
        Assertions.assertSame(InvalidFrameException.InvalidFrameError.BFR102, error.getError());
    }
}

package com.onepoint.kata.bowling;

import com.onepoint.kata.bowling.exception.InvalidGameException;
import com.onepoint.kata.bowling.model.BowlingFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.onepoint.kata.bowling.TenPinBowlingApplicationMain.computeFrames;

class TenPinBowlingApplicationMainTest {

    @ParameterizedTest
    @CsvSource({
            "xxxxxxxxxxxx, 300",
            "9-9-9-9-9-9-9-9-9-9-, 90",
            "5/5/5/5/5/5/5/5/5/5/5, 150",
            "X-/X-/X-/X-/X7/X, 207"
    })
    void validGames(String game, int expectedScore) {
        var frames = computeFrames(game);
        Assertions.assertEquals(10, frames.size());
        Assertions.assertEquals(expectedScore, frames.stream().map(BowlingFrame::getScore).reduce(0, Integer::sum));
    }

    @ParameterizedTest
    @CsvSource({
            "xxxxxxxxxxxxx, BGM100",
            "9-9-9-9-9-9-9-9-9-9-x, BGM100",
            "5/5/5/5/5/5/5/5/5/5/, BGM101",
            "X-/X-/X-/X-/X7/, BGM101",
            "'', BGM101",
            ", BGM102"
    })
    void invalidGames(String game, String errorCode) {
        var exception = Assertions.assertThrows(InvalidGameException.class, () -> computeFrames(game));
        Assertions.assertEquals(errorCode, exception.getError().getCode());
    }
}

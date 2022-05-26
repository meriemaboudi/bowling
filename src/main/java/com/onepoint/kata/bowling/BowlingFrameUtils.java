package com.onepoint.kata.bowling;

import com.onepoint.kata.bowling.exception.InvalidFrameException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BowlingFrameUtils {

    public static final char SPARE = '/';
    public static final char STRIKE = 'X';
    public static final char MISS = '-';

    public static int getDigitValue(Character symbol) {
        switch (symbol) {
            case SPARE:
            case STRIKE:
                return 10;
            case MISS:
                return 0;
            default:
                var pinCount = Character.digit(symbol, 10);
                if (pinCount <= 0 || pinCount > 9) {
                    throw InvalidFrameException.create(InvalidFrameException.InvalidFrameError.BFR102, symbol);
                }
                return pinCount;
        }
    }
}

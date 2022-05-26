package com.onepoint.kata.bowling;

import com.onepoint.kata.bowling.exception.BowlingException;
import com.onepoint.kata.bowling.exception.InvalidGameException;
import com.onepoint.kata.bowling.model.AbstractLookAheadFrame;
import com.onepoint.kata.bowling.model.BowlingFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.onepoint.kata.bowling.exception.InvalidGameException.InvalidGameError;

public class TenPinBowlingApplicationMain {

    public static void main(String... games) {
        int gameIdx = 0;
        for (String game : games) {
            System.out.printf("Game #%d : %s%n", ++gameIdx, game);
            List<BowlingFrame> frames;
            try {
                frames = computeFrames(game.toUpperCase());
            } catch (BowlingException be) {
                System.out.printf("An error occurred in game description [%s] %s%n", be.getClass().getSimpleName(), be.getMessage());
                continue;
            }
            int frameIdx = 0;
            int score = 0;
            for (BowlingFrame frame : frames) {
                System.out.printf("\tFrame #%d, score : %d %n", ++frameIdx, frame.getScore());
                score += frame.getScore();
            }
            System.out.printf("Total Score : %d%n", score);
        }
    }

    public static List<BowlingFrame> computeFrames(String actualGame) {
        if (actualGame == null) {
            throw InvalidGameException.create(InvalidGameError.BGM102, actualGame);
        }
        var game = actualGame.toUpperCase();
        List<AbstractLookAheadFrame> lookAheadFrames = new ArrayList<>();
        var bowlingFrames = new ArrayList<BowlingFrame>();
        var frameResolver = new FrameResolver();
        for (Character c : game.toCharArray()) {
            if (frameResolver == null) {
                throw InvalidGameException.create(InvalidGameError.BGM100, actualGame);
            }
            frameResolver.addAttempt(c);
            int pinCount = BowlingFrameUtils.getDigitValue(c);
            for (AbstractLookAheadFrame lookAheadFrame : lookAheadFrames) {
                lookAheadFrame.addScore(pinCount);
            }
            lookAheadFrames = lookAheadFrames.stream().filter(AbstractLookAheadFrame::isFrameScoreIncomplete).collect(Collectors.toList());
            if (frameResolver.isFrameReady()) {
                var frame = frameResolver.getFrame();
                bowlingFrames.add(frame);
                if (frame instanceof AbstractLookAheadFrame) {
                    lookAheadFrames.add((AbstractLookAheadFrame) frame);
                }
                if (bowlingFrames.size() == 10) {
                    // mark the end of the game
                    frameResolver = null;
                } else {
                    frameResolver = bowlingFrames.size() < 9 ? new FrameResolver() : new LastFrameResolver();
                }
            }
        }
        if (bowlingFrames.size() < 10) {
            throw InvalidGameException.create(InvalidGameError.BGM101, actualGame);
        }
        if (!lookAheadFrames.isEmpty()) {
            // condition should never be reached, unless code changes which means this is a regression
            throw new IllegalStateException("Should never happen.");
        }
        return bowlingFrames;
    }
}

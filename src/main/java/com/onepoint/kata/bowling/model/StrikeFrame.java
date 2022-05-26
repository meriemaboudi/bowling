package com.onepoint.kata.bowling.model;

public class StrikeFrame extends AbstractLookAheadFrame {

    @Override
    protected int getLookAheadCountDown() {
        return 2;
    }
}

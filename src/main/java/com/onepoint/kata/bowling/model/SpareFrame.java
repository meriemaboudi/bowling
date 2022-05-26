package com.onepoint.kata.bowling.model;

public class SpareFrame extends AbstractLookAheadFrame {

    @Override
    protected int getLookAheadCountDown() {
        return 1;
    }
}

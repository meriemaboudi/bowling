package com.onepoint.kata.bowling.model;

class SpareFrameTest extends AbstractLookAheadFrameTest<SpareFrame> {

    @Override
    protected int expectedLookAheadDepth() {
        return 1;
    }

    @Override
    protected SpareFrame getInstance() {
        return new SpareFrame();
    }
}
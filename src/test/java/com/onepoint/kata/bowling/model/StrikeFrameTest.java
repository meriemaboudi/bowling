package com.onepoint.kata.bowling.model;

class StrikeFrameTest extends AbstractLookAheadFrameTest<StrikeFrame> {

    @Override
    protected int expectedLookAheadDepth() {
        return 2;
    }

    @Override
    protected StrikeFrame getInstance() {
        return new StrikeFrame();
    }
}
package com.bullish.md.engine;

import com.bullish.md.datamodel.Tick;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedIncTickMDEngineTest {
    @Test
    void test_generate_tick(){
        FixedIncTickMDEngine tickEngine = new FixedIncTickMDEngine("ASDF", 1400.0);
        assertEquals(new Tick("ASDF", 1400), tickEngine.generateTick());
        assertEquals(new Tick("ASDF", 1410), tickEngine.generateTick());
        assertEquals(new Tick("ASDF", 1420), tickEngine.generateTick());
    }

}
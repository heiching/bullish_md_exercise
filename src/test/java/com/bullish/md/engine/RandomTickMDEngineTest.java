package com.bullish.md.engine;

import com.bullish.md.datamodel.Tick;
import com.bullish.md.utils.TickCountInterface;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomTickMDEngineTest {

    @Setter
    public static class MockTickCounter implements TickCountInterface {

        private int tickCount = 0;

        @Override
        public void tick() {
            tickCount++;
        }

        @Override
        public int getTickCount() {
            return tickCount;
        }

    }

    @Test
    void testGenerateTick() {

        RandomTickMDEngine engine = new RandomTickMDEngine("ASDF", 10000.0, 1000);
        MockTickCounter counter = new MockTickCounter();
        engine.setTickCounter(counter);
        engine.setRandGen(()-> 0.5);

        // When tick count is odd
        counter.setTickCount(3);
        Tick expected = new Tick("ASDF", 9500.0);
        assertEquals(expected, engine.generateTick());

        // When tick count is even
        counter.setTickCount(4);
        expected = new Tick("ASDF", 10000.0);
        assertEquals(expected, engine.generateTick());
    }
}
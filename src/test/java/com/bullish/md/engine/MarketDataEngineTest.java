package com.bullish.md.engine;

import com.bullish.md.MarketDataFeed;
import com.bullish.md.datamodel.Tick;
import com.bullish.md.utils.TickCountInterface;
import org.junit.jupiter.api.Test;

import static com.bullish.md.engine.MarketDataEngine.STOP_TICK_COUNT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MarketDataEngineTest {

    @Test
    void test_addListener() {
        MarketDataEngine engine = new MarketDataEngine("Test") {
            @Override
            protected Tick generateTick() {
                return null;
            }
        };

        MarketDataFeed mockFeed = mock(MarketDataFeed.class);

        engine.addListener(mockFeed);
        assertTrue(engine.getListeners().contains(mockFeed), "Listener is inside MDEngine after addListener");

    }

    @Test
    void test_run() {
        final int[] genTickCalled = {0};
        Tick tick = mock(Tick.class);
        MarketDataFeed mockFeed = mock(MarketDataFeed.class);
        MarketDataEngine engine = new MarketDataEngine("Test") {
            @Override
            protected Tick generateTick() {
                genTickCalled[0]++;
                return tick;
            }
        };
        engine.addListener(mockFeed);
        engine.run();
        assertEquals(genTickCalled[0], 100);
        verify(mockFeed, times(STOP_TICK_COUNT)).onTick(tick);
    }
}
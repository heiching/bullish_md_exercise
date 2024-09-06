package com.bullish.md;

import com.bullish.md.datamodel.SymbolCurrentState;
import com.bullish.md.datamodel.Tick;
import com.bullish.md.engine.MarketDataEngine;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MarketDataFeedTest {

    @Test
    void test_onTick_two_new_symbols() {
        MarketDataFeed feed = new MarketDataFeed();
        feed.onTick(new Tick("ASDF", 1.234));
        feed.onTick(new Tick("QWER", 3.456));

        SymbolCurrentState expectedASDF = new SymbolCurrentState("ASDF", 1.234);
        SymbolCurrentState expectedQWER = new SymbolCurrentState("QWER", 3.456);

        assertEquals(expectedASDF, feed.getSymbolCurrentState("ASDF"));
        assertEquals(expectedQWER, feed.getSymbolCurrentState("QWER"));

        feed.onTick(new Tick("ASDF", 1.200));
        expectedASDF.setLow(1.200);
        expectedASDF.setCurrent(1.200);
        assertEquals(expectedASDF, feed.getSymbolCurrentState("ASDF"));

    }

    @Test
    void test_onTick_update_low(){
        MarketDataFeed feed = new MarketDataFeed();
        SymbolCurrentState expectedASDF = new SymbolCurrentState("ASDF", 1.234);
        feed.onTick(new Tick("ASDF", 1.234));
        feed.onTick(new Tick("ASDF", 1.200));

        expectedASDF.setLow(1.200);
        expectedASDF.setCurrent(1.200);
        assertEquals(expectedASDF, feed.getSymbolCurrentState("ASDF"));
    }

    @Test
    void test_onTick_update_high(){
        MarketDataFeed feed = new MarketDataFeed();
        SymbolCurrentState expectedASDF = new SymbolCurrentState("ASDF", 1.234);
        feed.onTick(new Tick("ASDF", 1.234));
        feed.onTick(new Tick("ASDF", 1.400));

        expectedASDF.setHigh(1.400);
        expectedASDF.setCurrent(1.400);
        assertEquals(expectedASDF, feed.getSymbolCurrentState("ASDF"));
    }
}
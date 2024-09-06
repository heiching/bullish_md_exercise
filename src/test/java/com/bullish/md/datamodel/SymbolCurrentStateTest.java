package com.bullish.md.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymbolCurrentStateTest {

    @Test
    void test_updatePrice() {

        // Instantiation
        SymbolCurrentState currState = new SymbolCurrentState("ASDF", 3.456);
        assertEquals("ASDF", currState.getSymbol(), "Symbol incorrect during instantiation");
        assertEquals(3.456, currState.getCurrent(), "Current incorrect during instantiation");
        assertEquals(3.456, currState.getLow(), "Low incorrect during instantiation");
        assertEquals(3.456, currState.getHigh(), "High incorrect during instantiation");

        // First update (Low)
        currState.updatePrice(1.234);
        assertEquals("ASDF", currState.getSymbol(), "Symbol incorrect during low");
        assertEquals(1.234, currState.getCurrent(), "Current incorrect during low");
        assertEquals(1.234, currState.getLow(), "Low incorrect during low");
        assertEquals(3.456, currState.getHigh(), "High incorrect during low");

        // 2nd update (High)
        currState.updatePrice(7.891);
        assertEquals("ASDF", currState.getSymbol(), "Symbol incorrect during high");
        assertEquals(7.891, currState.getCurrent(), "Current incorrect during high");
        assertEquals(1.234, currState.getLow(), "Low incorrect during high");
        assertEquals(7.891, currState.getHigh(), "High incorrect during high");

        // 3rd update (In-between)
        currState.updatePrice(5.687);
        assertEquals("ASDF", currState.getSymbol(), "Symbol incorrect during in-between");
        assertEquals(5.687, currState.getCurrent(), "Current incorrect during in-between");
        assertEquals(1.234, currState.getLow(), "Low incorrect during in-between");
        assertEquals(7.891, currState.getHigh(), "High incorrect during in-between");

    }
}
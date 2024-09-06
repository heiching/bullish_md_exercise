package com.bullish.md.engine;

import com.bullish.md.datamodel.Tick;

public class FixedIncTickMDEngine extends MarketDataEngine {

    private double price;

    public FixedIncTickMDEngine(String market, double initPrice) {
        super(market);
        this.price = initPrice;
    }

    @Override
    protected Tick generateTick() {
        Tick newTick =  new Tick(market, price);
        price += 10;
        return newTick;
    }
}

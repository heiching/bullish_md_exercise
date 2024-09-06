package com.bullish.md.engine;

import com.bullish.md.datamodel.Tick;
import com.bullish.md.utils.RandNumGenInterface;
import lombok.Setter;

public class RandomTickMDEngine extends MarketDataEngine {
    public static class NativeRandomNumberGen implements RandNumGenInterface {

        @Override
        public double getDouble() {
            return Math.random();
        }
    }

    private double price;
    private final double maxChange;
    @Setter
    private RandNumGenInterface randGen;

    public RandomTickMDEngine(String market, double initPrice, double maxChange) {
        super(market);
        this.price = initPrice;
        this.maxChange = maxChange;
        randGen = new NativeRandomNumberGen();
    }

    @Override
    protected Tick generateTick() {
        double change = randGen.getDouble() * maxChange;
        price = tickCount.getTickCount() % 2 == 0 ? price + change : price - change;
        return new Tick(market, price);

    }
}

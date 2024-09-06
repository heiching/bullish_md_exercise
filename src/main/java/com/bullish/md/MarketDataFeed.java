package com.bullish.md;

import com.bullish.md.datamodel.SymbolCurrentState;
import com.bullish.md.datamodel.Tick;
import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class MarketDataFeed {

    private final Map<String, SymbolCurrentState> currentStates = new ConcurrentHashMap<>();

    public void onTick(Tick tick) {
        String market = tick.market();
        double price = tick.price();
        SymbolCurrentState currState = currentStates.compute(market, (k, v) -> (v == null) ?
                new SymbolCurrentState(market, price) : v.updatePrice(price));
        log.info(currState);
    }

    public SymbolCurrentState getSymbolCurrentState(String symbol){
        return currentStates.get(symbol);
    }

}

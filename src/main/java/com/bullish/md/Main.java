package com.bullish.md;

import com.bullish.md.engine.MarketDataEngine;
import com.bullish.md.engine.FixedIncTickMDEngine;
import com.bullish.md.engine.RandomTickMDEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        List<MarketDataEngine> engines = new ArrayList<>();

        MarketDataEngine btcMde = new FixedIncTickMDEngine("BTC-USD", 20000);
        MarketDataEngine ethMde = //new FixedIncTickMDEngine("ETH-USD", 5000);
                new RandomTickMDEngine("ETH-USD", 5000, 100);

        MarketDataFeed feed = new MarketDataFeed();

        engines.add(btcMde);
        engines.add(ethMde);

        ExecutorService executor = Executors.newCachedThreadPool();

        Main main = new Main();
        main.run(executor, engines, feed);



    }

    public void run(ExecutorService executor, List<MarketDataEngine> engines, MarketDataFeed feed) throws InterruptedException {

        engines.forEach((engine) -> engine.addListener(feed));
        engines.forEach(executor::execute);

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
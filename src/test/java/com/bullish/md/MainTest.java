package com.bullish.md;

import com.bullish.md.engine.MarketDataEngine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

class MainTest {
    @Test
    void test_run() throws InterruptedException {
        ExecutorService executorService = mock(ExecutorService.class);
        List<MarketDataEngine> engines = new ArrayList<>();
        MarketDataEngine mockEngine1 = mock(MarketDataEngine.class);
        MarketDataEngine mockEngine2 = mock(MarketDataEngine.class);
        engines.add(mockEngine1);
        engines.add(mockEngine2);
        MarketDataFeed marketDataFeed = mock(MarketDataFeed.class);

        Main main = new Main();

        main.run(executorService, engines, marketDataFeed);

        verify(mockEngine1).addListener(marketDataFeed);
        verify(mockEngine2).addListener(marketDataFeed);

        verify(executorService).execute(mockEngine1);
        verify(executorService).execute(mockEngine2);


    }

}
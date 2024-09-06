package com.bullish.md.bdd;

import com.bullish.md.datamodel.Tick;
import com.bullish.md.engine.MarketDataEngine;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions{
    private final Map<String, MarketDataEngine> engines;
    private ExecutorService executorService;

    public StepDefinitions(){
        this.engines = new HashMap<>();
    }

    @Given("generic market data engine {string}")
    public void genericMarketDataEngine(String name) {
        MarketDataEngine marketDataEngine = new MarketDataEngine(name) {
            @Override
            protected Tick generateTick() {
                return null;
            }
        };
        engines.put(name, marketDataEngine);
    }

    @Given("generic executor")
    public void genericExecutor() {
        executorService = Executors.newCachedThreadPool();
    }

    @When("run engine {string} in executor")
    public void runEngine(String engineName) {
        executorService.execute(engines.get(engineName));
    }

    @When("all engines stop")
    public void allEngineStops() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(20, TimeUnit.SECONDS);
    }

    @Then("engine {string} stops with defined number of ticks")
    public void stopEngineAfter100Ticks(String engineName) {
        int tickCount = engines.get(engineName).getTickCount();
        assertEquals(MarketDataEngine.STOP_TICK_COUNT, tickCount);
    }
}

Feature: MarketDataEngine

  Scenario: One engine
    Given generic market data engine "A"
    And generic executor
    When run engine "A" in executor
    And all engines stop
    Then engine "A" stops with defined number of ticks

  Scenario: Multiple engines
    Given generic market data engine "C"
    And generic market data engine "B"
    And generic executor
    When run engine "C" in executor
    And run engine "B" in executor
    And all engines stop
    Then engine "C" stops with defined number of ticks
    And engine "B" stops with defined number of ticks
# Bullish Market Data System Exercise by Patrick Leung

Requires Gradle 8.5+ and Java 21

## To build:

Windows: ./gradlew.bat build
POSIX: ./gradlew build

## To run:

Windows: ./gradlew.bat run
POSIX: ./gradlew run

## To run tests:

Windows: ./gradlew.bat test
POSIX: ./gradlew test

Raw Test results can be found in build/test-results
Test results in HTML format can be viewed in build/reports/tests/test/index.html
JaCoCo coverage report can be viewed in build/reports/jacoco/test/html/index.html

## Assumptions:

1. "Market" and "Symbol" are synonyms according to provided document.
    Tick can be enhanced to separate market and symbol to allow multiple symbols from same market/exchange/venue 
2. Same symbol only comes from 1 market.  Each MarketDataEngine is running on 1 thread meaning each symbol should only have 1 thread updating.  ConcurrentHashMap is used to allow future enhancement of supporting same symbol from multiple markets.
3. Spring framework is not used because
   - This project doesn't require web, jdbc, etc framework
   - Reduce memory, execution time overhead
4. For random tick engine, calculate "variance" by multiple [0..1] with max change
5. Timestamp/tick delay is not in consideration.  However, this can be easily added by including Thread.sleep(rand()) in MarketDataEngine
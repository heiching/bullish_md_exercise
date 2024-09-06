package com.bullish.md.datamodel;

import lombok.Data;

@Data
public class SymbolCurrentState {
    private String symbol;
    private double low;
    private double high;
    private double current;

    public SymbolCurrentState(String symbol, double price) {
        this.symbol = symbol;
        this.low = price;
        this.high = price;
        this.current = price;
    }

    public SymbolCurrentState updatePrice(double price){
        current = price;
        if (price < low) {
            low = price;
        }
        if (price > high) {
            high = price;
        }
        return this;
    }
}

package com.bullish.md.engine;

import com.bullish.md.MarketDataFeed;
import com.bullish.md.datamodel.Tick;
import com.bullish.md.utils.TickCountInterface;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class MarketDataEngine implements Runnable{

    @Getter
    public static class NormalTickCounter implements TickCountInterface{
        private transient int tickCount = 0;
        @Override
        public void tick() {
            tickCount++;
        }
    }

    public static final int STOP_TICK_COUNT = 100;

    protected TickCountInterface tickCount;
    protected final String market;
    @Getter
    protected List<MarketDataFeed> listeners = new ArrayList<>();

    protected MarketDataEngine(String market){
        this.market = market;
        this.tickCount = new NormalTickCounter();
    }

    protected void setTickCounter(TickCountInterface tickCount){
        this.tickCount = tickCount;
    }

    public void addListener(MarketDataFeed md){
        listeners.add(md);
    }

    protected abstract Tick generateTick();

    public int getTickCount(){
        return tickCount.getTickCount();
    }

    @Override
    public void run(){
        boolean active = true;
        while(active){
            tickCount.tick();
            Tick tick = generateTick();
            for(MarketDataFeed listener : listeners){
                listener.onTick(tick);
            }
            if (tickCount.getTickCount() >= STOP_TICK_COUNT) {
                active = false;
            }
        }
    }

}

package com.randallgr.cryptocurrencytracker;

public interface Updateable {

    void updateCurrentPrice();
    void updateIntradayPrices();
    void updateDailyPrices();
}

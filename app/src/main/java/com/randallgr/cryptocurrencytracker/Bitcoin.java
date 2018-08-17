package com.randallgr.cryptocurrencytracker;

import android.content.Context;

public class Bitcoin extends Cryptocurrency {

    public Bitcoin(Context c) {
        super(c);

        this.name = "Bitcoin";
        this.ticker = "BTC";
        fetchAll();
    }
}

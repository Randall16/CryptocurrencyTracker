package com.randallgr.cryptocurrencytracker;

import android.content.Context;

public class Bitcoin extends Cryptocurrency {

    public Bitcoin(Context c, Updateable re) {
        super(c,re);

        this.name = "Bitcoin";
        this.ticker = "BTC";
        fetchAll();
    }
}

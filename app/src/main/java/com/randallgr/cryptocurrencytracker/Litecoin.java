package com.randallgr.cryptocurrencytracker;

import android.content.Context;

public class Litecoin extends Cryptocurrency {

    private static final String NAME = "Litecoin", TICKER = "LTC";

    public Litecoin(Context c) {
        super(c);
        fetchAll();
    }

    public Litecoin(Context c, OnFetchesCompleteListener listener) {
        super(c);
        this.mListener = listener;

        fetchAll();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getTicker() {
        return TICKER;
    }
}

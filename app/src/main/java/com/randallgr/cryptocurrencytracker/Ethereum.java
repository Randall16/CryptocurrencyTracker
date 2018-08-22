package com.randallgr.cryptocurrencytracker;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class Ethereum extends Cryptocurrency {

    private static final String NAME = "Ethereum", TICKER = "ETH";



    public Ethereum(Context c) {
        super(c);

        fetchAll();
    }

    public Ethereum(Context c, OnFetchesCompleteListener listener) {
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

package com.randallgr.cryptocurrencytracker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;

public class Bitcoin extends Cryptocurrency {

    private static final String NAME = "Bitcoin", TICKER = "BTC";

    public Bitcoin(Context c) {
        super(c);
        fetchAll();
    }

    public Bitcoin(Context c, OnFetchesCompleteListener listener) {
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

package com.randallgr.cryptocurrencytracker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;

public class Bitcoin extends Cryptocurrency {


    public Bitcoin(Context c) {
        super(c);

        this.name = "Bitcoin";
        this.ticker = "BTC";
        fetchAll();
    }

    public Bitcoin(Context c, OnFetchesCompleteListener listener) {
        super(c);

        this.name = "Bitcoin";
        this.ticker = "BTC";
        this.mListener = listener;
        fetchAll();
    }
}

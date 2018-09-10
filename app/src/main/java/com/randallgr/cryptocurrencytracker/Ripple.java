package com.randallgr.cryptocurrencytracker;

import android.content.Context;

public class Ripple extends Cryptocurrency {

    public static final int logo = R.drawable.logo_ripple;
    private static final String NAME = "Ripple", TICKER = "XRP";

    public Ripple(Context c) {
        super(c);
        fetchAll();
    }

    public Ripple(Context c, OnFetchesCompleteListener listener) {
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

    @Override
    public int getLogoID() {
        return logo;
    }
}

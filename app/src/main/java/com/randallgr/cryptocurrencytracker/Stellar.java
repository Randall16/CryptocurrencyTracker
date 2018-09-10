package com.randallgr.cryptocurrencytracker;

import android.content.Context;

public class Stellar extends Cryptocurrency {

    public static final int logo = R.drawable.logo_stellar;
    private static final String NAME = "Stellar", TICKER = "XLM";

    public Stellar(Context c) {
        super(c);
        fetchAll();
    }

    public Stellar(Context c, OnFetchesCompleteListener listener) {
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

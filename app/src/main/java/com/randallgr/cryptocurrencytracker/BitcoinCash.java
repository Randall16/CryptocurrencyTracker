package com.randallgr.cryptocurrencytracker;

import android.content.Context;

public class BitcoinCash extends Cryptocurrency {

    public static final int logo = R.drawable.logo_bitcoin_cash;
    private static final String NAME = "Bitcoin Cash", TICKER = "BCH";

    public BitcoinCash(Context c) {
        super(c);
        fetchAll();
    }

    public BitcoinCash(Context c, OnFetchesCompleteListener listener) {
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

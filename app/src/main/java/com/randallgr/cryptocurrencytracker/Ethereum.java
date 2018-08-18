package com.randallgr.cryptocurrencytracker;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class Ethereum extends Cryptocurrency {


    public Ethereum(Context c) {
        super(c);

        name = "Ethereum";
        ticker = "ETH";

        fetchAll();
    }

    public Ethereum(Context c, OnFetchesCompleteListener listener) {
        super(c);

        this.name = "Ethereum";
        this.ticker = "ETH";
        this.mListener = listener;

        fetchAll();
    }
}

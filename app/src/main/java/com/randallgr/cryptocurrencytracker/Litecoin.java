package com.randallgr.cryptocurrencytracker;

import android.content.Context;

public class Litecoin extends Cryptocurrency {

    public Litecoin(Context c) {
        super(c);

        this.name = "Litecoin";
        this.ticker = "LTC";

        fetchAll();
    }

    public Litecoin(Context c, OnFetchesCompleteListener listener) {
        super(c);

        this.name = "Litecoin";
        this.ticker = "LTC";

        this.mListener = listener;

        fetchAll();
    }
}

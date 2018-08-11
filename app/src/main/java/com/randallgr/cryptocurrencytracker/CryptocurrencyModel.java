package com.randallgr.cryptocurrencytracker;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;


public class CryptocurrencyModel extends AndroidViewModel implements Updateable {

    public Cryptocurrency cryptoArray[];
    public Cryptocurrency selectededCrypto;

    private Application application;


    public CryptocurrencyModel(@NonNull Application applicationParam) {
        super(applicationParam);

        application = applicationParam;

        cryptoArray = new Cryptocurrency[3];
        selectededCrypto = new Bitcoin(application, this);
    }


    @Override
    public void updateCurrentPrice() {
        Log.v("tester", "working");
    }

    @Override
    public void updateIntradayPrices() {

    }

    @Override
    public void updateDailyPrices() {

    }


}

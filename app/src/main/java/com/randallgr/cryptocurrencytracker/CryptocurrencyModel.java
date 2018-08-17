package com.randallgr.cryptocurrencytracker;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.MutableBoolean;


public class CryptocurrencyModel extends AndroidViewModel implements Updateable {

    public Cryptocurrency selectededCrypto;
    public HomeFragment homeFragment;

    public MutableLiveData<Boolean> isLoading;

    private Application application;


    public CryptocurrencyModel(@NonNull Application applicationParam) {
        super(applicationParam);

        Log.v("tester", "running");

        application = applicationParam;

        homeFragment = new HomeFragment();
        selectededCrypto = new Bitcoin(application, this);


        isLoading = new MutableLiveData<>();
        isLoading.setValue(false);
        isLoading.setValue(true);
    }


    @Override
    public void fetchesComplete() {
        homeFragment.updateHomeFragment(selectededCrypto);
        isLoading.setValue(!isLoading.getValue());
    }


}

package com.randallgr.cryptocurrencytracker;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public abstract class Cryptocurrency {

    protected String name, ticker, domesticCurrency;
    protected double currentPrice, hourChange, dayChange, weekChange, monthChange, yearChange;
    protected  double [] dailyPrices, intraDayPrices, intraHourPrices;
    protected OnFetchesCompleteListener mListener;

    private static RequestQueue volleyQueue = null;
    private JsonObjectRequest jsonReqCurrentPrice, jsonReqDailyPrices, jsonReqIntraDayPrices;
    private int fetchCounter;
    private static final String EXCHANGE = "CCCAGG";


    // constructor
    public Cryptocurrency(Context c) {
        jsonReqDailyPrices = null;
        jsonReqIntraDayPrices = null;
        jsonReqCurrentPrice = null;
        dailyPrices = new double[365];   // initializing arrays
        intraDayPrices = new double[288];
        intraHourPrices = new double[60];

        if(volleyQueue == null)
            volleyQueue = Volley.newRequestQueue(c);  // initializing the volleyQueue

        // Pull user's preferred domestic currency from SharedPreferences
        domesticCurrency = c.getSharedPreferences("userPrefs", 0)
                .getString("domestic ticker", "USD");

        fetchCounter = 0;
    }

    public void setOnFetchesCompleteListener(OnFetchesCompleteListener onFetchesCompleteListener) {
        this.mListener = onFetchesCompleteListener;
    }

    //The fetchCurrentPrice method will return a JSON object containing the most up to date price.
    protected void fetchCurrentPrice() {

        final String url = "https://min-api.cryptocompare.com/data/price?fsym=" +
                ticker + "&tsyms=" + domesticCurrency;

        jsonReqCurrentPrice = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseCurrentPriceFetch(response);
                updateFetchCounter();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("tester", error.getMessage());
            }
        });

        volleyQueue.add(jsonReqCurrentPrice);
    }

    private void parseCurrentPriceFetch(JSONObject response) {

        String temp = "0";

        try { // necessary try/catch
            temp = response.getString(domesticCurrency);
        } catch (JSONException e) {
            e.printStackTrace();
            temp = "0";
        }

        this.currentPrice = Double.valueOf(temp);
    }


    /**
     * The fetchDailyPrices method will return a JSON object containing the cryptocurrency's
     * closing price in daily time intervals over the past year. This JSON object will then be
     * parsed to populate the dailyPrices array.
     */
    protected void fetchDailyPrices() {
        // Using CryptoCompare's histoday
        final String url = "https://min-api.cryptocompare.com/data/histoday?fsym=" +
                ticker + "&tsym=" + domesticCurrency + "&limit=365&aggregate=1&e=" + EXCHANGE;


        jsonReqDailyPrices = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseDailyPrices(response);
                updateFetchCounter();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        volleyQueue.add(jsonReqDailyPrices);
    }

    private void parseDailyPrices(JSONObject response) {

        JSONArray data = null;

        try {
            data = response.getJSONArray("Data");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for(int i = 0; i < 365; i++) {

            try {
                dailyPrices[i] = data.getJSONObject(364 - i).getDouble("close");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


    /**
     * The IntraDay fetch will return a JSON object containing the cryptocurrency's price history
     * in minute time intervals. This JSON object will then be parsed to populate both the
     * intraDayPrices and intraHourPrices arrays.
     */
    protected void fetchIntraDayPrices() {

        // Using CryptoCompare's histominute
        final String url = "https://min-api.cryptocompare.com/data/histominute?fsym=" +
                ticker + "&tsym=" + domesticCurrency + "&limit=1440&aggregate=1&e=" + EXCHANGE;


        jsonReqIntraDayPrices = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                parseIntraDayPrices(response);
                parseIntraHourPrices(response);
                updateFetchCounter();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("tester", error.getMessage());
            }
        });

        volleyQueue.add(jsonReqIntraDayPrices);
    }

    private void parseIntraDayPrices(JSONObject response) {

        JSONArray data = null;
        int arrayCounter = 288, k = 5;


        try {   // Getting the array
            data = response.getJSONArray("Data");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Array data has 1440 elements but we are only storing every 5th element of this array.
        for(int i = 0; i < 1440; i++) {

            if(k++ == 5) {

                try {
                    intraDayPrices[--arrayCounter] = data.getJSONObject(i).getDouble("close");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                k = 1;  // reset k
            }
        }

    }

    private void parseIntraHourPrices(JSONObject response) {

        JSONArray data = null;

        try {
            data = response.getJSONArray("Data");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 60; i++) {

            try {
                intraHourPrices[i] = data.getJSONObject(1439 - i).getDouble("close");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void fetchAll() {

        cancelJsonReqs();
        volleyQueue.getCache().clear();
        fetchCounter = 0;
        fetchDailyPrices();
        fetchIntraDayPrices();
        fetchCurrentPrice();
    }

    public void refreshPrices() {

        cancelJsonReqs();
        volleyQueue.getCache().invalidate(jsonReqCurrentPrice.getCacheKey(), true);
        volleyQueue.getCache().invalidate(jsonReqIntraDayPrices.getCacheKey(), true);

        fetchCounter = 1;

        fetchIntraDayPrices();
        fetchCurrentPrice();
    }

    private void cancelJsonReqs() {

        if(jsonReqCurrentPrice != null && jsonReqIntraDayPrices != null
                && jsonReqDailyPrices != null) {

            jsonReqCurrentPrice.cancel();
            jsonReqIntraDayPrices.cancel();
            jsonReqDailyPrices.cancel();
        }
    }

    private void updateFetchCounter() {
        fetchCounter++;

        if(fetchCounter >= 3) {
            calculatePercentChanges();
            mListener.onFetchesComplete();
        }
    }

    private void calculatePercentChanges() {
        // Using percent change formula
        hourChange = ( (currentPrice - intraHourPrices[59]) / intraHourPrices[59] ) * 100;
        dayChange = ( ( currentPrice - intraDayPrices[287]) / intraDayPrices[287] ) * 100;
        weekChange = ( (currentPrice - dailyPrices[6]) / dailyPrices[6] ) * 100;
        monthChange = ( (currentPrice - dailyPrices[29]) / dailyPrices[29] ) * 100;
        yearChange = ( (currentPrice - dailyPrices[364]) / dailyPrices[364] ) * 100;
    }

    // GET METHODS
    public double getCurrentPrice() {
        return currentPrice;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public String getDomesticCurrency() {
        return domesticCurrency;
    }

    public double getHourChange() {
        return hourChange;
    }

    public double getDayChange() {
        return dayChange;
    }

    public double getWeekChange() {
        return weekChange;
    }

    public double getMonthChange() {
        return monthChange;
    }

    public double getYearChange() {
        return yearChange;
    }

    public double getPriceByDay(int daysAgo) {
        return dailyPrices[daysAgo];
    }
}

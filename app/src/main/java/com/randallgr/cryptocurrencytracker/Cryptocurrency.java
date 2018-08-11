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

public class Cryptocurrency {

    protected String name, ticker, domesticCurrency;
    protected double currentPrice, dayChange, weekChange, monthChange, yearChange;
    protected double[] dailyPrices;

    private final RequestQueue volleyQueue;
    private JsonObjectRequest jsonReqCurrentPrice, jsonReqDailyPrices;
    private final Updateable viewModelInterface;

    // constructor
    public Cryptocurrency(Context c, Updateable updateable) {
        dailyPrices = new double[365];   // initializing array
        volleyQueue = Volley.newRequestQueue(c);  // initializing the volleyQueue



        domesticCurrency = c.getSharedPreferences("userPrefs", 0)
                .getString("domestic ticker", "USD");

        viewModelInterface = updateable;
    }

    protected void fetchCurrentPrice() {

        final String url = "https://min-api.cryptocompare.com/data/price?fsym=" +
                ticker + "&tsyms=" + domesticCurrency;

        jsonReqCurrentPrice = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseCurrentPriceFetch(response);
                viewModelInterface.updateCurrentPrice();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
        Log.i("testing", temp);
    }

    protected void fetchDailyPrices() {

        final String url = "https://min-api.cryptocompare.com/data/histoday?fsym=" +
                ticker + "&tsym=" + domesticCurrency + "&limit=365&aggregate=1&e=CCCAGG";


        jsonReqDailyPrices = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseDailyPrices(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        volleyQueue.add(jsonReqDailyPrices);
    }

    private void parseDailyPrices(JSONObject response) {

        String temp = "0";
        JSONArray data = null;

        try {
            data = response.getJSONArray("Data");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for(int i = 0; i < 365; i++) {

            try {
                dailyPrices[i] = data.getJSONObject(364-i).getDouble("close");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }



    // GET METHODS
    public double getCurrentPrice() {
        return currentPrice;
    }

}

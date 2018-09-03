package com.randallgr.cryptocurrencytracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


public class GraphFragment extends Fragment {

    private LineChart chart;
    private LineDataSet hourDataSet, dayDataSet, sevenDayDataSet, monthDataSet, yearDataSet;
    private RadioGroup radioGroup;
    private RadioButton hourRadioButton, dayRadioButton, sevenDayRadioButton, monthRadioButton;
    private RadioButton yearRadioButton;

    public GraphFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph, container, false);

        attachIDs(view);
        setListeners();

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        chart.getXAxis().setDrawLabels(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.setTouchEnabled(false);

        return view;
    }

    public void updateGraphFragment(Cryptocurrency selectedCrypto) {

        chart.clear();
        updateAllDataSets(selectedCrypto);
        yearRadioButton.toggle();
        displaySeries(yearDataSet);
    }

    private void updateAllDataSets(Cryptocurrency selectedCrypto) {
        updateHourDataSet(selectedCrypto);
        updateDayDataSet(selectedCrypto);
        update30DayDataSet(selectedCrypto);
        updateYearDataSet(selectedCrypto);
    }

    private void updateHourDataSet(Cryptocurrency selectedCrypto) {
        List<Entry> entries = new ArrayList<>();

        for(int i = 0; i < 60; i++)
            entries.add(new Entry(i, (float) selectedCrypto.getIntraHourPrice(60-i)));

        hourDataSet = new LineDataSet(entries, "Minute Interval Pricing");
    }

    private void updateDayDataSet(Cryptocurrency selectedCrypto) {
        List<Entry> entries = new ArrayList<>();

        for(int i = 0; i < 288; i++)
            entries.add(new Entry(i, (float) selectedCrypto.getIntraDayPrice(288-i)));

        dayDataSet = new LineDataSet(entries, "Five Minute Interval Pricing");
    }

    private void update30DayDataSet(Cryptocurrency selectedCrypto) {
        List<Entry> entries = new ArrayList<>();

        for(int i = 0; i < 30; i++)
            entries.add(new Entry(i, (float) selectedCrypto.getIntraYearPrice(30-i)));

        monthDataSet = new LineDataSet(entries, "Day Interval Pricing");
    }

    private void updateYearDataSet(Cryptocurrency selectedCrypto) {
        List<Entry> entries = new ArrayList<>();

        for(int i = 0; i < 365; i++)
            entries.add(new Entry(i, (float) selectedCrypto.getIntraYearPrice(365-i)));

        yearDataSet = new LineDataSet(entries, "Day Interval Pricing");
    }

    private void attachIDs(View view) {
        chart = view.findViewById(R.id.chart);
        radioGroup = view.findViewById(R.id.radioGroup);
        hourRadioButton = view.findViewById(R.id.rb_1h);
        dayRadioButton = view.findViewById(R.id.rb_1d);
        sevenDayRadioButton = view.findViewById(R.id.rb_7d);
        monthRadioButton = view.findViewById(R.id.rb_30d);
        yearRadioButton = view.findViewById(R.id.rb_1y);
    }

    private void setListeners() {

        hourRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySeries(hourDataSet);
            }
        });

        dayRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySeries(dayDataSet);
            }
        });

        monthRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySeries(monthDataSet);
            }
        });

        yearRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySeries(yearDataSet);
            }
        });
    }

    private void displaySeries(LineDataSet ds) {
        chart.clear();
        ds.setColors(new int[] {R.color.stockGreen}, getActivity());
        ds.setDrawCircles(false);
        ds.setDrawValues(false);
        chart.setData(new LineData(ds));
    }

}

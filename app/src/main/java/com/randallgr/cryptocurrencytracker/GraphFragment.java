package com.randallgr.cryptocurrencytracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GraphFragment extends Fragment {

    private LineChart chart;

    public GraphFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph, container, false);

        chart = view.findViewById(R.id.chart);

        /*Random r = new Random();

        List<Entry> ent = new ArrayList<>();

        for(int i = 0; i < 300; i++) {
            ent.add(new Entry(i, r.nextInt(1000) +1 ));
        }

        LineDataSet ds = new LineDataSet(ent, "Label");
        chart.setData(new LineData(ds));*/

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        return view;
    }

    public void updateGraphFragment(Cryptocurrency selectedCrypto) {

        chart.clear();

        List<Entry> ent = new ArrayList<>();

        for(int i = 0; i < 365; i++)
            ent.add(new Entry(i,(float) selectedCrypto.getPriceByDay(364 - i)));

        LineDataSet ds = new LineDataSet(ent, "Price");
        ds.setColors(new int[] {R.color.stockGreen}, getActivity());
        ds.setDrawCircles(false);
        chart.setData(new LineData(ds));
        chart.getXAxis().setDrawLabels(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.setTouchEnabled(false);


    }

}

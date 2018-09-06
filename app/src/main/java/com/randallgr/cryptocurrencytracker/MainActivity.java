package com.randallgr.cryptocurrencytracker;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Spinner cryptoSelectionSpinner;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressBar loadingCircle;
    private ImageButton refreshButton;

    private CryptocurrencyViewModel cryptoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cryptoViewModel = ViewModelProviders.of(this).get(CryptocurrencyViewModel.class);
        initSpinner();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //setupTabIcons();

        loadingCircle = findViewById(R.id.progressBar);
        loadingCircle.setVisibility(View.VISIBLE);

        cryptoViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                loadingCircle.setVisibility(View.INVISIBLE);
            }
        });

        refreshButton = findViewById(R.id.ib_refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingCircle.setVisibility(View.VISIBLE);
                cryptoViewModel.selectededCrypto.refreshPrices();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.set_domestic:
                DomesticCurrencyDialog domesticCurrencyDialog = new DomesticCurrencyDialog();
                domesticCurrencyDialog.show(getSupportFragmentManager(), "d2");
                return true;

            case R.id.set_preload:
                SetPreloadDialog setPreloadDialog = new SetPreloadDialog();
                setPreloadDialog.show(getSupportFragmentManager(), "d1");
        }


        return super.onOptionsItemSelected(item);
    }

    private void initSpinner() {
        cryptoSelectionSpinner = findViewById(R.id.s_cryptoSelection);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.supportedCryptocurrencies, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cryptoSelectionSpinner.setAdapter(adapter);

        cryptoSelectionSpinner.setSelection(pullPreload());

        cryptoSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadingCircle.setVisibility(View.VISIBLE);
                cryptoViewModel.changeSelectedCrypto(i);

                TextView t = (TextView) adapterView.getChildAt(0);
                if(t != null)
                    t.setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private int pullPreload() {
        SharedPreferences sp = getSharedPreferences(SharedPrefsHelper.USER_PREFERENCES, 0);
        return sp.getInt(SharedPrefsHelper.PRELOAD, 0);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_tab);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_graph_tab);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_converter_tab);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(cryptoViewModel.homeFragment, "HOME");
        adapter.addFragment(cryptoViewModel.graphFragment, "GRAPH");
        adapter.addFragment(cryptoViewModel.converterFragment, "CONVERTER");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);     // keep all three fragments in memory
    }

}

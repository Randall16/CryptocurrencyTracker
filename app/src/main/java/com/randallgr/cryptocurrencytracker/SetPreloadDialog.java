package com.randallgr.cryptocurrencytracker;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetPreloadDialog extends DialogFragment {

    private String [] supportedCryptcurrencies;
    private int selection;

    public SetPreloadDialog() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        supportedCryptcurrencies = getResources().getStringArray(R.array.supportedCryptocurrencies);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose a Cryptocurrency to be Loaded on App Startup");

        builder.setSingleChoiceItems(supportedCryptcurrencies, -1,
                new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selection = i;
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // save selection to SharedPreferences
                SharedPreferences sp = getActivity().
                        getSharedPreferences(SharedPrefsUtil.USER_PREFERENCES, 0);

                SharedPreferences.Editor ed = sp.edit();
                ed.putInt(SharedPrefsUtil.PRELOAD, selection);
                ed.commit();

                Toast.makeText(getActivity(), "Preload Crypotcurrency Set",
                        Toast.LENGTH_SHORT).show();
            }
        });


        return builder.show();
    }
}

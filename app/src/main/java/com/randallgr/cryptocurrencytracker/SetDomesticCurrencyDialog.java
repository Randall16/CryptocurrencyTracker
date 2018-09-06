package com.randallgr.cryptocurrencytracker;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetDomesticCurrencyDialog extends DialogFragment {

    private String abriv;

    public SetDomesticCurrencyDialog() {
        // Required empty public constructor
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Domestic Currency");
        builder.setSingleChoiceItems(DomesticCurrencyUtil.SUPPORTED_DOMESTIC_CURRENCIES,
                -1, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0)
                    abriv = "USD";
                else if(i == 1)
                    abriv = "EUR";
                else if(i == 2)
                    abriv = "GBP";
                else if(i == 3)
                    abriv = "CAD";
                else if(i == 4)
                    abriv = "CNY";

            }


        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            } });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // save users selection to SharedPreferences
                SharedPreferences sp = getActivity()
                        .getSharedPreferences(SharedPrefsUtil.USER_PREFERENCES, 0);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString(SharedPrefsUtil.DOMESTIC_TICKER, abriv);
                ed.commit();


                // restarting application after user selection is made
                Intent intent = getActivity().getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getActivity().getBaseContext().getPackageName() );
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            } });

        return builder.show();
    }
}

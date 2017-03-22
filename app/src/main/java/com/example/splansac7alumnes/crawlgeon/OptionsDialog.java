package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;//Recordar que el import debe ser V4
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Xexio on 10/03/2017.
 */

public class OptionsDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //this.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.opcions);

        builder.setMessage(null).setView(R.layout.options_layout);//Posem el layout al Dialog



        // Create the AlertDialog object and return it
        return builder.create();


    }
}
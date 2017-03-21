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
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());

        //Agafem el layout que hem creat per tenir una View
        final View view = factory.inflate(R.layout.options_layout,null);

        builder.setTitle(R.string.opcions);


        builder.setMessage(null)
                .setView(view)//Posem el layout al Dialog
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });




        // Create the AlertDialog object and return it
        return builder.create();


    }
}
package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;


/**
 * Created by crreinal19.alumnes on 21/03/17.
 */

public class OptionsLostDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setMessage(null).setView(R.layout.options_lost_layout);

        /*int view = R.layout.options_win_layout;

        builder.setMessage(null)
                .setView(view)//Posem el layout al Dialog
                ;*/



        return builder.create();

    }
}

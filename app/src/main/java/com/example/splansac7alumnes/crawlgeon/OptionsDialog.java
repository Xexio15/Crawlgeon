package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;//Recordar que el import debe ser V4
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by Xexio on 10/03/2017.
 */

public class OptionsDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //this.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.);
        //Necessitem un LayoutInflater per crear la View i aixi poder implementar el nostre boto ABout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.options_layout, null);


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.opcions);

        builder.setMessage(null).setView(view);//Posem el layout al Dialog

        /*
        * Boto about que obre una pagina web amb informacio
         */
        Button about = (Button) view.findViewById(R.id.buttonAbout);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/ub-pis/PIS_12/wiki"));
                startActivity(intent);*/

                Intent toAbout = new Intent(getActivity(),About.class);
                startActivity(toAbout);
            }
        });


        // Create the AlertDialog object and return it
        return builder.create();


    }

}
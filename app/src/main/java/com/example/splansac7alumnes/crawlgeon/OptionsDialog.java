package com.example.splansac7alumnes.crawlgeon;

import android.app.Activity;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Sergio Plans on 10/03/2017.
 */

public class OptionsDialog extends Dialog {
    public OptionsDialog(Context context, int themeResId) {
        super(context, themeResId);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Treiem el titol
        this.getWindow().setBackgroundDrawableResource(R.drawable.options_dialog_bg);//Posem el fons que volem
        this.setContentView(R.layout.options_layout);//Posem el layout
        this.setCanceledOnTouchOutside(true);//Si clickem fora es tanca(Diria que no funciona be)
        this.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);//Amb WrapContent evitem que es posi
                                                                                                                // la imatge a tota la pantalla, proba a canviar per MATCH_PARENT i veus que es posa a pantalla completa
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//Activem un fons semitransparent sota del dialog

         /*
        * Boto about que obre la activity About
         */
        Button about = (Button) findViewById(R.id.buttonAbout);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/ub-pis/PIS_12/wiki"));
                startActivity(intent);*/
                Intent toAbout = new Intent(OptionsDialog.super.getOwnerActivity(),About.class);//getOwnerActivity agafa atribut que hem passat amb setOwnerActivity quan creem el Dialog
                OptionsDialog.super.getOwnerActivity().startActivity(toAbout);
            }
        });
    }


}
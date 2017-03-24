package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


/**
 * Created by crreinal19.alumnes on 21/03/17.
 */



public class OptionsLostDialog extends Dialog {

    private Context context;
    public OptionsLostDialog(Context context, int themeResId){

        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Treiem el titol
        this.getWindow().setBackgroundDrawableResource(R.drawable.options_dialog_bg);//Posem el fons que volem
        this.setContentView(R.layout.options_lost_layout);//Posem el layout
        this.setCanceledOnTouchOutside(false);//Si clickem fora es tanca(Diria que no funciona be)
        this.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);//Amb WrapContent evitem que es posi
        // la imatge a tota la pantalla, proba a canviar per MATCH_PARENT i veus que es posa a pantalla completa
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//Activem un fons semitransparent sota del dialog


         /*
        * Boto about que obre la activity About
         */
        Button retry = (Button) findViewById(R.id.btnRetry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/ub-pis/PIS_12/wiki"));
                startActivity(intent);*/
                Intent gameScreen = new Intent(OptionsLostDialog.super.getOwnerActivity(),GameScreen.class);//getOwnerActivity agafa atribut que hem passat amb setOwnerActivity quan creem el Dialog
                dismiss(); //Tanquem el dialog
                context.startActivity(gameScreen);
            }
        });

        Button menu = (Button) findViewById(R.id.btnMenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/ub-pis/PIS_12/wiki"));
                startActivity(intent);*/
                Intent levelSelection = new Intent(OptionsLostDialog.super.getOwnerActivity(),LevelSelection.class);
                dismiss(); //Tanquem el dialog
                context.startActivity(levelSelection);

            }
        });

    }

    /*
     * Metodo para deshabilitar el boton de atras
     */
    public boolean onKeyDown(int keyCode, KeyEvent event){
        return false;
    }
}

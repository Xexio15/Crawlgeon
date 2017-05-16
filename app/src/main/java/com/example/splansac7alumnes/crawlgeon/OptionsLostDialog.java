package com.example.splansac7alumnes.crawlgeon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by crreinal19.alumnes on 21/03/17.
 */



public class OptionsLostDialog extends Dialog {
    private Controller controlador;
    private Context context;
    public OptionsLostDialog(Context context, int themeResId, Controller controlador){

        super(context, themeResId);
        this.context = context;
        this.controlador = controlador;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Treiem el titol
        this.getWindow().setBackgroundDrawableResource(R.drawable.options_dialog_bg);//Posem el fons que volem
        this.setContentView(R.layout.options_lost_layout);//Posem el layout
        this.setCanceledOnTouchOutside(false);//Si clickem fora es tanca(Diria que no funciona be)
        this.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//Amb WrapContent evitem que es posi
        // la imatge a tota la pantalla, proba a canviar per MATCH_PARENT i veus que es posa a pantalla completa
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//Activem un fons semitransparent sota del dialog

        ((TextView) findViewById(R.id.textLost)).setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/PixelFont.ttf"));


        /**
        * Boto about que obre la activity About
        */
        Button retry = (Button) findViewById(R.id.btnRetry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.playButtonSound(context);
                controlador.stopMusica();
                controlador.stopFX();
                Intent gameScreen = new Intent(OptionsLostDialog.super.getOwnerActivity(),GameScreen.class);//getOwnerActivity agafa atribut que hem passat amb setOwnerActivity quan creem el Dialog
                ((Activity)context).finish();//Finalitzem GameScreen
                ((Activity)context).overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                dismiss(); //Tanquem el dialog
                context.startActivity(gameScreen);//Reobrim GameScreen
            }
        });

        /**
         * Boto que ens torna al menu de seleccio de nivell
         */
        Button menu = (Button) findViewById(R.id.btnMenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.playButtonSound(context);
                controlador.stopMusica();
                controlador.stopFX();
                Intent levelSelection = new Intent(OptionsLostDialog.super.getOwnerActivity(),LevelSelection.class);
                ((Activity)context).finish();//Finalitzem GameScreen
                ((Activity)context).overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                dismiss(); //Tanquem el dialog

                context.startActivity(levelSelection);//Obrim LevelSelection

            }
        });

    }

    /**
     * Metodo para deshabilitar el boton de atras
     */
    public boolean onKeyDown(int keyCode, KeyEvent event){
        return false;
    }
}

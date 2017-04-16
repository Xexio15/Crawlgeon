package com.example.splansac7alumnes.crawlgeon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by crreinal19.alumnes on 24/03/17.
 */

public class OptionsGameDialog extends Dialog{
    private Context context;
    public OptionsGameDialog(Context context, int themeResId){
        super(context, themeResId);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Treiem el titol
        this.getWindow().setBackgroundDrawableResource(R.drawable.options_dialog_bg);//Posem el fons que volem (FONS TEMPORAL: S'HA DE CANVIAR)
        this.setContentView(R.layout.options_game_layout);//Posem el layout
        this.setCanceledOnTouchOutside(false);//Si clickem fora no es tanca
        this.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//Amb WrapContent evitem que es posi
        // la imatge a tota la pantalla, proba a canviar per MATCH_PARENT i veus que es posa a pantalla completa
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//Activem un fons semitransparent sota del dialog


        /*
         * Boto resume que ens retorna al joc
         */
        Button resume = (Button) findViewById(R.id.buttonResume);
        resume.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        /*
         * Boto leave que ens retorna al menú de selecció de nivells
         */
        Button leave = (Button) findViewById(R.id.buttonLeave);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent levelSelection = new Intent(OptionsGameDialog.super.getOwnerActivity(),LevelSelection.class);
                ((Activity)context).finish();//Finalitzem GameScreen
                ((Activity)context).overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
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

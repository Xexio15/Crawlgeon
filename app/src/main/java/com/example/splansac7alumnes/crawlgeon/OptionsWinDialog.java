package com.example.splansac7alumnes.crawlgeon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by crreinal19.alumnes on 21/03/17.
 */

public class OptionsWinDialog extends Dialog{

    private Controller controlador;
    private Context context;
    private int puntuacion;
    private String score;
    public OptionsWinDialog(Context context, int themeResId, Controller controlador, int puntuacion, String score){

        super(context, themeResId);
        this.context = context;
        this.controlador = controlador;
        this.puntuacion = puntuacion;
        this.score = score;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Treiem el titol
        this.getWindow().setBackgroundDrawableResource(R.drawable.options_dialog_bg);//Posem el fons que volem (FONS TEMPORAL: S'HA DE CANVIAR)
        this.setContentView(R.layout.options_win_layout);//Posem el layout
        this.setCanceledOnTouchOutside(false);//Si clickem fora no es tanca
        this.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//Amb WrapContent evitem que es posi
        // la imatge a tota la pantalla, proba a canviar per MATCH_PARENT i veus que es posa a pantalla completa
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//Activem un fons semitransparent sota del dialog

        ((TextView) findViewById(R.id.textWin)).setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/PixelFont.ttf"));
        TextView xp = ((TextView) findViewById(R.id.xp));
        xp.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/PixelFont.ttf"));
        xp.setText(score);

        ImageView star1 = (ImageView) findViewById(R.id.star1);
        ImageView star2 = (ImageView) findViewById(R.id.star2);
        ImageView star3 = (ImageView) findViewById(R.id.star3);

        if (this.puntuacion == 3){
            star1.setImageResource(R.drawable.star);
            star2.setImageResource(R.drawable.star);
            star3.setImageResource(R.drawable.star);
        }else if (this.puntuacion == 2){
            star1.setImageResource(R.drawable.star);
            star2.setImageResource(R.drawable.star);
            star3.setImageResource(R.drawable.emptystar);
        }else{
            star1.setImageResource(R.drawable.star);
            star2.setImageResource(R.drawable.emptystar);
            star3.setImageResource(R.drawable.emptystar);
        }

        /**
         * Boto Next Level que obre la activity del següent nivell
         */
        Button next_lvl = (Button) findViewById(R.id.buttonNext);
        next_lvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!controlador.isActualBoss()) {


                    int nivelActual = controlador.getNivelActual();

                    /*  PARA CUANDO HAYA LOS 10 NIVELES
                     *  if((nivelActual+1)<10) {
                     *      controlador.setNivelActual(controlador.getNivelActual() + 1);
                     *  }
                     */
                    if((nivelActual+1)<3) {
                        controlador.setNivelActual(controlador.getNivelActual() + 1);
                        //controlador.restartMusica();
                    }else if(nivelActual==2){
                        controlador.setNivelActual(10);

                    }
                    controlador.stopMusica();



                    Intent nextLevel = new Intent(OptionsWinDialog.super.getOwnerActivity(), GameScreen.class);//getOwnerActivity agafa atribut que hem passat amb setOwnerActivity quan creem el Dialog
                    ((Activity) context).finish();//Finalitzem GameScreen
                    ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); //ANIMACIO FADE
                    dismiss(); //Tanquem el dialog
                    context.startActivity(nextLevel);
                }
            }
        });

        /**
         * Boto Menu que obre el menu de seleccio de nivells
         */
        Button menu = (Button) findViewById(R.id.buttonLevelMenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.stopMusica();
                Intent levelSelection = new Intent(OptionsWinDialog.super.getOwnerActivity(),LevelSelection.class);
                ((Activity)context).finish();//Finalitzem GameScreen
                ((Activity)context).overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                dismiss(); //Tanquem el dialog
                context.startActivity(levelSelection);


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

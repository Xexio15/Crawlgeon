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
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by crreinal19.alumnes on 24/03/17.
 */

public class OptionsGameDialog extends Dialog{
    private Context context;
    private Controller controlador;
    public OptionsGameDialog(Context context, int themeResId, Controller controlador){
        super(context, themeResId);
        this.context=context;
        this.controlador = controlador;
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

        ((TextView) findViewById(R.id.textMusic)).setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textFX)).setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textPause)).setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/PixelFont.ttf"));

        SeekBar musica = (SeekBar)findViewById(R.id.musicVolume);
        SeekBar fx = (SeekBar)findViewById(R.id.fxVolume);
        musica.setProgress((int)controlador.getMusicVolume());
        fx.setProgress((int)controlador.getFXVolume());

        /**
         * Boto resume que ens retorna al joc
         */
        Button resume = (Button) findViewById(R.id.buttonResume);
        resume.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        /**
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

        /**
         * Listener que actualitza el volum de la musica
         */
        musica.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                controlador.changeMusicVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /**
         * Listener que actualitza el volum dels efectes
         */
        fx.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                controlador.changeMusicVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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


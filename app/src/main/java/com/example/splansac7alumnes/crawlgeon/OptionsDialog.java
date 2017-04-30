package com.example.splansac7alumnes.crawlgeon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Sergio Plans on 10/03/2017.
 */

public class OptionsDialog extends Dialog {
    private Context context;
    private Controller controlador;
    public OptionsDialog(Context context, int themeResId, Controller controlador) {
        super(context, themeResId);
        this.context = context;
        this.controlador = controlador;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Treiem el titol
        this.getWindow().setBackgroundDrawableResource(R.drawable.options_dialog_bg);//Posem el fons que volem
        this.setContentView(R.layout.options_layout);//Posem el layout
        this.setCanceledOnTouchOutside(true);//Si clickem fora es tanca(Diria que no funciona be)
        this.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//Amb WrapContent evitem que es posi
                                                    // la imatge a tota la pantalla, proba a canviar per MATCH_PARENT i veus que es posa a pantalla completa
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//Activem un fons semitransparent sota del dialog

        ((TextView) findViewById(R.id.textMusic)).setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textFX)).setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/PixelFont.ttf"));

        SeekBar musica = (SeekBar)findViewById(R.id.musicVolume);
        SeekBar fx = (SeekBar)findViewById(R.id.fxVolume);
        musica.setProgress((int)controlador.getMusicVolume());
        fx.setProgress((int)controlador.getFXVolume());

        /**
        * Boto about que obre la activity About
        */
        Button about = (Button) findViewById(R.id.buttonAbout);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                controlador.playButtonSound(context);
                Intent toAbout = new Intent(OptionsDialog.super.getOwnerActivity(),About.class);//getOwnerActivity agafa atribut que hem passat amb setOwnerActivity quan creem el Dialog
                OptionsDialog.super.getOwnerActivity().startActivity(toAbout);
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
                controlador.saveData();
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
                controlador.changeFXVolume(progress);
                controlador.saveData();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


}
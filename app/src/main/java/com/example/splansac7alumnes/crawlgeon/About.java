package com.example.splansac7alumnes.crawlgeon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.PowerManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class About extends AppCompatActivity {
    private Controller controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        this.controlador = controlador.getInstance();

        ((TextView) findViewById(R.id.agradecimientos)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textAbout)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textAbout2)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textAbout3)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/PixelFont.ttf"));

        Button back = (Button) findViewById(R.id.botoBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.playBackButtonSound(About.this);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); //ANIMACIO FADE
            }
        });




        Button borrar = (Button) findViewById(R.id.borrarDatos);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(About.this)
                        .setTitle("Alert")
                        .setMessage("This will delete all your data")
                        .setNegativeButton(R.string.cancel,null)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                controlador.deleteData();
                            }
                        })
                        .show();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event ){
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK){
            controlador.playBackButtonSound(About.this);
            finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!controlador.isPlayingMusica()) {
            controlador.initMusica(this, R.raw.menus_music);
            controlador.setShouldPlay (true);
            controlador.resumeMusica();
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        PowerManager mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

        //shouldPlay = false;

        if (!mPowerManager.isInteractive()){
            controlador.setShouldPlay(false);
        }
        if (!controlador.isShouldPlay() && controlador.isPlayingMusica()) { // it won't pause music if shouldPlay is true
            controlador.pauseMusica();
        }
    }
    }



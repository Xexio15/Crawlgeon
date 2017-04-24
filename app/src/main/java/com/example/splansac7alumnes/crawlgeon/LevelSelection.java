package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class LevelSelection extends AppCompatActivity {
    private Controller controlador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);

        if(controlador == null) {
            controlador = controlador.getInstance();
        }

        if(!controlador.isPlayingMusica()){
            controlador.initMusica(this, R.raw.menus_music);
            controlador.playMusica();
        }

        /**
        * Aquest boto ens tornara a la pantalla de seleccio de dungeon
        */
        Button lvlSel_To_DungSel = (Button) findViewById(R.id.buttonLvlSelToDungSel);
        lvlSel_To_DungSel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent toDungSel = new Intent(LevelSelection.this, DungeonSelection.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                startActivity(toDungSel);
            }
        });

        /**
        * Aquest boto ens obrira un FragmentDialog que ens mostrara les opcions
        */
        Button options = (Button) findViewById(R.id.buttonOptions);
        options.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Creem un Dialog pero instanciem OptionsDialog
                Dialog dialog = new OptionsDialog(LevelSelection.this,R.style.Crawl, controlador);
                //Pasem la activitat actual
                dialog.setOwnerActivity(LevelSelection.this);
                //El mostrem
                dialog.show();
            }
        });

        /**
         * Botón nivel 1
         */
        Button btn_01x01 = (Button) findViewById(R.id.btn_01x01);
        btn_01x01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.setNivelActual(1);
                //iniciarNivel();
                controlador.stopMusica();
                Intent lvl_01x01 = new Intent(LevelSelection.this, GameScreen.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                startActivity(lvl_01x01);
            }
        });

        /**
         * Botón nivel 2
         */
        Button btn_01x02 = (Button) findViewById(R.id.btn_01x02);
        btn_01x02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.stopMusica();
                Intent lvl_01x02 = new Intent(LevelSelection.this, GameScreen.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                controlador.setNivelActual(2);
                startActivity(lvl_01x02);

            }
        });

        /**
         * Botón nivel 3
         */
        Button btn_01x03 = (Button) findViewById(R.id.btn_01x03);
        btn_01x03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.stopMusica();
                Intent lvl_01x03 = new Intent(LevelSelection.this, GameScreen.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                controlador.setNivelActual(3);
                //startActivity(lvl_01x03);

            }
        });

        /**
         * Botón nivel 4
         */
        Button btn_01x04 = (Button) findViewById(R.id.btn_01x04);
        btn_01x04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.stopMusica();
                Intent lvl_01x04 = new Intent(LevelSelection.this, GameScreen.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                controlador.setNivelActual(4);
                //startActivity(lvl_01x04);

            }
        });

        /**
         * Botón nivel 5
         */
        Button btn_01x05 = (Button) findViewById(R.id.btn_01x05);
        btn_01x05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.stopMusica();
                Intent lvl_01x05 = new Intent(LevelSelection.this, GameScreen.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                controlador.setNivelActual(5);
                //startActivity(lvl_01x05);

            }
        });

        /**
         * Botón nivel 6
         */
        Button btn_01x06 = (Button) findViewById(R.id.btn_01x06);
        btn_01x06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.stopMusica();
                Intent lvl_01x06 = new Intent(LevelSelection.this, GameScreen.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                controlador.setNivelActual(6);
                //startActivity(lvl_01x06);

            }
        });

        /**
         * Botón nivel 7
         */
        Button btn_01x07 = (Button) findViewById(R.id.btn_01x07);
        btn_01x07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.stopMusica();
                Intent lvl_01x07 = new Intent(LevelSelection.this, GameScreen.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                controlador.setNivelActual(7);
                //startActivity(lvl_01x07);

            }
        });

        /**
         * Botón nivel 8
         */
        Button btn_01x08 = (Button) findViewById(R.id.btn_01x08);
        btn_01x08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.stopMusica();
                Intent lvl_01x08 = new Intent(LevelSelection.this, GameScreen.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                controlador.setNivelActual(8);
                //startActivity(lvl_01x08);

            }
        });

        /**
         * Botón nivel 9
         */
        Button btn_01x09 = (Button) findViewById(R.id.btn_01x09);
        btn_01x09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.stopMusica();
                Intent lvl_01x09 = new Intent(LevelSelection.this, GameScreen.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                controlador.setNivelActual(9);
                //startActivity(lvl_01x09);

            }
        });

        /**
         * Botón nivel 10
         */
        Button btn_01x10 = (Button) findViewById(R.id.btn_01x10);
        btn_01x10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.stopMusica();
                Intent lvl_01x10 = new Intent(LevelSelection.this, GameScreen.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                controlador.setNivelActual(10);
                //startActivity(lvl_01x10);

            }
        });

        if(controlador.isBloqueado(2)){
            btn_01x02.setEnabled(false);
            btn_01x02.setBackgroundResource(R.drawable.lvl2bloqueado);
        }
        if(controlador.isBloqueado(3)){
            btn_01x03.setEnabled(false);
            btn_01x03.setBackgroundResource(R.drawable.lvl3bloqueado);
        }
        if(controlador.isBloqueado(4)){
            btn_01x04.setEnabled(false);
            btn_01x04.setBackgroundResource(R.drawable.lvl4bloqueado);
        }
        if(controlador.isBloqueado(5)){
            btn_01x05.setEnabled(false);
            btn_01x05.setBackgroundResource(R.drawable.lvl5bloqueado);
        }
        if(controlador.isBloqueado(6)){
            btn_01x06.setEnabled(false);
            btn_01x06.setBackgroundResource(R.drawable.lvl6bloqueado);
        }
        if(controlador.isBloqueado(7)){
            btn_01x07.setEnabled(false);
            btn_01x07.setBackgroundResource(R.drawable.lvl7bloqueado);
        }
        if(controlador.isBloqueado(8)){
            btn_01x08.setEnabled(false);
            btn_01x08.setBackgroundResource(R.drawable.lvl8bloqueado);
        }
        if(controlador.isBloqueado(9)){
            btn_01x09.setEnabled(false);
            btn_01x09.setBackgroundResource(R.drawable.lvl9bloqueado);
        }
        if(controlador.isBloqueado(10)){
            btn_01x10.setEnabled(false);
            btn_01x10.setBackgroundResource(R.drawable.bossbloqueado);
        }


    }
    public boolean onKeyDown(int keyCode, KeyEvent event ){
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK){
            Intent toDungSel = new Intent(LevelSelection.this, DungeonSelection.class);
            finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
            startActivity(toDungSel);
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    /*public void iniciarNivel(){
        controlador.stopMusica();
        Intent lvl_01x01 = new Intent(LevelSelection.this, GameScreen.class);
        finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
        startActivity(lvl_01x01);
    }*/
}

package com.example.splansac7alumnes.crawlgeon;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Dialog;
import android.content.ComponentName;
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

import java.util.List;

public class MainMenu extends AppCompatActivity {

    private Controller controlador;
    private Dialog dialog;


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

    /**
     * Modificamos onStop para evitar que pare la musica
     */
   /* public void onStop() {
        super.onStop();
        PowerManager mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

        if (!mPowerManager.isInteractive()){
            shouldPlay = false;
        }
        if (!shouldPlay && controlador.isPlayingMusica()) { // it won't pause music if shouldPlay is true
            controlador.pauseMusica();
        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controlador.saveData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem la pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        if(controlador == null){
            controlador = controlador.getInstance();
            controlador.setContext(this);
            controlador.initData();
        }
        //controlador.loadData();

        controlador.initMusica(this,R.raw.menus_music);
        controlador.setShouldPlay(true);
        if(!controlador.isPlayingMusica()) {
            controlador.playMusica();
        }

        /**
        * Aquest boto ens canviara de la pantalla d'inici a la pantalla de seleccio de dungeon
        */
        Button play = (Button) findViewById(R.id.buttonPlay);
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creem un nou intent que ens canvii d'una activity a una altra
                Intent menu_To_Sel = new Intent(MainMenu.this, DungeonSelection.class);
                //Finalitzem el menu
                //finish();
                controlador.playButtonSound(MainMenu.this);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right); //ANIMACIO SLIDE
                //Iniciem la activity
                startActivity(menu_To_Sel);
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
                Dialog dialog = new OptionsDialog(MainMenu.this,R.style.Crawl, controlador);//Passem el context actual, passem el tema que volem
                controlador.playButtonSound(MainMenu.this);
                //Pasem la activitat actual
                dialog.setOwnerActivity(MainMenu.this);
                //El mostrem
                dialog.show();

            }
        });

        ((TextView) findViewById(R.id.version)).setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
    }

    /**
     * Metodo para confirmar la salida de la app cuando presionamos a la tecla de atras
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            dialog = new Dialog(this,R.style.DialogsCrawlgeon);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.salir_layout);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.options_dialog_bg);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            TextView title = (TextView) dialog.findViewById(R.id.textTitle);
            title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
            TextView mensage = (TextView) dialog.findViewById(R.id.textMensage);
            mensage.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
            Button btnC = (Button) dialog.findViewById(R.id.bCancel);
            btnC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            Button btnO = (Button) dialog.findViewById(R.id.btnOk);
            btnO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controlador.stopMusica();
                    MainMenu.this.finish();
                }
            });
            dialog.show();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}

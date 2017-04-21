package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    private boolean shouldPlay;
    Controller controlador;
    public void onStop() {
        super.onStop();
        if (!shouldPlay) { // it won't pause music if shouldPlay is true
            controlador.stopMusica();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem la pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        if(controlador == null){
            controlador = controlador.getInstance();
        }
        controlador.initMusica(this,R.raw.menus_music);
        shouldPlay = true;
        if(!controlador.isPlayingMusica()) {
            controlador.playMusica();
        }



        /*
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
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right); //ANIMACIO SLIDE
                //Iniciem la activity
                startActivity(menu_To_Sel);
            }
        });


        /*
        * Aquest boto ens obrira un FragmentDialog que ens mostrara les opcions
        */
        Button options = (Button) findViewById(R.id.buttonOptions);
        options.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Creem un Dialog pero instanciem OptionsDialog
                Dialog dialog = new OptionsDialog(MainMenu.this,R.style.Crawl, controlador);//Passem el context actual, passem el tema que volem
                //Pasem la activitat actual
                dialog.setOwnerActivity(MainMenu.this);
                //El mostrem
                dialog.show();

            }
        });
    }

    /*
    * Metodo para confirmar la salida de la app cuando presionamos a la tecla de atras
    */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Are you sure?")
                    .setNegativeButton(R.string.cancel,null)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainMenu.this.finish();
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}

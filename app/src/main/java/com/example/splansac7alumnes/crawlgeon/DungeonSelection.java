package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DungeonSelection extends AppCompatActivity {
    private Controller controlador;


    @Override
    protected void onRestart() {
        super.onRestart();
        set_lvlUnlockedTXT();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon_selection);

        ((TextView)findViewById(R.id.textLvlsCompleteDng2)).setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));



        final TextView click = (TextView) findViewById(R.id.textClick);
        click.bringToFront();
        click.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));

        if(controlador == null){
            controlador = controlador.getInstance();
        }
        set_lvlUnlockedTXT();

        /**
         * Esta animacion no queda del todo bien
         * */

        /*final Animation parp = AnimationUtils.loadAnimation(this,R.anim.parpadeo);
        parp.reset();
        parp.setRepeatMode(Animation.INFINITE);
        click.startAnimation(parp);

        parp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                parp.reset();
                click.startAnimation(parp);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
*/
        /**
        * Aquest boto ens retornara a la pantalla d'inici des de la de seleccio de dungeon
        */
        Button sel_To_Menu = (Button) findViewById(R.id.buttonSelToMen);
        sel_To_Menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Intent toMenu = new Intent(DungeonSelection.this, MainMenu.class);
                controlador.playBackButtonSound(DungeonSelection.this);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                //startActivity(toMenu);
            }
        });

        /**
        * Aquest boto iniciara l'activity de Seleccio de Nivell i ens mostrara la pantalla que pertoca
        */
        final Button sel_To_LvlSel = (Button) findViewById(R.id.buttonDungeon1);
        sel_To_LvlSel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final ConstraintLayout dung = (ConstraintLayout) findViewById(R.id.activity_dungeon_selection);
                controlador.initFX(DungeonSelection.this, R.raw.door_sound);
                controlador.restartFX();
                controlador.playFX();
                sel_To_LvlSel.setEnabled(false);
                Animation anim = AnimationUtils.loadAnimation(DungeonSelection.this, R.anim.desaparece_largo);
                dung.startAnimation(anim);

                MediaPlayer mp = controlador.getFXPlayer();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        dung.setAlpha(0);
                        sel_To_LvlSel.setEnabled(true);
                        Intent toLvlSel = new Intent(DungeonSelection.this, LevelSelection.class);
                        finish();
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                        startActivity(toLvlSel);
                    }
                });
            }
        });

        Button dung2 = (Button) findViewById(R.id.buttonDungeon2);
        dung2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Blocked! (WIP)", Toast.LENGTH_SHORT);
                toast.show();
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
                Dialog dialog = new OptionsDialog(DungeonSelection.this,R.style.Crawl, controlador);
                controlador.playButtonSound(DungeonSelection.this);
                //Pasem la activitat actual
                dialog.setOwnerActivity(DungeonSelection.this);
                //El mostrem
                dialog.show();
            }
        });

        /**
        * Aquest boto iniciara l'activity de Equip i ens mostrara la pantalla que pertoca
        */
        Button equip = (Button) findViewById(R.id.buttonEquip);
        equip.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent toEquip = new Intent(DungeonSelection.this, Equip.class);
                controlador.playButtonSound(DungeonSelection.this);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                startActivity(toEquip);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event ){
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK){
            //Intent toMenu = new Intent(DungeonSelection.this, MainMenu.class);
            controlador.playBackButtonSound(DungeonSelection.this);
            finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
            //startActivity(toMenu);
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    public void set_lvlUnlockedTXT(){
        TextView lvlsBlocked = (TextView) findViewById(R.id.textLvlsCompleteDng1);
        lvlsBlocked.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        String lvls = String.valueOf(controlador.getNumLvlsBlocked());
        lvlsBlocked.setText(lvls+"/10");
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

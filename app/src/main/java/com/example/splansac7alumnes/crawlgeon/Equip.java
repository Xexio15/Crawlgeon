package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.splansac7alumnes.crawlgeon.Tiles.Fire;
import com.example.splansac7alumnes.crawlgeon.Tiles.Tile;

import org.w3c.dom.Text;

import java.util.Locale;

public class Equip extends AppCompatActivity {
    private Controller controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);
        if(controlador == null) {
            controlador = controlador.getInstance();
        }

        ImageView im = (ImageView) findViewById(R.id.pj);
        im.setBackgroundResource(controlador.getPersonaje().getStaticAnim());
        im.setImageResource(0);
        AnimationDrawable anim = (AnimationDrawable) im.getBackground();
        anim.start();

        TextView level = (TextView) findViewById(R.id.level);
        level.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        level.setText("Lvl "+(controlador.getPersonaje().getNivel()));

        TextView life = (TextView) findViewById(R.id.life);
        life.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        life.setText("Max. Life: "+(controlador.getPersonaje().getVida()));

        TextView armor = (TextView) findViewById(R.id.armor);
        armor.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        armor.setText("Max. Armor: "+((Math.round(controlador.getPersonaje().getVida()) * 20) / 100));

        TextView dmgBasic = (TextView) findViewById(R.id.basicDmg);
        dmgBasic.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        dmgBasic.setText("Basic Attack: "+String.format(Locale.US, "%.2f",(controlador.getPersonaje().getBonusTile())* (controlador.getTiles().get(0).getDamage()) + controlador.getTiles().get(0).getDamage()));

        TextView dmgDefense = (TextView) findViewById(R.id.defenseDmg);
        dmgDefense.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        dmgDefense.setText("Defense: "+ String.format(Locale.US, "%.2f",(controlador.getPersonaje().getBonusTile())* (controlador.getTiles().get(1).getDamage()) + controlador.getTiles().get(1).getDamage()));

        TextView dmgHealth = (TextView) findViewById(R.id.healthDmg);
        dmgHealth.setText("Heal: "+String.format(Locale.US, "%.2f",(controlador.getPersonaje().getVida()/100)*controlador.getTiles().get(6).getDamage()));
        dmgHealth.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));

        TextView dmgFire = (TextView) findViewById(R.id.fireDmg);
        dmgFire.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        dmgFire.setText("Fire: "+String.format(Locale.US, "%.2f",(controlador.getPersonaje().getBonusTile())* (controlador.getTiles().get(2).getDamage()) + controlador.getTiles().get(2).getDamage()));

        TextView dmgIce = (TextView) findViewById(R.id.iceDmg);
        dmgIce.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        dmgIce.setText("Ice: "+String.format(Locale.US, "%.2f",(controlador.getPersonaje().getBonusTile())* (controlador.getTiles().get(4).getDamage()) + controlador.getTiles().get(4).getDamage()));

        TextView dmgLight = (TextView) findViewById(R.id.lightDmg);
        dmgLight.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        dmgLight.setText("Lighting: "+String.format(Locale.US, "%.2f",(controlador.getPersonaje().getBonusTile())* (controlador.getTiles().get(5).getDamage()) + controlador.getTiles().get(5).getDamage()));

        TextView dmgArcane = (TextView) findViewById(R.id.arcaneDmg);
        dmgArcane.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        dmgArcane.setText("Arcane: "+String.format(Locale.US, "%.2f",(controlador.getPersonaje().getBonusTile())* (controlador.getTiles().get(3).getDamage()) + controlador.getTiles().get(3).getDamage()));

        ProgressBar exp = (ProgressBar) findViewById(R.id.barraEXP);
        exp.setMax(controlador.getPersonaje().getXpNecesaria());
        exp.setProgress(controlador.getPersonaje().getXpActual());

        /**
        * Aquest boto ens tornara a la pantalla de seleccio de dungeon
        */
        Button atras = (Button) findViewById(R.id.buttonEquipSelToDungSel);
        atras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toDungSel = new Intent(Equip.this, DungeonSelection.class);
                controlador.playBackButtonSound(Equip.this);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                startActivity(toDungSel);
            }
        });

        /**
        * Aquest boto ens obrira un FragmentDialog que ens mostrara les opcions
        */
        Button options = (Button) findViewById(R.id.buttonOptions);
        options.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creem un Dialog pero instanciem OptionsDialog
                Dialog dialog = new OptionsDialog(Equip.this,R.style.Crawl, controlador);
                controlador.playButtonSound(Equip.this);
                //Pasem la activitat actual
                dialog.setOwnerActivity(Equip.this);
                //El mostrem
                dialog.show();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event ){
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK){
            Intent toDungSel = new Intent(Equip.this, DungeonSelection.class);
            controlador.playBackButtonSound(Equip.this);
            finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
            startActivity(toDungSel);
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

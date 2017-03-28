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

public class DungeonSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon_selection);


        /*
        * Aquest boto ens retornara a la pantalla d'inici des de la de seleccio de dungeon
        */
        Button sel_To_Menu = (Button) findViewById(R.id.buttonSelToMen);
        sel_To_Menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toMenu = new Intent(DungeonSelection.this, MainMenu.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                startActivity(toMenu);
            }
        });

        /*
        * Aquest boto iniciara l'activity de Seleccio de Nivell i ens mostrara la pantalla que pertoca
        */
        Button sel_To_LvlSel = (Button) findViewById(R.id.buttonDungeon);
        sel_To_LvlSel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent toLvlSel = new Intent(DungeonSelection.this, LevelSelection.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                startActivity(toLvlSel);
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
                Dialog dialog = new OptionsDialog(DungeonSelection.this,R.style.Crawl);
                //Pasem la activitat actual
                dialog.setOwnerActivity(DungeonSelection.this);
                //El mostrem
                dialog.show();
            }
        });

        /*
        * Aquest boto iniciara l'activity de Equip i ens mostrara la pantalla que pertoca
        */
        Button equip = (Button) findViewById(R.id.buttonEquip);
        equip.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent toEquip = new Intent(DungeonSelection.this, Equip.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                startActivity(toEquip);
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event ){
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK){
            Intent toMenu = new Intent(DungeonSelection.this, MainMenu.class);
            finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
            startActivity(toMenu);
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}

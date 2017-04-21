package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ResourceBundle;

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
          /*
        * Aquest boto ens tornara a la pantalla de seleccio de dungeon
        */
        Button atras = (Button) findViewById(R.id.buttonEquipSelToDungSel);
        atras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toDungSel = new Intent(Equip.this, DungeonSelection.class);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
                startActivity(toDungSel);
            }
        });

        /*
        * Aquest boto ens obrira un FragmentDialog que ens mostrara les opcions
        */
        Button options = (Button) findViewById(R.id.buttonOptions);
        options.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creem un Dialog pero instanciem OptionsDialog
                Dialog dialog = new OptionsDialog(Equip.this,R.style.Crawl, controlador);
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
            finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
            startActivity(toDungSel);
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}

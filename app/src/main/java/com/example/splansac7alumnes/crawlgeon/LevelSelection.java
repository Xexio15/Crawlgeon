package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class LevelSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);


        /*
        * Aquest boto ens tornara a la pantalla de seleccio de dungeon simplement aturant l'activity amb finish()
        */
        Button lvlSel_To_DungSel = (Button) findViewById(R.id.buttonLvlSelToDungSel);
        lvlSel_To_DungSel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
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
                Dialog dialog = new OptionsDialog(LevelSelection.this,R.style.Crawl);
                //Pasem la activitat actual
                dialog.setOwnerActivity(LevelSelection.this);
                //El mostrem
                dialog.show();
            }
        });

        Button btn_01x01 = (Button) findViewById(R.id.btn_01x01);
        btn_01x01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent lvl_01x01 = new Intent(LevelSelection.this, GameScreen.class);

                startActivity(lvl_01x01);

            }
        });
    }
}

package com.example.splansac7alumnes.crawlgeon;

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
                //Creem el nou dialog que hem definit a la nostra classe OptionsDialog
                OptionsDialog dialog = new OptionsDialog();
                //El mostrem
                dialog.show(getSupportFragmentManager(),"opt");
            }
        });
    }
}

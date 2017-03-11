package com.example.splansac7alumnes.crawlgeon;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem la pantalla completa
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);


        /*
        * Aquest boto ens canviara de la pantalla d'inici a la pantalla de seleccio de dungeon
        */
        Button play = (Button) findViewById(R.id.buttonPlay);
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creem un nou intent que ens canvii d'una activity a una altra
                Intent menu_To_Sel = new Intent(MainMenu.this, DungeonSelection.class);
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
                //Creem el nou dialog que hem definit a la nostra classe OptionsDialog
                OptionsDialog dialog = new OptionsDialog();
                //El mostrem
                dialog.show(getSupportFragmentManager(),"opt");
            }
        });
    }
}

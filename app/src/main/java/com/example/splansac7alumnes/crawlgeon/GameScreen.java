package com.example.splansac7alumnes.crawlgeon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.KeyEvent;

public class GameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem la pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        Button win = (Button) findViewById(R.id.buttonWin);
        win.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creem el nou dialog que hem definit a la nostra classe OptionsDialog
                OptionsWinDialog dialog = new OptionsWinDialog(GameScreen.this, R.style.Crawl);
                dialog.setOwnerActivity(GameScreen.this);
                //El mostrem
                dialog.show();

            }
        });

        Button lost = (Button) findViewById(R.id.buttonLose);
        lost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creem el nou dialog que hem definit a la nostra classe OptionsDialog
                OptionsLostDialog dialog = new OptionsLostDialog(GameScreen.this, R.style.Crawl);
                dialog.setOwnerActivity(GameScreen.this);
                //El mostrem
                dialog.show();

            }
        });

    }
        /*
        * Metodo para deshabilitar el boton de atras
        */
        public boolean onKeyDown(int keyCode, KeyEvent event){
            OptionsGameDialog dialog = new OptionsGameDialog(GameScreen.this, R.style.Crawl);
            dialog.setOwnerActivity(GameScreen.this);
            //El mostrem
            dialog.show();
            return false;
        }



    }


package com.example.splansac7alumnes.crawlgeon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        Button win = (Button) findViewById(R.id.buttonWin);
        win.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creem el nou dialog que hem definit a la nostra classe OptionsDialog
                OptionsWinDialog dialog = new OptionsWinDialog();
                //El mostrem
                dialog.show(getSupportFragmentManager(),"win");

            }
        });

        Button lost = (Button) findViewById(R.id.buttonLose);
        lost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creem el nou dialog que hem definit a la nostra classe OptionsDialog
                OptionsLostDialog dialog = new OptionsLostDialog(GameScreen.this,R.style.Crawl);
                //El mostrem
                dialog.show();

            }
        });

    }

}

package com.example.splansac7alumnes.crawlgeon;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        ((TextView) findViewById(R.id.textAbout)).setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textAbout2)).setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textAbout3)).setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));
        Button back = (Button) findViewById(R.id.botoBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); //ANIMACIO FADE
            }
        });

    }
}

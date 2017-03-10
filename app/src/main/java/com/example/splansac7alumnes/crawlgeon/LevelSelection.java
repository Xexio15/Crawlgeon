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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);

        Button lvlSel_To_DungSel = (Button) findViewById(R.id.buttonLvlSelToDungSel);

        lvlSel_To_DungSel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button options = (Button) findViewById(R.id.buttonOptions);

        options.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                OptionsDialog dialog = new OptionsDialog();
                dialog.show(getSupportFragmentManager(),"opt");
            }
        });
    }
}

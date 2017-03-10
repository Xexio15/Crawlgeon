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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);

        Button play = (Button) findViewById(R.id.buttonPlay);

        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent menu_To_Sel = new Intent(MainMenu.this, DungeonSelection.class);
                startActivity(menu_To_Sel);
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

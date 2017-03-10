package com.example.splansac7alumnes.crawlgeon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class DungeonSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon_selection);

        Button sel_To_Menu = (Button) findViewById(R.id.buttonSelToMen);

        sel_To_Menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button sel_To_LvlSel = (Button) findViewById(R.id.buttonDungeon);

        sel_To_LvlSel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent toLvlSel = new Intent(DungeonSelection.this, LevelSelection.class);
                startActivity(toLvlSel);
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

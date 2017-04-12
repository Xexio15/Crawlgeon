package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {
    //PROVISIONAL
    private TilesArray tiles;
    private static final int BASIC = 0;
    private static final int DEFENSE = 1;
    private static final int FIRE = 2;
    private static final int ARCANE = 3;
    private static final int ICE = 4;
    private static final int LIGHTING = 5;
    private static final int HEALTH = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem la pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        this.tiles = new TilesArray(GameScreen.this);

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

        Button omplena = (Button) findViewById(R.id.tiles);
        omplena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillGrid();
            }
        });
    }

    public void fillGrid(){
        GridView tablero = (GridView) findViewById(R.id.tablero);
        tablero.setNumColumns(7);
        tablero.setAdapter(new ImageAdapter(this,tiles));
        tablero.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TextView text = (TextView)findViewById(R.id.textocualquiera);

                //Ataque basico
                if((v.getTag()).equals(tiles.getArray().get(BASIC).getImatge().getTag())){
                    text.setText("basic");
                }

                //Defensa
                if((v.getTag()).equals(tiles.getArray().get(DEFENSE).getImatge().getTag())){
                    text.setText("shield");
                }

                //Fuego
                if((v.getTag()).equals(tiles.getArray().get(FIRE).getImatge().getTag())){
                    text.setText("fire");
                }

                //Arcano
                if((v.getTag()).equals(tiles.getArray().get(ARCANE).getImatge().getTag())){
                    text.setText("arcane");
                }

                //Hielo
                if((v.getTag()).equals(tiles.getArray().get(ICE).getImatge().getTag())){
                    text.setText("ice");
                }

                //Rayo
                if((v.getTag()).equals(tiles.getArray().get(LIGHTING).getImatge().getTag())){
                    text.setText("lighting");
                }

                //Cura
                if((v.getTag()).equals(tiles.getArray().get(HEALTH).getImatge().getTag())){
                    text.setText("health");
                }


            }
        });


    }
        /*
        * Metodo para deshabilitar el boton de atras
        */
        public boolean onKeyDown(int keyCode, KeyEvent event){
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                Dialog dialog = new OptionsGameDialog(GameScreen.this, R.style.Crawl);
                dialog.setOwnerActivity(GameScreen.this);
                //El mostrem
                dialog.show();
                return true;
            }
            return super.onKeyDown(keyCode,event);

        }



    }


package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splansac7alumnes.crawlgeon.Tiles.Tile;

import java.util.ArrayList;
import java.util.Iterator;

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
    protected GridView tablero;
    protected int actual;
    protected Tile tipoTile;
    protected boolean iguales;
    protected ArrayList<Integer> seleccion;
    protected int posicionAnterior = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem la pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        this.tiles = new TilesArray(GameScreen.this);
        this.seleccion = new ArrayList<>();
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

        Button omple = (Button) findViewById(R.id.tiles);
        omple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillGrid();
            }
        });
    }

    public void fillGrid(){
        tablero = (GridView) findViewById(R.id.tablero);
        tablero.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE); //Activamos el modo de seleccion multiple
        tablero.setNumColumns(7);
        tablero.setAdapter(new ImageAdapter(this,tiles));
        tablero.setOnTouchListener(new AdapterView.OnTouchListener(){
            //Guardar en una variable uno de los objetos seleccionados
            //Con un booleano comparar si el objeto seleccionado anterior y el actual son iguales
            //Si el booleano = true, usar el objeto de la variable para decidir que hacer
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(seleccion.size() < 49) {
                    TextView text = (TextView) findViewById(R.id.textocualquiera);
                    int posicion = tablero.pointToPosition((int) event.getX(), (int) event.getY());
                    ImageView imatge = ((ImageView) tablero.getItemAtPosition(posicion));

                    if (event.getAction() == MotionEvent.ACTION_DOWN) {//Poner el dedo en la pantalla
                        iguales = true;
                        if (posicion == -1) {
                            text.setText("invalid");
                        } else {
                            actual = (int) (imatge.getTag());
                        }
                        return true;
                    }

                    else if (event.getAction() == MotionEvent.ACTION_MOVE) {//Deslizar el dedo por la pantalla
                        int anterior = actual;
                        posicion = tablero.pointToPosition((int) event.getX(), (int) event.getY());
                        actual = (int) ((ImageView) tablero.getItemAtPosition(posicion)).getTag();

                        if (actual != anterior) {
                            iguales = false;
                            seleccion.add(null);
                        } else {
                            if(posicion != posicionAnterior) {
                                seleccion.add(posicion);
                            }
                        }

                        posicionAnterior = posicion;
                        return true;
                    }

                    else if (event.getAction() == MotionEvent.ACTION_UP) {//Levantar el dedo de la pantalla
                        if(seleccion.size() >= 3) {
                            if (iguales) {
                                Tile elemento=tile(imatge);
                                text.setText("BOOM" + elemento.getElement());
                                realizarHechizo(seleccion, elemento);
                            } else {
                                text.setText("NO NO...Diferentes");
                            }
                        }else if (seleccion.size() < 3){
                            text.setText("Minimo 3");
                        }

                        seleccion = new ArrayList<>();
                        posicionAnterior = -1;
                        return true;
                    }
                }

                seleccion = new ArrayList<>();
                return false;
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

    /*
     * Metodo para realizar el hechizo
     */
        public void realizarHechizo(ArrayList<Integer> seleccion, Tile tile){
            ((ImageAdapter)(tablero.getAdapter())).realizarHechizo(seleccion);
        }

    /*
     * Metodo para coger un tile del tipo necesitado
     */
    public Tile tile(ImageView imatge){
            //Basico
            if ((imatge.getTag()).equals(tiles.getArray().get(BASIC).getImatge().getTag())) {
                return tiles.getArray().get(BASIC);
            }

            //Defensa
            if ((imatge.getTag()).equals(tiles.getArray().get(DEFENSE).getImatge().getTag())) {
              return tiles.getArray().get(DEFENSE);
            }

            //Fuego
            if ((imatge.getTag()).equals(tiles.getArray().get(FIRE).getImatge().getTag())) {
                return tiles.getArray().get(FIRE);
            }

            //Arcano
            if ((imatge.getTag()).equals(tiles.getArray().get(ARCANE).getImatge().getTag())) {
                return tiles.getArray().get(ARCANE);
            }

            //Hielo
            if ((imatge.getTag()).equals(tiles.getArray().get(ICE).getImatge().getTag())) {
                return tiles.getArray().get(ICE);
            }

            //Rayo
            if ((imatge.getTag()).equals(tiles.getArray().get(LIGHTING).getImatge().getTag())) {
                return tiles.getArray().get(LIGHTING);
            }

            //Cura
            if ((imatge.getTag()).equals(tiles.getArray().get(HEALTH).getImatge().getTag())) {
                return tiles.getArray().get(HEALTH);
            }

            return null;
        }



    }


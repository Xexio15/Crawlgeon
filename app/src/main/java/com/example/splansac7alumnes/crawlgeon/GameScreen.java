package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.splansac7alumnes.crawlgeon.Tiles.Health;
import com.example.splansac7alumnes.crawlgeon.Tiles.Shield;
import com.example.splansac7alumnes.crawlgeon.Tiles.Tile;
import com.example.splansac7alumnes.crawlgeon.monsters.Monster;

import java.util.ArrayList;

public class GameScreen extends AppCompatActivity {
    private TilesArray tiles;
    private static final int BASIC = 0;
    private static final int DEFENSE = 1;
    private static final int FIRE = 2;
    private static final int ARCANE = 3;
    private static final int ICE = 4;
    private static final int LIGHTING = 5;
    private static final int HEALTH = 6;

    protected GridView tablero;
    protected int actual = 1;
    protected Tile elemento;
    protected boolean iguales;
    protected ArrayList<Integer> seleccion;
    protected ArrayList<Integer> listaDeSelec;
    protected int posicionAnterior = -1;
    private int turnosPJ;
    private int turnosMonstruo;
    private int turnoActual = 0;
    private int dañoMonstruo;
    private int vidaMaxPersonaje;

    protected Controller controlador;
    private TextView vidaPJ;
    private TextView vidaMonstruo;
    private TextView armor;
    private ProgressBar barraVidaMonstruo;
    private ProgressBar barraVidaPJ;
    private ProgressBar barraArmor;
    private Monster monstruo;
    private Character personaje;
    private ImageView enemyImg;
    private ImageView pjImg;
    private Level nivel;
    private boolean enemigoMuerto;
    private boolean pjMuerto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem la pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        if(controlador == null) {
            controlador = controlador.getInstance();
        }

        //Inicializamos
        this.monstruo = controlador.getMonstruoNivelActual();
        this.personaje = controlador.getPersonaje();
        this.nivel = controlador.getActual();
        this.tiles = new TilesArray(GameScreen.this);
        this.seleccion = new ArrayList<>();
        this.listaDeSelec = new ArrayList<>();
        this.vidaPJ = (TextView) findViewById(R.id.vidaPJ);
        this.vidaMonstruo = (TextView) findViewById(R.id.vidaEnemigo);
        this.armor  = (TextView) findViewById(R.id.armorPJ);
        this.armor.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));//Canviem la font del text
        this.vidaMonstruo.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));//Canviem la font del text
        this.vidaPJ.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/PixelFont.ttf"));//Canviem la font del text
        this.barraVidaMonstruo = (ProgressBar) findViewById(R.id.barraVidaEnemiga);
        this.barraVidaPJ = (ProgressBar) findViewById(R.id.barraVidaPJ);
        this.barraArmor = (ProgressBar) findViewById(R.id.barraArmorPJ);
        this.enemigoMuerto = false;
        this.pjMuerto = false;

        if(nivel.isBoss()){
            controlador.initMusica(GameScreen.this,R.raw.boss_music);
        }else{
            controlador.initMusica(GameScreen.this,R.raw.level_music);
        }
        controlador.playMusica();

        if(personaje == null){
            this.barraVidaPJ.setMax(100);
            this.vidaPJ.setText("" + barraVidaPJ.getMax());
            this.barraVidaPJ.setProgress(barraVidaPJ.getMax());
            this.turnosPJ=1;
        }else {
            pjImg= (ImageView) findViewById(R.id.pjImg);
            pjImg.setImageResource(personaje.getID());
            this.turnosPJ=controlador.getTurnosPJ();

            this.vidaMaxPersonaje=controlador.getVidaPersonaje();
            this.barraArmor.setMax((vidaMaxPersonaje*20)/100);
            this.barraArmor.setProgress(0);
            this.armor.setText(""+0);

            this.barraVidaPJ.setMax(vidaMaxPersonaje);
            this.vidaPJ.setText("" + barraVidaPJ.getMax());
            this.barraVidaPJ.setProgress(barraVidaPJ.getMax());
        }
        if(monstruo == null){
            this.barraVidaMonstruo.setMax(50);
            this.vidaMonstruo.setText("" + this.barraVidaMonstruo.getMax());
            this.barraVidaMonstruo.setProgress(barraVidaMonstruo.getMax());
            this.turnosMonstruo = 1;
            this.dañoMonstruo = 5;
        }else {
            enemyImg= (ImageView) findViewById(R.id.imgEnemy);
            //enemyImg.setImageResource(monstruo.getID());
            animate(enemyImg, monstruo.getAnim());
            this.barraVidaMonstruo.setMax(controlador.getVidaMonstruo());
            this.vidaMonstruo.setText("" + this.barraVidaMonstruo.getMax());
            this.barraVidaMonstruo.setProgress(barraVidaMonstruo.getMax());
            this.turnosMonstruo = controlador.getTurnosMonstruo();
            this.dañoMonstruo = controlador.getDañoMonstruo();
        }

        fillGrid();//Llenamos la Grid cuando se inicia la activity

        Button win = (Button) findViewById(R.id.buttonWin);
        win.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creem el nou dialog que hem definit a la nostra classe OptionsDialog
                OptionsWinDialog dialog = new OptionsWinDialog(GameScreen.this, R.style.Crawl, controlador,2);
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
                OptionsLostDialog dialog = new OptionsLostDialog(GameScreen.this, R.style.Crawl, controlador);
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

    /**
     * Metodo que rellena la GridView i contiene los events de movimientos del dedo
     */
    public void fillGrid(){
        tablero = (GridView) findViewById(R.id.tablero);
        tablero.setFocusable(false);
        tablero.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE); //Activamos el modo de seleccion multiple
        tablero.setNumColumns(7);
        tablero.setAdapter(new ImageAdapter(this,tiles,(ImageView)findViewById(R.id.imAnim), controlador.getProbabilidadesIniciales(),controlador.getProbabilidades()));

        Animation animation = AnimationUtils.loadAnimation(GameScreen.this,android.R.anim.fade_in);
        GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .2f, .2f);
        tablero.setLayoutAnimation(controller);

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
                            seleccionarTile(posicion);
                            listaDeSelec.add(posicion);
                        }

                        return true;
                    }

                    else if (event.getAction() == MotionEvent.ACTION_MOVE) {//Deslizar el dedo por la pantalla
                        int anterior = actual;
                        posicion = tablero.pointToPosition((int) event.getX(), (int) event.getY());

                        if (posicion == -1) {
                            return false;
                        }
                        else {
                            seleccionarTile(posicion);
                            actual = (int) ((ImageView) tablero.getItemAtPosition(posicion)).getTag();
                            text.setText(""+posicionAnterior+" "+posicion);

                            if (actual != anterior) {
                                if(imatge != null) {
                                    iguales = false;
                                    seleccion.add(null);
                                    listaDeSelec.add(posicion);
                                }
                            } else {

                                if (posicion != posicionAnterior) {

                                    //Comprobamos que sean coniguas
                                    if(posicionAnterior == -1){
                                        if(imatge != null) {
                                            seleccion.add(posicion);
                                        }
                                    }
                                    else if((posicion == posicionAnterior-7 || posicion == posicionAnterior+7 || posicion == posicionAnterior-1 || posicion == posicionAnterior+1)) {
                                        if(imatge != null) {
                                            if (!seleccion.contains(posicion)) {
                                                seleccion.add(posicion);
                                            }
                                        }
                                    }
                                    listaDeSelec.add(posicion);
                                }
                            }


                        }
                        posicionAnterior = posicion;
                        return true;
                    }

                    else if (event.getAction() == MotionEvent.ACTION_UP) {//Levantar el dedo de la pantalla
                        deseleccionarTile(listaDeSelec);

                        if(seleccion.size() >= 3) {

                            if (iguales) {
                                elemento = tile(imatge);
                                if(elemento != null) {
                                    text.setText("BOOM" + elemento.getElement());
                                    realizarHechizo(seleccion, elemento);
                                    reordenarTablero();
                                    turnoActual++;
                                    realizarAtaqueEnemigo();

                                }
                            } else {
                                text.setText("NO NO...Diferentes");
                            }

                        }else if (seleccion.size() < 3){
                            text.setText("Minimo 3");
                        }
                        seleccion = new ArrayList<>();
                        listaDeSelec = new ArrayList<>();
                        posicionAnterior = -1;
                        return true;
                    }
                }
                seleccion = new ArrayList<>();
                listaDeSelec = new ArrayList<>();
                posicionAnterior = -1;
                return false;
            }
        });


    }
        /**
        * Metodo para deshabilitar el boton de atras
        */
        public boolean onKeyDown(int keyCode, KeyEvent event){
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                Dialog dialog = new OptionsGameDialog(GameScreen.this, R.style.Crawl, controlador);
                dialog.setOwnerActivity(GameScreen.this);
                //El mostrem
                dialog.show();
                return true;
            }
            return super.onKeyDown(keyCode,event);
        }

        /**
         * Metodo para realizar el hechizo
         */
        public void realizarHechizo(ArrayList<Integer> seleccion, Tile tile){

            this.imageAdapter().realizarHechizo(seleccion);
            if(tile.getFxID() != 0){
                controlador.initFX(GameScreen.this,tile.getFxID());
                controlador.restartFX();
                controlador.playFX();
            }

            if(!(tile instanceof Health) && !(tile instanceof Shield)){
                int daño = tile.getDamage() * seleccion.size();
                int vida = Integer.parseInt(vidaMonstruo.getText().toString());
                vida = vida - daño;

                if(vida > 0) {
                    this.vidaMonstruo.setText("" + vida);
                    actualizarBarra(barraVidaMonstruo, vida);
                }else{
                    actualizarBarra(barraVidaMonstruo, 0);
                    this.vidaMonstruo.setText("DEAD");
                    this.enemigoMuerto = true;
                    int vidapj = Integer.parseInt(vidaPJ.getText().toString());
                    controlador.desbloquearNivel();
                    if(vidapj == vidaMaxPersonaje){
                        winDialog(3);
                        puntuarNivel(3);
                    }else if(vidapj >= vidaMaxPersonaje/2) {
                        puntuarNivel(2);
                        winDialog(2);
                    }else{
                        puntuarNivel(1);
                        winDialog(1);
                    }
                }
            }else{

                if(tile instanceof Health){
                    int vida = Integer.parseInt(vidaPJ.getText().toString());
                    if(vida < vidaMaxPersonaje) {
                        if(vida + tile.getDamage() * seleccion.size() > vidaMaxPersonaje){
                            vidaPJ.setText(""+vidaMaxPersonaje);
                        }else {
                            vida = vida + tile.getDamage() * seleccion.size();
                            vidaPJ.setText("" + vida);
                        }
                    }
                }else {
                    int armadura = barraArmor.getProgress() + (vidaMaxPersonaje / 100) * seleccion.size();
                    if (armadura >= barraArmor.getMax()) {
                        barraArmor.setProgress(barraArmor.getMax());
                        armor.setText(barraArmor.getMax()+"");
                    }else{
                        barraArmor.setProgress(armadura);
                        armor.setText(armadura+"");
                    }
                }
            }

        }

    public void realizarAtaqueEnemigo(){
        if (turnoActual==turnosPJ && !this.enemigoMuerto && !this.pjMuerto){
            for(int i = 0; i<turnosMonstruo; i++){
                int vida = Integer.parseInt(vidaPJ.getText().toString());
                int armadura = barraArmor.getProgress();
                armadura = armadura - dañoMonstruo;
                if (armadura > 0) {
                    barraArmor.setProgress(armadura);
                    armor.setText(armadura+"");
                }else{
                    barraArmor.setProgress(armadura);
                    armor.setText(0 + "");
                    vida = vida + armadura;
                }

                if(vida > 0) {
                    this.vidaPJ.setText("" + vida);
                    actualizarBarra(barraVidaPJ, vida);
                }else{
                    actualizarBarra(barraVidaPJ, 0);
                    this.vidaPJ.setText("DEAD");
                    this.pjMuerto = true;
                    loseDialog();
                }
            }
            turnoActual = 0;
        }
    }

    /**
     * Pone en modo seleccion la Tile de la posicion pasada
     */
    public void seleccionarTile(int posicion){
        this.imageAdapter().seleccionarTile(posicion);
    }

    /**
     * Quita el modo seleccion de la lista de Tiles seleccionadas
     */
    public void deseleccionarTile(ArrayList<Integer> listaDeSelec){
        this.imageAdapter().deseleccionarTile(listaDeSelec);
    }

    /**
     * Enseña la puntuacion del nivel en la pantalla de niveles
     */
    public void puntuarNivel(int puntuacion){
        //Cuando haya mas dungeons tendremos de coger el num de la dungeon aqui
        if (puntuacion > nivel.getPuntuacion()){
            nivel.setPuntuacion(puntuacion);
        }
    }

    /**
     * Cambia el valor de la barra de vida pasada
     */
    public void actualizarBarra(ProgressBar bar, int vida){
        bar.setProgress(vida);
    }

    /**
     * Metodo para coger un tile del tipo necesitado
     */
    public Tile tile(ImageView imatge){
        if(imatge != null) {
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
        }
        return null;
    }

    /**
     * Nos muestra el dialogo de ganar
     */
    public void winDialog(int puntuacion){
        OptionsWinDialog dialog = new OptionsWinDialog(GameScreen.this, R.style.Crawl, controlador, puntuacion);
        dialog.setOwnerActivity(GameScreen.this);
        //El mostrem
        dialog.show();
    }

    public void loseDialog(){
        OptionsLostDialog dialog = new OptionsLostDialog(GameScreen.this, R.style.Crawl, controlador);
        dialog.setOwnerActivity(GameScreen.this);
        //El mostrem
        dialog.show();
    }

    /*
     * Comprueba si hay movimientos para hacer y, si no hay, reordena las fichas que hay actualmente en el tablero
     */
    public void reordenarTablero(){
        while(!this.imageAdapter().check_grid()){
            this.imageAdapter().reordenarTablero();
        }

        this.imageAdapter().notifyDataSetChanged();
    }

    public ImageAdapter imageAdapter(){
        return ((ImageAdapter)(tablero.getAdapter()));
    }


    public void animate(ImageView im, int imSequence){
        if(imSequence != 0) {
            im.setBackgroundResource(imSequence);
            AnimationDrawable anim = (AnimationDrawable) im.getBackground();
            anim.start();
        }else{
            im.setImageResource(monstruo.getID());
        }
    }


}


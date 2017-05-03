package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.PowerManager;
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
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.splansac7alumnes.crawlgeon.Tiles.Arcane;
import com.example.splansac7alumnes.crawlgeon.Tiles.Basic;
import com.example.splansac7alumnes.crawlgeon.Tiles.Fire;
import com.example.splansac7alumnes.crawlgeon.Tiles.Health;
import com.example.splansac7alumnes.crawlgeon.Tiles.Ice;
import com.example.splansac7alumnes.crawlgeon.Tiles.Lighting;
import com.example.splansac7alumnes.crawlgeon.Tiles.Shield;
import com.example.splansac7alumnes.crawlgeon.Tiles.Tile;
import com.example.splansac7alumnes.crawlgeon.monsters.Monster;

import java.util.ArrayList;
import java.util.Locale;

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
    private float dañoMonstruo;
    private float vidaMaxPersonaje;
    private float armaduraMaxPersonaje;

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
    private boolean animationRunning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//Posem la pantalla completa
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        if (controlador == null) {
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
        this.armor = (TextView) findViewById(R.id.armorPJ);
        TextView lvl = ((TextView) findViewById(R.id.lvl));
        lvl.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/PixelFont.ttf"));
        lvl.setText("LvL " + personaje.getNivel());
        this.armor.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/PixelFont.ttf"));//Canviem la font del text
        this.vidaMonstruo.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/PixelFont.ttf"));//Canviem la font del text
        this.vidaPJ.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/PixelFont.ttf"));//Canviem la font del text
        this.barraVidaMonstruo = (ProgressBar) findViewById(R.id.barraVidaEnemiga);
        this.barraVidaPJ = (ProgressBar) findViewById(R.id.barraVidaPJ);
        this.barraArmor = (ProgressBar) findViewById(R.id.barraArmorPJ);
        this.enemigoMuerto = false;
        this.pjMuerto = false;

        //Selector de musica si es boss o no
        if (nivel.isBoss()) {
            controlador.initMusica(GameScreen.this, R.raw.boss_music);
        } else {
            controlador.initMusica(GameScreen.this, R.raw.level_music);
        }
        controlador.playMusica();

        //Animacion estatica del PJ
        pjImg = (ImageView) findViewById(R.id.pjImg);
        animate(pjImg, personaje.getStaticAnim());

        //Inicializacion de turnos del PJ
        this.turnosPJ = controlador.getTurnosPJ();

        //Inicializacion de las barras de vida i armadura i textos del PJ
        this.vidaMaxPersonaje = controlador.getVidaPersonaje();
        this.armaduraMaxPersonaje = (Math.round(vidaMaxPersonaje) * 20) / 100;
        this.barraArmor.setMax(Math.round(armaduraMaxPersonaje));
        actualizarBarra(barraArmor, 0);
        this.armor.setText("" + 0 + ".00");
        this.barraVidaPJ.setMax(Math.round(vidaMaxPersonaje));
        this.vidaPJ.setText("" + String.format(Locale.US, "%.2f", vidaMaxPersonaje));
        actualizarBarra(this.barraVidaPJ, barraVidaPJ.getMax());

        //Animacion estatica del mosntruo
        enemyImg = (ImageView) findViewById(R.id.imgEnemy);
        animate(enemyImg, monstruo.getStaticAnim());

        //inicializacion de barra enemiga
        this.barraVidaMonstruo.setMax(Math.round(controlador.getVidaMonstruo()));
        this.vidaMonstruo.setText("" + this.barraVidaMonstruo.getMax() + ".00");
        actualizarBarra(this.barraVidaMonstruo, barraVidaMonstruo.getMax());

        //Inicializacion de turnos i daño del monstruo
        this.turnosMonstruo = controlador.getTurnosMonstruo();
        this.dañoMonstruo = controlador.getDañoMonstruo();

        fillGrid();//Llenamos la Grid cuando se inicia la activity
    }

    /**
     * Metodo que contiene los events de movimientos del dedo
     */
    public void fillGrid() {
        tablero = (GridView) findViewById(R.id.tablero);
        tablero.setFocusable(false);
        tablero.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE); //Activamos el modo de seleccion multiple
        tablero.setNumColumns(7);
        tablero.setAdapter(new ImageAdapter(this, tiles, (ImageView) findViewById(R.id.imAnim), controlador.getProbabilidadesIniciales(), controlador.getProbabilidades()));

        Animation animation = AnimationUtils.loadAnimation(GameScreen.this, android.R.anim.fade_in);
        GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .2f, .2f);
        tablero.setLayoutAnimation(controller);
        this.reordenarTablero();//Si la primera tabla no es valida
        tablero.setOnTouchListener(new AdapterView.OnTouchListener() {
            //Guardar en una variable uno de los objetos seleccionados
            //Con un booleano comparar si el objeto seleccionado anterior y el actual son iguales
            //Si el booleano = true, usar el objeto de la variable para decidir que hacer
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!animationRunning) {
                    if (seleccion.size() < 49) {
                        //TextView text = (TextView) findViewById(R.id.textocualquiera);
                        int posicion = tablero.pointToPosition((int) event.getX(), (int) event.getY());
                        ImageView imatge = ((ImageView) tablero.getItemAtPosition(posicion));


                        if (event.getAction() == MotionEvent.ACTION_DOWN) {//Poner el dedo en la pantalla
                            iguales = true;

                            if (posicion == -1) {
                            } else {
                                actual = (int) (imatge.getTag());
                                seleccionarTile(posicion);
                                listaDeSelec.add(posicion);
                            }
                            return true;
                        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {//Deslizar el dedo por la pantalla
                            int anterior = actual;
                            posicion = tablero.pointToPosition((int) event.getX(), (int) event.getY());

                            if (posicion == -1) {
                                return false;
                            } else {
                                seleccionarTile(posicion);
                                actual = (int) ((ImageView) tablero.getItemAtPosition(posicion)).getTag();

                                if (actual != anterior) {
                                    if (imatge != null) {
                                        iguales = false;
                                        seleccion.add(null);
                                        listaDeSelec.add(posicion);
                                    }
                                } else {

                                    if (posicion != posicionAnterior) {

                                        //Comprobamos que sean coniguas
                                        if (posicionAnterior == -1) {
                                            if (imatge != null) {
                                                seleccion.add(posicion);
                                            }
                                        } else if ((posicion == posicionAnterior - 7 || posicion == posicionAnterior + 7 || posicion == posicionAnterior - 1 || posicion == posicionAnterior + 1)) {
                                            if (imatge != null) {
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
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {//Levantar el dedo de la pantalla
                            deseleccionarTile(listaDeSelec);

                            if (seleccion.size() >= 3) {

                                if (iguales) {
                                    elemento = tile(imatge);
                                    if (elemento != null) {
                                        //realizarHechizo(seleccion, elemento);
                                        animationRunning = true;
                                        pjAttackAnimation(seleccion, elemento);
                                        reordenarTablero();
                                        turnoActual++;
                                        //realizarAtaqueEnemigo();

                                    }
                                }
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
                return false;
            }
        });


    }

    /**
     * Metodo para deshabilitar el boton de atras
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Dialog dialog = new OptionsGameDialog(GameScreen.this, R.style.Crawl, controlador);
            dialog.setOwnerActivity(GameScreen.this);
            //El mostrem
            dialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Metodo que devuelve la animacion correspondiente a la tile
     * @param tile
     * @return
     */
    public int getAnimacionAtaquePJ(Tile tile){
        if(tile instanceof Basic){
            return personaje.getBasicAnim();
        }else if(tile instanceof Shield){
            return personaje.getDefAnim();
        }else if(tile instanceof Health){
            return personaje.getHealAnim();
        }else if(tile instanceof Fire){
            return personaje.getFireAnim();
        }else if(tile instanceof Ice){
            return personaje.getIceAnim();
        }else if(tile instanceof Arcane){
            return personaje.getArcaneAnim();
        }else if(tile instanceof Lighting){
            return personaje.getElectricAnim();
        }
        return 0;
    }

    /**
     * Metodo para realizar el movimiento de tiles, reporducir sonido i esperar al fin de la animacion del PJ
     * @param seleccion
     * @param tile
     */
    public void pjAttackAnimation(final ArrayList<Integer> seleccion, final Tile tile){
        //Primero dejamos que se muevan la fichas i se haga la animacion de Tile i haga el sonido
        imageAdapter().realizarHechizo(seleccion);
        if (tile.getFxID() != 0) {
            controlador.initFX(GameScreen.this, tile.getFxID());
            controlador.restartFX();
            controlador.playFX();
        }

        int idAnim = getAnimacionAtaquePJ(tile);
        if(idAnim == 0){
            idAnim = personaje.getElectricAnim();
        }

        //Tenemos el handler d ela animacion
        AnimationDrawable anim = (AnimationDrawable)getDrawable(idAnim);
        pjImg.setBackgroundResource(idAnim);
        AnimationDrawableHandler cad = new AnimationDrawableHandler(anim) {
            //cuando la animacion termine se ejecutara esto
            @Override
            void onAnimationFinish() {
                //realizamos el daño
                realizarHechizo(seleccion, tile);
                animate(pjImg, personaje.getStaticAnim());
                //Llamamos al mismo metodo pero para el ataque enemigo
                enemyAttackAnimation();
            }
        };
        pjImg.setBackgroundDrawable(cad);
        //iniciamos la animacion
        cad.start();
    }

    /**
     * Metodo de espera a la animacion enemiga
     */
    public void enemyAttackAnimation(){
        enemyImg.setBackgroundResource(monstruo.getAttackAnim());
        AnimationDrawable anim = (AnimationDrawable)getDrawable(monstruo.getAttackAnim());
        AnimationDrawableHandler cad = new AnimationDrawableHandler(anim) {
            @Override
            void onAnimationFinish() {
                realizarAtaqueEnemigo();
                animationRunning = false;
                animate(enemyImg, monstruo.getStaticAnim());
            }
        };
        enemyImg.setBackgroundDrawable(cad);
        //iniciamos la animacion
        cad.start();
    }

    /**
     * Metodo para realizar el hechizo
     */
    public void realizarHechizo(ArrayList<Integer> seleccion, Tile tile) {
        if (!(tile instanceof Health) && !(tile instanceof Shield)) {
            float daño = (tileDamage(tile) * seleccion.size());
            float vida = vidaMonstruo();
            vida = vida - daño;

            if (vida > 0) {
                this.vidaMonstruo.setText("" + String.format(Locale.US, "%.2f", vida));
                actualizarBarra(barraVidaMonstruo, vida);
            } else {
                actualizarBarra(barraVidaMonstruo, 0);
                this.vidaMonstruo.setText("DEAD");
                this.enemigoMuerto = true;
                float vidapj = vidaPJ();
                controlador.desbloquearNivel();
                if (vidapj >= vidaMaxPersonaje / 100 * 80) {
                    winDialog(3);
                    puntuarNivel(3);
                } else if (vidapj >= vidaMaxPersonaje / 2) {
                    winDialog(2);
                    puntuarNivel(2);
                } else {
                    winDialog(1);
                    puntuarNivel(1);
                }
            }
        } else {

            if (tile instanceof Health) {
                float vida = vidaPJ() + tileSupport(tile) * seleccion.size();
                if (vida >= vidaMaxPersonaje) {
                    vidaPJ.setText("" + String.format(Locale.US, "%.2f", vidaMaxPersonaje));
                    vida = vidaMaxPersonaje;
                } else {
                    vidaPJ.setText("" + String.format(Locale.US, "%.2f", vida));
                }
                actualizarBarra(barraVidaPJ, vida);
            } else {
                float armadura = armaduraPJ() + tileSupport(tile) * seleccion.size();
                if (armadura >= armaduraMaxPersonaje) {
                    armadura = armaduraMaxPersonaje;
                    armor.setText(armaduraMaxPersonaje + "");
                } else {
                    armor.setText(String.format(Locale.US, "%.2f", armadura) + "");
                }
                actualizarBarra(barraArmor, Math.round(armadura));
            }
        }

    }

    public void realizarAtaqueEnemigo() {
        if (turnoActual == turnosPJ && !this.enemigoMuerto && !this.pjMuerto) {
            for (int i = 0; i < turnosMonstruo; i++) {
                float vida = vidaPJ();
                float armadura = armaduraPJ();
                armadura = armadura - dañoMonstruo;
                if (armadura > 0) {
                    actualizarBarra(barraArmor, Math.round(armadura));
                    armor.setText(String.format(Locale.US, "%.2f", armadura) + "");
                } else {
                    actualizarBarra(barraArmor, Math.round(armadura));
                    armor.setText(0 + "");
                    vida = vida + armadura;
                }

                if (vida > 0) {
                    this.vidaPJ.setText("" + String.format(Locale.US, "%.2f", vida));
                    actualizarBarra(barraVidaPJ, vida);
                } else {
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
    public void seleccionarTile(int posicion) {
        this.imageAdapter().seleccionarTile(posicion);
    }

    /**
     * Quita el modo seleccion de la lista de Tiles seleccionadas
     */
    public void deseleccionarTile(ArrayList<Integer> listaDeSelec) {
        this.imageAdapter().deseleccionarTile(listaDeSelec);
    }

    /**
     * Enseña la puntuacion del nivel en la pantalla de niveles
     */
    public void puntuarNivel(int puntuacion) {
        //Cuando haya mas dungeons tendremos de coger el num de la dungeon aqui
        if (puntuacion > nivel.getPuntuacion()) {
            nivel.setPuntuacion(puntuacion);
        }
    }

    /**
     * Cambia el valor de la barra de vida pasada
     */
    public void actualizarBarra(ProgressBar bar, float vida) {
        bar.setProgress(Math.round(vida));
    }

    /**
     * Metodo para coger un tile del tipo necesitado
     */
    public Tile tile(ImageView imatge) {
        if (imatge != null) {
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
    public void winDialog(int puntuacion) {
        Level nivel = controlador.getNivel(controlador.getNivelActual());
        int expGanada;
        if (puntuacion < nivel.getPuntuacion()) {
            expGanada = nivel.getXpBase();
        } else {
            expGanada = nivel.getXpBase() + (nivel.getXpPerStar() * (puntuacion - nivel.getPuntuacion()));
        }
        String score = "";
        if (personaje.subirEXP(expGanada)) {
            score = "UP!  ";
        } else {
            score = expGanada + " XP!";
        }
        OptionsWinDialog dialog = new OptionsWinDialog(GameScreen.this, R.style.Crawl, controlador, puntuacion, score, personaje.getXpActual(), personaje.getXpNecesaria());
        dialog.setOwnerActivity(GameScreen.this);
        //El mostrem
        controlador.saveData();
        dialog.show();

    }

    public void loseDialog() {
        OptionsLostDialog dialog = new OptionsLostDialog(GameScreen.this, R.style.Crawl, controlador);
        dialog.setOwnerActivity(GameScreen.this);
        //El mostrem
        controlador.saveData();
        dialog.show();
    }

    /**
     * Comprueba si hay movimientos para hacer y, si no hay, reordena las fichas que hay actualmente en el tablero
     */
    public void reordenarTablero() {
        while (!this.imageAdapter().check_grid()) {
            this.imageAdapter().reordenarTablero();
        }

        this.imageAdapter().notifyDataSetChanged();
    }

    /**
     * Returna el adapter
     * @return
     */
    public ImageAdapter imageAdapter() {
        return ((ImageAdapter) (tablero.getAdapter()));
    }


    /**
     * Anima la ImageView pasada con la animacion pasada (Utilizado para static)
     * @param im
     * @param imSequence
     */
    public void animate(ImageView im, int imSequence) {
        if (imSequence != 0) {
            im.setBackgroundResource(imSequence);
            AnimationDrawable anim = (AnimationDrawable) im.getBackground();
            anim.start();
        } else {
            im.setImageResource(monstruo.getID());
        }
    }

    public float vidaPJ(){
        return Float.parseFloat(vidaPJ.getText().toString());
    }

    public float vidaMonstruo(){
        return Float.parseFloat(vidaMonstruo.getText().toString());
    }

    public float armaduraPJ(){
        return Float.parseFloat(armor.getText().toString());
    }

    /**
     * retorna el daño con el bonus de la tile pasada
     * @param tile
     * @return
     */
    public float tileDamage(Tile tile){
        float tileDamage = tile.getDamage();
        tileDamage = tileDamage + tileDamage*personaje.getBonusTile();
        return tileDamage;
    }

    /**
     * Retorna la curacion o la defensa de la tile pasada
     * @param tile
     * @return
     */
    public float tileSupport(Tile tile){
        float tileDamage = tile.getDamage();
        tileDamage = (vidaMaxPersonaje/100)*tileDamage;
        return tileDamage;
    }






    @Override
    protected void onResume() {
        super.onResume();
        if(!controlador.isPlayingMusica()) {
            if(!controlador.getActual().isBoss()){
                controlador.initMusica(this, R.raw.level_music);
            }else{
                controlador.initMusica(this, R.raw.boss_music);
            }
            controlador.setShouldPlay (true);
            controlador.resumeMusica();
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        PowerManager mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

        //shouldPlay = false;

        if (!mPowerManager.isInteractive()){
            controlador.setShouldPlay(false);
        }
        if (!controlador.isShouldPlay() && controlador.isPlayingMusica()) { // it won't pause music if shouldPlay is true
            controlador.pauseMusica();
        }
    }

}


package com.example.splansac7alumnes.crawlgeon;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.splansac7alumnes.crawlgeon.Tiles.Tile;
import com.example.splansac7alumnes.crawlgeon.monsters.Monster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Created by Sergio Plans on 21/04/2017.
 */

public class Controller{
    private Data data;
    private File dades;
    private MediaPlayer reproMusica;
    private boolean isPlayingMusica;
    private boolean isPlayingFX;
    private int nivelActual = 1;
    private int dungActual;
    private MediaPlayer reproFX;
    private static Controller instance = new Controller();
    private Context context;
    private int seekMusic;
    private boolean shouldPlay;

    /**
     * Nos da la instancia del controlador
     */
    public static Controller getInstance(){

        return instance;
    }

    /**
     * Constructor privado (Singleton)
     */
    private Controller(){

    }

    public boolean isShouldPlay(){
        return shouldPlay;
    }

    public void setShouldPlay(boolean x){
        shouldPlay = x;
    }
    /**
     * Elimina los datos completamente
     */
    public void deleteData(){
        this.data = new Data();
        changeMusicVolume(100);
        changeFXVolume(100);
    }

    /**
     * Set del context inicial
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Inicializa Data si es null sino lo carga
     */
    public  void initData(){
        if(!loadData()){
            this.data = new Data();
            changeMusicVolume(100);
            changeFXVolume(100);
        }
    }
    /**
     * Inicia el reproductor de musica con el archivo pasado
     */
    public void initMusica(Context cont, int id){
        if(reproMusica == null) {
            reproMusica = MediaPlayer.create(cont, id);
            reproMusica.setLooping(true);
        }
    }

    /**
     * Inicia la reproduccion de musica
     */
    public void playMusica(){
        isPlayingMusica = true;
        reproMusica.setVolume(data.getVolumenMusica()/100,data.getVolumenMusica()/100);
        reproMusica.start();
    }

    /**
     * Detiene la musica i pone a null el reporductor
     */
    public void stopMusica(){
        if(reproMusica != null) {
            isPlayingMusica = false;
            reproMusica.stop();
            reproMusica = null;
        }
    }

    /**
     * Recupera on habia parat
     */
    public void resumeMusica(){
        isPlayingMusica = true;
        reproMusica.setVolume(data.getVolumenMusica()/100,data.getVolumenMusica()/100);
        reproMusica.start();
        reproMusica.seekTo(seekMusic);

    }

    /**
     * Atura la musica guardant el punt on s'ha parat
     */
    public void pauseMusica(){
        isPlayingMusica = false;
        reproMusica.pause();
        seekMusic = reproMusica.getCurrentPosition();
        reproMusica = null;
    }

    /**
     * Pone la reproduccion de la musica al segundo 0
     */
    public void restartMusica(){
        reproMusica.seekTo(0);
    }


    /**
     * Devuelve si se esta reproduciendo musica
     */
    public boolean isPlayingMusica(){
        return isPlayingMusica;
    }

    /**
     * Inicia el reproductor de efectos
     */
    public void initFX(Context cont, int id){
        reproFX = null;
        if(reproFX == null) {
            reproFX = MediaPlayer.create(cont, id);
        }
    }

    /**
     * Reproduce el efecto inicializado
     */
    public void playFX(){
        reproFX.setVolume(data.getVolumenFX()/100, data.getVolumenFX()/100);
        reproFX.start();
    }

    /**
     * Detiene el reporductor de efectos
     */
    public void stopFX(){
        if(reproFX != null) {
            isPlayingFX = false;
            reproFX.stop();
            reproFX = null;
        }
    }

    /**
     * Pone la reproduccion del efecto al segundo 0
     */
    public void restartFX(){
        reproFX.seekTo(0);
    }

    /**
     * Devuelve si se esta reproduciendo un efecto
     */
    public boolean isPlayingFX(){
        return isPlayingFX;
    }

    /**
     * Devuelve el reproductor de efectos
     */
    public MediaPlayer getFXPlayer(){
        return reproFX;
    }

    /**
     * Cambia el volumen del reproductor de efectos (0-100)
     */
    public void changeFXVolume(float fx){
        if(reproFX != null) {
            reproFX.setVolume(fx/100, fx/100);
        }
        data.setVolumenFX(fx);
    }

    /**
     * Cambia el volumen del reproductor de musica (0-100)
     */
    public void changeMusicVolume(float music){
        if(reproMusica != null) {
            reproMusica.setVolume(music/100, music/100);
        }
        data.setVolumenMusica(music);
    }

    /**
     * Devuelve el volumen actual de la musica
     */
    public float getMusicVolume(){
        return data.getVolumenMusica();
    }

    /**
     * Devuelve el volumen actual de los efectos
     */
    public float getFXVolume(){
        return data.getVolumenFX();
    }

    public void playButtonSound(Context ctx){
        this.initFX(ctx, R.raw.buttonsound);
        this.restartFX();
        this.playFX();
    }

    public void playBackButtonSound(Context ctx){
        this.initFX(ctx, R.raw.buttonbacksound);
        this.restartFX();
        this.playFX();
    }

    /**
     * Guarda datos
     */
    public boolean saveData(){
        ObjectOutput out = null;

        try {
            this.dades = new File(context.getFilesDir(),"CrawlgeonData.data");
            out = new ObjectOutputStream(new FileOutputStream(this.dades));
            out.writeObject(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Carga datos
     */
    public boolean loadData() {
        ObjectInputStream input;
        this.dades = new File(context.getFilesDir(), "CrawlgeonData.data");
        try {
            input = new ObjectInputStream(new FileInputStream(this.dades));
            this.data = (Data) input.readObject();
            input.close();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Devuelve el personaje guardado
     */
    public Character getPersonaje(){
        return data.getPersonaje();
    }

    /**
     * Cambia el nivel acutal
     * @param nivel
     */
    public void setNivelActual(int nivel){
        this.nivelActual = nivel;
    }

    /**
     * Devuelve el nivel actual
     * @return
     */
    public int getNivelActual(){
        return this.nivelActual;
    }

    /**
     * Comprueba si el nivel actual es boss i por lo tanto si es el ultimo
     * @return
     */
    public boolean isActualBoss(){
        return getActual().isBoss();
    }


    /**
     * Devuelve un array con las probabilidades iniciales del nivel actual
     * @return
     */
    public float[] getProbabilidadesIniciales(){
        return getActual().getProbabilidadesIniciales();
    }

    /**
     * Devuelve un array con las probabilidades de sustitucion del nivel actual
     * @return
     */
    public float[] getProbabilidades(){
        return getActual().getProbabilidades();
    }

    /**
     * Devuelve la id del monstruo correspondiente al nivel
     * @return
     */
    public Monster getMonstruoNivelActual(){
        return getActual().getMonstruo();
    }

    public void desbloquearNivel(){
        /*
        *   PARA CUANDO HAYA LOS 10 NIVELES PARA DESBLOQUEAR
        *
        *   if((nivelActual+1)<10) {
        *       this.getNivel(nivelActual).setDesbloqueado();
        *   }
        */

        if((nivelActual+1)<3) {
            this.getNivel(nivelActual+1).setDesbloqueado();
        }else if(nivelActual==2){
            this.getNivel(10).setDesbloqueado();
        }
    }

    public boolean isBloqueado(int i){
        return data.getNiveles().get(i-1).isBloqueado();
    }

    public int getTurnosPJ(){ return getActual().getTurnosPJ(); }

    public int getTurnosMonstruo(){ return getActual().getTurnosMonstruo(); };

    public Level getActual(){ return data.getNiveles().get(nivelActual-1); }

    public float getVidaMonstruo(){ return getActual().getVidaMonstruo(); }

    public float getDañoMonstruo(){ return getActual().getDañoMonstruo(); }

    public float getVidaPersonaje(){ return getPersonaje().getVida(); }

    /**
     * Devuelve el nivel a partir de el indice pasado por parametro
     * @param num
     * @return
     */
    public Level getNivel(int num){
        return data.getNiveles().get(num-1);
    }

    public int getNumLvlsBlocked(){
        int i = 0;
        for(int j = 2; j <= 10; j++){
            if(!isBloqueado(j)) i++;
        }
        return i;
    }

    public ArrayList<Tile> getTiles(){
        return new TilesArray(context).getArray();
    }

    public boolean getTutorial(){
        return this.data.getTutorial();
    }

    public void completarTutorial(){
        this.data.setTutorial(false);
    }
}

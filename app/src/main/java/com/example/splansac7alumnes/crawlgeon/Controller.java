package com.example.splansac7alumnes.crawlgeon;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.splansac7alumnes.crawlgeon.monsters.Monster;

import java.io.File;

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
        isPlayingMusica = false;
        reproMusica.stop();
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
        isPlayingFX = false;
        reproFX.stop();
        reproFX = null;
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

    /**
     * Guarda datos
     */
    public boolean saveData(){
        return false;
    }

    /**
     * Carga datos
     */
    public boolean loadData(){
        //codigo para cargar archivo
        //Si la carga bien retorna true i no se volvera a inicializar en el constructor
        if(data != null){
            return true;
        }else{
            return false;
        }
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
        if((nivelActual+1)<10) {
            data.getNiveles().get(nivelActual).setDesbloqueado();
        }
    }

    public boolean isBloqueado(int i){
        return data.getNiveles().get(i-1).isBloqueado();
    }

    public int getTurnosPJ(){ return getActual().getTurnosPJ(); };

    public int getTurnosMonstruo(){ return getActual().getTurnosMonstruo(); };

    public Level getActual(){ return data.getNiveles().get(nivelActual-1); }

    public int getVidaMonstruo(){ return getActual().getVidaMonstruo(); }

    public int getDañoMonstruo(){ return getActual().getDañoMonstruo(); }

    public int getVidaPersonaje(){ return getPersonaje().getVida(); }
}

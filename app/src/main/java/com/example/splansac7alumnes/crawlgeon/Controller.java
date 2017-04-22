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
}

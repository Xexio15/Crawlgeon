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

    public static Controller getInstance(){
        return instance;
    }
    private Controller(){

         if(!loadData()){
             this.data = new Data();
             changeMusicVolume(100);
             changeFXVolume(100);
         }
    }

    public void initMusica(Context cont, int id){
        if(reproMusica == null) {
            reproMusica = MediaPlayer.create(cont, id);
            reproMusica.setLooping(true);
        }
    }

    public void playMusica(){
        isPlayingMusica = true;
        reproMusica.start();
    }

    public void stopMusica(){
        isPlayingMusica = false;
        reproMusica.stop();
        reproMusica = null;
    }

    public boolean isPlayingMusica(){
        return isPlayingMusica;
    }

    public void initFX(Context cont, int id){
        if(reproFX == null) {
            reproFX = MediaPlayer.create(cont, id);
        }
    }

    public void playFX(){
        reproFX.start();
    }

    public void stopFX(){
        isPlayingFX = false;
        reproFX.stop();
        reproFX = null;
    }

    public void restartFX(){
        reproFX.seekTo(0);
    }

    public boolean isPlayingFX(){
        return isPlayingFX;
    }



    public MediaPlayer getFXPlayer(){
        return reproFX;
    }

    public void changeFXVolume(float fx){
        if(reproFX != null) {
            reproFX.setVolume(fx/100, fx/100);
        }
        data.setVolumenFX(fx);
    }

    public void changeMusicVolume(float music){
        if(reproMusica != null) {
            reproMusica.setVolume(music/100, music/100);
        }
        data.setVolumenMusica(music);
    }

    public float getMusicVolume(){
        return data.getVolumenMusica();
    }

    public float getFXVolume(){
        return data.getVolumenFX();
    }

    public boolean saveData(){
        return false;
    }
    public boolean loadData(){
        //codigo para cargar archivo
        //Si la carga bien retorna true i no se volvera a inicializar en el constructor
        if(data != null){
            return true;
        }else{
            return false;
        }

    }

    public Character getPersonaje(){
        return data.getPersonaje();
    }
}

package com.example.splansac7alumnes.crawlgeon;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Sergio Plans on 20/04/2017.
 */

public class Character implements Serializable {
    private int vida = 100;
    private int daño;
    private int nivel;
    private ImageView sprite;
    public Character (){

    }

    public int getDaño() {
        return daño;
    }

    public int getNivel() {
        return nivel;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }




}

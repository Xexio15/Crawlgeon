package com.example.splansac7alumnes.crawlgeon.monsters;

import android.content.Context;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Sergio Plans on 19/04/2017.
 */

public class Monster implements Serializable {
    private int vida;
    private int daño;
    private ImageView sprite;
    public Monster(int vida, int daño, ImageView sprite){
        this.vida = vida;
        this.daño = daño;
        this.sprite = sprite;
    }

    public int getVida(){
        return vida;
    }

    public int getDaño() {
        return daño;
    }

    public ImageView getSprite() {
        return sprite;
    }
}

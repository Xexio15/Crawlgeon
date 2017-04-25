package com.example.splansac7alumnes.crawlgeon.monsters;

import android.content.Context;
import android.widget.ImageView;

import com.example.splansac7alumnes.crawlgeon.R;

import java.io.Serializable;

/**
 * Created by Sergio Plans on 19/04/2017.
 */

public class Monster implements Serializable {
    private int vida;
    private int daño;
    private int id;
    private int anim;
    public Monster(int vida, int daño, int id, int anim){
        this.vida = vida;
        this.daño = daño;
        this.id = id;
        this.anim = anim;
    }

    public int getVida(){ return vida; }

    public int getDaño() {
        return daño;
    }

    public int getID(){
        return id;
    }

    public int getAnim(){
        return anim;
    }
}

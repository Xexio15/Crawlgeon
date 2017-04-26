package com.example.splansac7alumnes.crawlgeon.monsters;

import java.io.Serializable;

/**
 * Created by Sergio Plans on 19/04/2017.
 */

public class Monster implements Serializable {
    private float vida;
    private float daño;
    private int id;
    private int anim;
    public Monster(float vida, float daño, int id, int anim){
        this.vida = vida;
        this.daño = daño;
        this.id = id;
        this.anim = anim;
    }

    public float getVida(){ return vida; }

    public float getDaño() {
        return daño;
    }

    public int getID(){
        return id;
    }

    public int getAnim(){
        return anim;
    }
}

package com.example.splansac7alumnes.crawlgeon.monsters;

import java.io.Serializable;

/**
 * Created by Sergio Plans on 19/04/2017.
 */

public class Monster implements Serializable {
    private float vida;
    private float daño;
    private int id;
    private int staticanim;
    private int attackanim;
    public Monster(float vida, float daño, int id, int staticanim,int attackanim){
        this.vida = vida;
        this.daño = daño;
        this.id = id;
        this.staticanim = staticanim;
        this.attackanim = attackanim;
    }

    public float getVida(){ return vida; }

    public float getDaño() {
        return daño;
    }

    public int getID(){
        return id;
    }

    public int getStaticAnim(){
        return staticanim;
    }

    public int getAttackAnim(){
        return attackanim;
    }
}

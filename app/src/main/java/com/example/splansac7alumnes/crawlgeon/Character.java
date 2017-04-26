package com.example.splansac7alumnes.crawlgeon;

import java.io.Serializable;

/**
 * Created by Sergio Plans on 20/04/2017.
 */

public class Character implements Serializable {
    private float vida = 100;
    private int daño;
    private int nivel;
    private int idSprite = R.drawable.heroe;
    private int staticAnim = R.drawable.staticpjanim;

    public Character (){

    }

    public int getDaño() {
        return daño;
    }

    public int getNivel() {
        return nivel;
    }

    public int getID() {
        return idSprite;
    }

    public float getVida() {
        return vida;
    }

    public int getStaticAnim(){
        return staticAnim;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setStaticAnim(int anim){
        this.staticAnim = anim;
    }





}

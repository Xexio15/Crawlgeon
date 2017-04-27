package com.example.splansac7alumnes.crawlgeon;

import java.io.Serializable;

/**
 * Created by Sergio Plans on 20/04/2017.
 */

public class Character implements Serializable {
    private float vida = 100;
    private int daño;
    private int nivel;
    private int xpActual;
    private int xpNecesaria;
    private int idSprite = R.drawable.heroe;
    private int staticAnim = R.drawable.staticpjanim;
    private float bonusAtaque = 0; //+0%


    public Character (){
        xpActual = 0;
        nivel = 1;
        xpNecesaria = 100;
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


    /**
     * Aumenta la experiencia
     * Si sube de nivel, lo sube i retorna true sino retorna false i no lo sube
     * @return
     */
    public boolean subirEXP(int xpGanada){
        xpActual = xpActual + xpGanada;
        if(xpActual >= xpNecesaria){
            subirNivel();
            if(xpActual > xpNecesaria){
                int xp = xpActual - xpNecesaria;
                xpActual = xp;
            }else {
                xpActual = 0;
            }
            xpNecesaria = Math.round(xpNecesaria * ((float) 1.25));
            return true;
        }else{
            return false;
        }


    }
    public void subirNivel(){
        nivel = nivel+1;
        bonusAtaque = (float)0.1*nivel;
        vida = Math.round((float)1.26*vida);
        if(nivel==3){
            vida = Math.round((float)1.26*5*vida);
        }
    }

    public float getBonusTile(){
        return bonusAtaque;
    }




}

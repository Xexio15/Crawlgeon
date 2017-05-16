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
    private int idSprite = R.drawable.staticpj1;
    private int staticAnim = R.drawable.staticpjanim;
    private int electricAnim = R.drawable.electricanim;
    private int iceAnim = R.drawable.iceanim;
    private int fireAnim = R.drawable.fireanim;
    private int arcaneAnim = R.drawable.arcaneanim;
    private int swordAnim = R.drawable.swordanim;
    private int shieldAnim = R.drawable.shieldanim;
    private int potionAnim = R.drawable.potionanim;
    private int fallAnim = R.drawable.pjfallanim;
    private float bonusAtaque = 0; //+0%
    private int painSound = R.raw.pain_character;
    private int deathSound = R.raw.death_character;


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

    public int getElectricAnim(){
        return electricAnim;
    }

    public int getIceAnim(){
        return iceAnim;
    }

    public int getFireAnim(){
        return fireAnim;
    }

    public int getArcaneAnim(){
        return arcaneAnim;
    }

    public int getBasicAnim(){
        return swordAnim;
    }

    public int getDefAnim(){
        return shieldAnim;
    }

    public int getHealAnim(){
        return potionAnim;
    }

    public int getFallAnim(){
        return fallAnim;
    }

    public int getPainSound() { return painSound; }

    public int getDeathSound() { return deathSound; }

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

    public void setElectricAnim(int anim){
        this.electricAnim = anim;
    }

    public void setIceAnim(int anim){
        this.iceAnim = anim;
    }

    public void setFireAnim(int anim){
        this.fireAnim = anim;
    }

    public void setArcaneAnim(int anim){
        this.arcaneAnim = anim;
    }

    public void setBasicAnim(int anim){
        this.swordAnim = anim;
    }

    public void setDefAnim(int anim){
        this.shieldAnim = anim;
    }

    public void setHealAnim(int anim){
        this.potionAnim = anim;
    }

    public void setFallAnim(int anim){
        this.fallAnim = anim;
    }


    /**
     * Aumenta la experiencia
     * Si sube de nivel, lo sube i retorna true sino retorna false i no lo sube
     * @return
     */
    public boolean subirEXP(int xpGanada){
        xpActual = xpActual + xpGanada;
        if(xpActual >= xpNecesaria){
            while(xpActual>=xpNecesaria) {
                subirNivel();
                if (xpActual > xpNecesaria) {
                    int xp = xpActual - xpNecesaria;
                    xpActual = xp;
                } else {
                    xpActual = 0;
                }
                xpNecesaria = Math.round(xpNecesaria * ((float) 1.25));
            }
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
            nivel=7;
            bonusAtaque = (float)0.1*nivel;
            vida = Math.round((float)1.26*1.26*1.26*1.26*vida);
        }
    }

    public float getBonusTile(){
        return bonusAtaque;
    }

    public int getXpNecesaria() { return xpNecesaria; }

    public int getXpActual() { return xpActual; }

}

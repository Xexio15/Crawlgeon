package com.example.splansac7alumnes.crawlgeon.monsters;

import java.io.Serializable;

/**
 * Created by Sergio Plans on 19/04/2017.
 */

public class Monster implements Serializable {
    private float vida;
    protected float daño;
    private int id;
    private int staticanim;
    protected int attackanim;
    private int attackSound;
    private int deathSound;
    private int painSound;
    private int attackArmorSound;
    private int fallAnim;
    private String vulnerabilidad;

    public Monster(float vida, float daño, int id, int staticanim,int attackanim,int fallAnim, int attackSound, int attackArmorSound, int painSound, int deathSound, String vulnerabilidad){
        this.vida = vida;
        this.daño = daño;
        this.id = id;
        this.staticanim = staticanim;
        this.attackanim = attackanim;
        this.fallAnim = fallAnim;
        this.attackSound = attackSound;
        this.deathSound = deathSound;
        this.painSound = painSound;
        this.attackArmorSound = attackArmorSound;
        this.vulnerabilidad = vulnerabilidad;
    }

    public int getDeathSound() {
        return deathSound;
    }

    public int getAttackSound() {
        return attackSound;
    }

    public int getAttackArmorSound() { return attackArmorSound; }

    public int getPainSound() { return painSound; }

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

    public int getFallAnim(){
        return fallAnim;
    }

    public String getVulnerabilidad(){
        return vulnerabilidad;
    }
}

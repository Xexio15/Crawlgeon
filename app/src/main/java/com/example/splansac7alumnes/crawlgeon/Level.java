package com.example.splansac7alumnes.crawlgeon;

import com.example.splansac7alumnes.crawlgeon.monsters.Monster;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sergio Plans on 22/04/2017.
 */

public class Level implements Serializable{
    private float[] probabilidadesIniciales = new float[7];
    private float[] probabilidades = new float[7];
    private Monster monstruo;
    private boolean bloqueado;
    private boolean isBoss;
    private int numNivel;
    private int numDungeon;
    private int turnosPJ;
    private int turnosEnemigo;
    private int puntuacion;

    public Level(float[] probabilidadesIniciales, float[] probabilidades, Monster monstruo, boolean bloqueado, boolean isBoss, int numNivel, int numDungeon, int turnosPJ, int turnosEnemigo, int puntuacion) {
        this.probabilidadesIniciales = probabilidadesIniciales;
        this.probabilidades = probabilidades;
        this.monstruo = monstruo;
        this.bloqueado = bloqueado;
        this.isBoss = isBoss;
        this.numNivel = numNivel;
        this.numDungeon = numDungeon;
        this.turnosPJ = turnosPJ;
        this.turnosEnemigo = turnosEnemigo;
        this.puntuacion = puntuacion;
    }


    public float[] getProbabilidadesIniciales() {
        return probabilidadesIniciales;
    }

    public float[] getProbabilidades() {
        return probabilidades;
    }

    public Monster getMonstruo(){
        return monstruo;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public int getPuntuacion(){
        return puntuacion;
    }

    public void setProbabilidadesIniciales(float[] probabilidadesIniciales) {
        this.probabilidadesIniciales = probabilidadesIniciales;
    }

    public void setProbabilidades(float[] probabilidades) {
        this.probabilidades = probabilidades;
    }

    public void setMonstruo(Monster monstruo) {
        this.monstruo = monstruo;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public void setDesbloqueado(){ bloqueado = false; };

    public int getTurnosPJ(){ return turnosPJ; };

    public int getTurnosMonstruo(){ return turnosEnemigo; };

    public int getVidaMonstruo(){ return monstruo.getVida(); }

    public int getDañoMonstruo(){ return monstruo.getDaño(); }

    public void setPuntuacion(int puntuacion){
        this.puntuacion = puntuacion;
    }
}

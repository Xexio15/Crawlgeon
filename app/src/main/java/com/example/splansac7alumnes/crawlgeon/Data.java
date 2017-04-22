package com.example.splansac7alumnes.crawlgeon;

import com.example.splansac7alumnes.crawlgeon.monsters.Monster;
import com.example.splansac7alumnes.crawlgeon.monsters.Rat;

import java.io.Serializable;


/**
 * Created by Sergio Plans on 21/04/2017.
 */

public class Data implements Serializable{
    private Character personaje;
    private float volumenMusica;
    private float volumenFX;
    //Niveles desbloqueados o por desbloquear

    public void setVolumenMusica(float volumenMusica) {
        this.volumenMusica = volumenMusica;
    }

    public void setPersonaje(Character personaje) {
        this.personaje = personaje;
    }

    public void setVolumenFX(float volumenFX) {
        this.volumenFX = volumenFX;
    }



    public Character getPersonaje() {
        if(personaje == null){
            this.personaje = new Character();
            return personaje;
        }
        return personaje;
    }

    public float getVolumenFX() {
        return volumenFX;
    }

    public float getVolumenMusica() {
        return volumenMusica;
    }



}

package com.example.splansac7alumnes.crawlgeon;

import com.example.splansac7alumnes.crawlgeon.monsters.Monster;
import com.example.splansac7alumnes.crawlgeon.monsters.Rat;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Sergio Plans on 21/04/2017.
 */

public class Data implements Serializable{
    private Character personaje;
    private float volumenMusica;
    private float volumenFX;
    private ArrayList<Level> niveles;//En caso de tener mas de una dungeon hacer una array de arrays
    //Niveles desbloqueados o por desbloquear

    public Data(){
        niveles = new ArrayList<>();
        float[] probsIn1 = {30,25,20,15,10,0,0};
        float[] probs1 = {20,20,20,20,20,0,0};
        Level lvl1 = new Level(probsIn1, probs1, new Rat(), false, false, 1, 1);
        niveles.add(lvl1);
    }

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

    public ArrayList<Level> getNiveles(){
        return  niveles;
    }

}

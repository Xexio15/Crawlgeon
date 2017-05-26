package com.example.splansac7alumnes.crawlgeon;

import com.example.splansac7alumnes.crawlgeon.monsters.Monster;
import com.example.splansac7alumnes.crawlgeon.monsters.Orc;
import com.example.splansac7alumnes.crawlgeon.monsters.Rat;
import com.example.splansac7alumnes.crawlgeon.monsters.SkeletonKing;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Sergio Plans on 21/04/2017.
 */

public class Data implements Serializable{
    private Character personaje;
    private float volumenMusica;
    private float volumenFX;
    private boolean tutorial;
    private ArrayList<Level> niveles;//En caso de tener mas de una dungeon hacer una array de arrays
    //Niveles desbloqueados o por desbloquear

    public Data(){
        niveles = new ArrayList<>();
        //Ataque Defensa Vida Fuego Hielo Rayo Arcano
        float[] probsIn1 = {30,25,20,15,10,0,0};
        float[] probsIn2 = {30,25,20,0,0,20,5};
        float[] probsIn3 = {20,25,20,0,15,0,20};
        //float[] probsInPruebas = {15,15,14,14,14,14,14};
        float[] probs1 = {20,20,20,20,20,0,0};
        float[] probs2 = {20,20,20,0,0,20,20};
        float[] probs3 = {20,20,20, 0,20,0,20};
        Level lvl1 = new Level(probsIn1, probs1, new Rat(), false, false, 1, 1, 1, 1, 0,15);
        Level lvl2 = new Level(probsIn2, probs2, new Orc(), true, false, 2, 1, 1, 1, 0,25);
        Level lvl3 = new Level(probsIn1, probs1, new Rat(), true, false, 3, 1, 1, 1, 0,35);
        Level lvl4 = new Level(probsIn1, probs1, new Rat(), true, false, 4, 1, 1, 1, 0,45);
        Level lvl5 = new Level(probsIn1, probs1, new Rat(), true, false, 5, 1, 1, 1, 0,55);
        Level lvl6 = new Level(probsIn1, probs1, new Rat(), true, false, 6, 1, 1, 1, 0,70);
        Level lvl7 = new Level(probsIn1, probs1, new Rat(), true, false, 7, 1, 1, 1, 0,80);
        Level lvl8 = new Level(probsIn1, probs1, new Rat(), true, false, 8, 1, 1 ,1, 0,90);
        Level lvl9 = new Level(probsIn1, probs1, new Rat(), true, false, 9, 1, 1, 1, 0,120);
        Level lvl10 = new Level(probsIn3, probs3, new SkeletonKing(), true, true, 10, 1, 1, 1, 0,200);
        niveles.add(lvl1);
        niveles.add(lvl2);
        niveles.add(lvl3);
        niveles.add(lvl4);
        niveles.add(lvl5);
        niveles.add(lvl6);
        niveles.add(lvl7);
        niveles.add(lvl8);
        niveles.add(lvl9);
        niveles.add(lvl10);
        tutorial = true;

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

    public void setTutorial(boolean tutorial){
        this.tutorial = tutorial;
    }

    public boolean getTutorial(){
        return this.tutorial;
    }



}

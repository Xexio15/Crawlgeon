package com.example.splansac7alumnes.crawlgeon.Tiles;

import android.widget.ImageView;

/**
 * Created by Sergio Plans on 11/04/2017.
 */

public class Tile {
    private float damage;

    private ImageView imatge;
    private String element;
    private int drawable;
    private int fxID;
    public Tile(int drawable, float damage, int fxID){
        this.drawable = drawable;
        this.damage = damage;
        this.fxID = fxID;

    }
    public void setImatge(ImageView imatge){
        this.imatge = imatge;
        this.imatge.setTag(drawable);//Posem una etiqueta que sera la ID del arxiu png per posteriorment poder comparar imatges
    }
    public void setElement(String element){
        this.element = element;
    }

    public ImageView getImatge(){
        imatge.setMinimumWidth(50);
        imatge.setMaxWidth(50);
        imatge.setMinimumHeight(50);
        imatge.setMaxHeight(50);
        return imatge;
    }

    public String getElement(){
        return element;
    }

    public int getDrawableID(){
        return drawable;
    }

    public float getDamage(){
        return damage;
    }

    public int getFxID() {
        return fxID;
    }

    public void setFxID(int fxID) {
        this.fxID = fxID;
    }
}

package com.example.splansac7alumnes.crawlgeon.Tiles;

import android.widget.ImageView;

/**
 * Created by Sergio Plans on 11/04/2017.
 */

public class Tile {
    private int damage;

    private ImageView imatge;
    private String element;
    private int drawable;
    public Tile(int drawable, int damage){
        this.drawable = drawable;
        this.damage = damage;

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

    public int getDamage(){
        return damage;
    }
}

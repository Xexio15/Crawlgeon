package com.example.splansac7alumnes.crawlgeon.Tiles;

import android.content.Context;
import android.widget.ImageView;

import com.example.splansac7alumnes.crawlgeon.R;

/**
 * Created by Sergio Plans on 11/04/2017.
 */

public class Arcane extends Tile{

    public Arcane(Context context){
        super(R.drawable.elementoarcano,7, R.raw.arcane2);
        ImageView arcane = new ImageView(context);
        arcane.setImageResource(R.drawable.elementoarcano);
        super.setImatge(arcane);
        super.setElement("Arcane");
    }

}

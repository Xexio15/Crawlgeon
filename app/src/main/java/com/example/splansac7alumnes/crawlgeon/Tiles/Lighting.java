package com.example.splansac7alumnes.crawlgeon.Tiles;

import android.content.Context;
import android.widget.ImageView;

import com.example.splansac7alumnes.crawlgeon.R;

/**
 * Created by Sergio Plans on 11/04/2017.
 */

public class Lighting extends Tile{

    public Lighting(Context context){
        super(R.drawable.elementorayo4,5,0);
        ImageView light = new ImageView(context);
        light.setImageResource(R.drawable.elementorayo4);
        super.setImatge(light);
        super.setElement("Lighting");
    }
}

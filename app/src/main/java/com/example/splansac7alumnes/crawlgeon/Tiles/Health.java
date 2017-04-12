package com.example.splansac7alumnes.crawlgeon.Tiles;

import android.content.Context;
import android.widget.ImageView;

import com.example.splansac7alumnes.crawlgeon.R;

/**
 * Created by Sergio Plans on 11/04/2017.
 */

public class Health extends Tile{
    public Health(Context context){
        super(R.drawable.health_potion3);
        ImageView health = new ImageView(context);
        health.setImageResource(R.drawable.health_potion3);
        super.setImatge(health);
        super.setElement("Health");
    }
}

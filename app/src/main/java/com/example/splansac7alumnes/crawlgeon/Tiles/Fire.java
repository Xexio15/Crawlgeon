package com.example.splansac7alumnes.crawlgeon.Tiles;

import android.content.Context;
import android.widget.ImageView;

import com.example.splansac7alumnes.crawlgeon.R;

/**
 * Created by Sergio Plans on 11/04/2017.
 */

public class Fire extends Tile{
    public Fire(Context context){
        super(R.drawable.elementofuego4);
        ImageView fire = new ImageView(context);
        fire.setImageResource(R.drawable.elementofuego4);
        super.setImatge(fire);
        super.setElement("Fire");
    }
}

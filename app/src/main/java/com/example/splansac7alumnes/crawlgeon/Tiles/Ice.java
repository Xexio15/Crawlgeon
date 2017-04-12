package com.example.splansac7alumnes.crawlgeon.Tiles;

import android.content.Context;
import android.widget.ImageView;

import com.example.splansac7alumnes.crawlgeon.R;

/**
 * Created by Sergio Plans on 11/04/2017.
 */

public class Ice extends Tile{
    public Ice(Context context){
        super(R.drawable.elementohieo3);
        ImageView ice = new ImageView(context);
        ice.setImageResource(R.drawable.elementohieo3);
        super.setImatge(ice);
        super.setElement("Ice");
    }
}

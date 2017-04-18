package com.example.splansac7alumnes.crawlgeon.Tiles;

import android.content.Context;
import android.widget.ImageView;

import com.example.splansac7alumnes.crawlgeon.R;

/**
 * Created by Sergio Plans on 11/04/2017.
 */

public class Shield extends Tile{

    public Shield(Context context){
        super(R.drawable.defensa2,2);
        ImageView shield = new ImageView(context);
        shield.setImageResource(R.drawable.defensa2);
        super.setImatge(shield);
        super.setElement("Defense");
    }
}

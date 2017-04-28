package com.example.splansac7alumnes.crawlgeon.Tiles;

import android.content.Context;
import android.widget.ImageView;

import com.example.splansac7alumnes.crawlgeon.R;

/**
 * Created by Sergio Plans on 11/04/2017.
 */

public class Basic extends Tile{

    public Basic(Context context){
        super(R.drawable.ataque_basico_2,1,R.raw.basic);
        if(context == null){
            System.out.print("es nulo");
        }
        ImageView basic = new ImageView(context);
        basic.setImageResource(R.drawable.ataque_basico_2);
        super.setImatge(basic);
        super.setElement("Basic");
    }
}

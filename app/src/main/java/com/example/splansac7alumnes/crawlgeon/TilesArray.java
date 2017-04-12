package com.example.splansac7alumnes.crawlgeon;

import android.content.Context;

import com.example.splansac7alumnes.crawlgeon.Tiles.Arcane;
import com.example.splansac7alumnes.crawlgeon.Tiles.Basic;
import com.example.splansac7alumnes.crawlgeon.Tiles.Fire;
import com.example.splansac7alumnes.crawlgeon.Tiles.Health;
import com.example.splansac7alumnes.crawlgeon.Tiles.Ice;
import com.example.splansac7alumnes.crawlgeon.Tiles.Lighting;
import com.example.splansac7alumnes.crawlgeon.Tiles.Shield;
import com.example.splansac7alumnes.crawlgeon.Tiles.Tile;

import java.util.ArrayList;

/**
 * Created by Sergio Plans on 11/04/2017.
 */

public class TilesArray {
    private ArrayList<Tile> tiles;
    private Context context;
    public TilesArray(Context context){
        this.context=context;
        tiles = new ArrayList(7);
        Tile basic = new Basic(context);
        Tile shield = new Shield(context);
        Tile fire = new Fire(context);
        Tile arcane = new Arcane(context);
        Tile ice = new Ice(context);
        Tile lighting = new Lighting(context);
        Tile health = new Health(context);


        tiles.add(0,  basic);
        tiles.add(1,  shield);
        tiles.add(2,  fire);
        tiles.add(3,  arcane);
        tiles.add(4,  ice);
        tiles.add(5,  lighting);
        tiles.add(6,  health);
    }

    public ArrayList<Tile> getArray(){
        return tiles;
    }
}

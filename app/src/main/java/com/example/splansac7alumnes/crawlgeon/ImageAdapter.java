package com.example.splansac7alumnes.crawlgeon;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.splansac7alumnes.crawlgeon.Tiles.Tile;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sergio Plans on 12/04/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private TilesArray tiles;
    public ImageAdapter(Context c, TilesArray tiles) {

        mContext = c;
        this.tiles = tiles;
        fill();
    }

    public int getCount() {
        return listaIdsImagenes.length;
    }

    public Object getItem(int position) {
        ImageView imatge = new ImageView(mContext);
        imatge.setTag(listaIdsImagenes[position]);
        imatge.setImageResource(listaIdsImagenes[position]);
        return imatge;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(75, 75));
            imageView.setPadding(0,0,0,0);

        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setTag(listaIdsImagenes[position]); //Posem la etiqueta que coincidira amb la de la classe Tile
        imageView.setImageResource(listaIdsImagenes[position]);
        return imageView;
    }

    // references to our images
   private int[] listaIdsImagenes = new int[49];
    public void fill(){
        for(int x = 0; x <= 48; x++){

            ArrayList<Tile> seleccion = tiles.getArray();
            Random num = new Random();
            int numero = num.nextInt(6-0+1)+0;
            listaIdsImagenes[x] = seleccion.get(numero).getDrawableID();

        }
    }

}
package com.example.splansac7alumnes.crawlgeon;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.splansac7alumnes.crawlgeon.Tiles.Tile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
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
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
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

    public void removeItem(int position){
        listaIdsImagenes[position] = 0;
        notifyDataSetChanged();
    }

    public void removeItems(ArrayList<Integer> seleccion){
        Iterator<Integer> iterator = seleccion.iterator();
        while(iterator.hasNext()){
            this.removeItem(iterator.next());
        }
    }

    public void realizarHechizo(ArrayList<Integer> seleccion){//En proceso
        this.removeItems(seleccion);
        /*for(int i=48; i>=42; i++){
            int inicial=i;
            while(i>inicial-42) {
                if (listaIdsImagenes[i] == 0) {
                    for (int l = i - 7; l >= 0; l -= 7) {
                        if (l >= 0) {
                            if (listaIdsImagenes[l] != 0) {
                                listaIdsImagenes[i] = listaIdsImagenes[l];
                                listaIdsImagenes[l] = 0;
                            }
                        }
                    }
                } else {
                    i -= 7;
                }
            }
        }*/
        for (int x = 42; x <= 48; x++) {
            int i = x;
            while (i > 0) {
                if (listaIdsImagenes[i] == 0) {
                    int l = i - 7;
                    boolean acabado = false;
                    while (l >= 0 && !acabado) {
                        if (listaIdsImagenes[l] != 0) {
                            listaIdsImagenes[i] = listaIdsImagenes[l];
                            listaIdsImagenes[l] = 0;
                            acabado = true;
                        } else {
                            l -= 7;
                        }
                    }
                }
                i -= 7;

            }

        }

        for (int i = 0; i < 49; i++){
            Random num = new Random();
            if (listaIdsImagenes[i] == 0){
                int numero = num.nextInt(6-0+1)+0;
                listaIdsImagenes[i] = tiles.getArray().get(numero).getDrawableID();
            }
        }

        /*int aux = listaIdsImagenes[0];
        listaIdsImagenes[0]=listaIdsImagenes[1];
        listaIdsImagenes[1]=aux;*/

        notifyDataSetChanged();



    }

}
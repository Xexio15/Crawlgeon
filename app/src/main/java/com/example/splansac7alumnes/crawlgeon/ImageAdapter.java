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
import java.util.Collection;
import java.util.Collections;
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
        //fill();
        rellenarTablero(20,20,30,5,8,7,10);
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
    /*private int[] listaIdsImagenes = new int[49];
    public void fill(){
        for(int x = 0; x <= 48; x++){
            ArrayList<Tile> seleccion = tiles.getArray();
            Random num = new Random();
            int numero = num.nextInt(6-0+1)+0;
            listaIdsImagenes[x] = seleccion.get(numero).getDrawableID();


        }
    }*/

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
        /*int i=42;
        while(i>0) {
            if (listaIdsImagenes[i] == 0) {
                int l=i-7;
                boolean acabado=false;
                while (l>=0 && !acabado){
                    if (listaIdsImagenes[l] != 0) {
                        listaIdsImagenes[i] = listaIdsImagenes[l];
                        listaIdsImagenes[l] = 0;
                        acabado=true;
                    }else{
                        l=-7;
                    }
                }
            }
            i -= 7;

        }*/

        /*int aux = listaIdsImagenes[0];
        listaIdsImagenes[0]=listaIdsImagenes[1];
        listaIdsImagenes[1]=aux;*/
        notifyDataSetChanged();


    }

    private int[] listaIdsImagenes = new int[49];
    public void rellenarTablero(int probVida, int probDefensa, int probAtaque, int probFuego, int probArcano, int probRayo, int probHielo){
        int numVida = 0;
        int numDefensa = 0;
        int numAtaque = 0;
        int numFuego = 0;
        int numArcano = 0;
        int numRayo = 0;
        int numHielo = 0;
        int x = 0;
        ArrayList<Tile> seleccion = tiles.getArray();
        ArrayList<Integer> array = new ArrayList<>();

        if( probVida != 0){
            numVida = (int)(49*probVida)/100;
            for(int i = 0; i <= numVida; i++){
                array.add(seleccion.get(6).getDrawableID());
                x+=1;
            }
        }
        if(probDefensa != 0){
            numDefensa = (int)(49*probDefensa)/100;
            for(int i = 0; i <= numDefensa; i++){
                array.add(seleccion.get(1).getDrawableID());
                x+=1;
            }
        }
        if( probAtaque != 0){
            numAtaque = (int)(49*probAtaque)/100;
            for(int i = 0; i <= numAtaque; i++){
                array.add(seleccion.get(0).getDrawableID());
                x+=1;
            }
        }
        if(probFuego != 0){
            numFuego = (int)(49*probFuego)/100;
            for(int i = 0; i <= numFuego; i++){
                array.add(seleccion.get(2).getDrawableID());
                x+=1;
            }
        }
        if( probArcano != 0){
            numArcano = (int)(49*probArcano)/100;
            for(int i = 0; i <= numArcano; i++){
                array.add(seleccion.get(3).getDrawableID());
                x+=1;
            }
        }
        if(probRayo != 0){
            numRayo = (int)(49*probRayo)/100;
            for(int i = 0; i <= numRayo; i++){
                array.add(seleccion.get(5).getDrawableID());
                x+=1;
            }
        }
        if( probHielo != 0){
            numHielo = 49 - (numArcano+numAtaque+numDefensa+numFuego+numVida+numRayo);
            for(int i = 0; i <= numHielo; i++){
                array.add(seleccion.get(4).getDrawableID());
                x+=1;
            }
        }
        Random random = new Random();
        Collections.shuffle(array,random);
        Iterator<Integer> iterator = array.iterator();
        for (int n=0; n<49; n++){
            listaIdsImagenes[n] = iterator.next();
        }






    }

}
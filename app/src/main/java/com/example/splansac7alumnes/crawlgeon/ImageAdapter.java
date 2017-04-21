package com.example.splansac7alumnes.crawlgeon;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.splansac7alumnes.crawlgeon.Tiles.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Sergio Plans on 12/04/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private TilesArray tiles;
    private int[] listaIdsImagenes = new int[49];
    private int numVida = 0;
    private int numDefensa = 0;
    private int numAtaque = 0;
    private int numFuego = 0;
    private int numArcano = 0;
    private int numRayo = 0;
    private int numHielo = 0;
    private ViewGroup grupo;
    private ImageView anim;
    public ImageAdapter(Context c, TilesArray tiles, ImageView anim) {
        mContext = c;
        this.tiles = tiles;
        this.anim = anim;
        //fill();
        rellenarTablero(20,20,30,5,8,7,10);//Probabilidades
    }

    public int getCount() {
        return listaIdsImagenes.length;
    }

    public Object getItem(int position) {
        /*ImageView imatge = new ImageView(mContext);
        imatge.setTag(listaIdsImagenes[position]);
        imatge.setImageResource(listaIdsImagenes[position]);
        return imatge;*/

        return grupo.getChildAt(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        grupo = parent;//Nose sirve para poder coger un item de la tabla en getItem
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

    public void changeItem(int position, int res){
        listaIdsImagenes[position] = res;
        notifyDataSetChanged();
    }
    public void removeItem(int position){
        /*
         * Animacion de expandir
         */
        //ImageView im = ((ImageView)grupo.getChildAt(position));
        anim.setImageResource(listaIdsImagenes[position]);
        anim.bringToFront();
        Animation lanz = AnimationUtils.loadAnimation(mContext,R.anim.lanzar_hechizo);
        lanz.reset();
        anim.startAnimation(lanz);
        /*
         *
         */

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

        /*
         * Bucle para rellenar las Tiles eliminadas
         */
        for (int i = 0; i < 49; i++){
            Random num = new Random();
            if (listaIdsImagenes[i] == 0){
                int numero = num.nextInt(6-0+1)+0;
                listaIdsImagenes[i] = tiles.getArray().get(numero).getDrawableID();

               /*
                * Animacion de expandir
                */
                Animation trans = AnimationUtils.loadAnimation(mContext,R.anim.aparece);
                trans.reset();
                grupo.getChildAt(i).startAnimation(trans);
                /*
                 *
                 */
            }
        }
        //rellenarVacios();

        /*int aux = listaIdsImagenes[0];
        listaIdsImagenes[0]=listaIdsImagenes[1];
        listaIdsImagenes[1]=aux;*/

        //Notificamos los cambios
        notifyDataSetChanged();



    }

    public void rellenarVacios(){
        //Not that way
        int nVida = 0;
        int nDefensa = 0;
        int nAtaque = 0;
        int nFuego = 0;
        int nArcano = 0;
        int nRayo = 0;
        int nHielo = 0;
        for( int i = 0; i < 49; i++){
            if(listaIdsImagenes[i] == this.tiles.getArray().get(0).getDrawableID()){
                nAtaque++;
            }
            else if(listaIdsImagenes[i] == tiles.getArray().get(1).getDrawableID()){
                nDefensa++;
            }
            else if( listaIdsImagenes[i] == tiles.getArray().get(2).getDrawableID()){
                nFuego++;
            }
            else if(listaIdsImagenes[i] == tiles.getArray().get(3).getDrawableID()){
                nArcano++;
            }
            else if(listaIdsImagenes[i] == tiles.getArray().get(4).getDrawableID()){
                nHielo++;
            }
            else if(listaIdsImagenes[i] == tiles.getArray().get(5).getDrawableID()){
                nRayo++;
            }
            else if(listaIdsImagenes[i] == tiles.getArray().get(6).getDrawableID()){
                nVida++;
            }
        }
        for (int i = 0; i < 49; i++){
            if (listaIdsImagenes[i] == 0){
                if(nAtaque < numAtaque) {
                    listaIdsImagenes[i] = tiles.getArray().get(0).getDrawableID();
                }
                else if(nDefensa < numDefensa){
                    listaIdsImagenes[i] = tiles.getArray().get(1).getDrawableID();
                }
                else if(nFuego < numFuego){
                    listaIdsImagenes[i] = tiles.getArray().get(2).getDrawableID();
                }
                else if(nArcano < numArcano){
                    listaIdsImagenes[i] = tiles.getArray().get(3).getDrawableID();
                }
                else if(nHielo < numHielo){
                    listaIdsImagenes[i] = tiles.getArray().get(4).getDrawableID();
                }
                else if(nRayo < numRayo){
                    listaIdsImagenes[i] = tiles.getArray().get(5).getDrawableID();
                }
                else if(nVida < numVida){
                    listaIdsImagenes[i] = tiles.getArray().get(6).getDrawableID();
                }
            }
        }
    }

    public void seleccionarTile(int posicion){
        ImageView im = (ImageView) this.getItem(posicion);
        im.setAlpha(0.5f);
    }

    public void deseleccionarTile(ArrayList<Integer> seleccion){
        int i = 0;
        while(i < seleccion.size()){
            ImageView im = (ImageView) this.getItem(seleccion.get(i));
            im.setAlpha(1.0f);
            i++;
        }
    }

    public void rellenarTablero(int probVida, int probDefensa, int probAtaque, int probFuego, int probArcano, int probRayo, int probHielo){

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
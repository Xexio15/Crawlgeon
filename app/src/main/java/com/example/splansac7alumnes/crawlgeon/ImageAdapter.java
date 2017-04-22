package com.example.splansac7alumnes.crawlgeon;

import android.content.Context;
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
    private int probVidaInicial = 0;
    private int probDefensaInicial = 0;
    private int probAtaqueInicial = 0;
    private int probFuegoInicial = 0;
    private int probArcanoInicial = 0;
    private int probRayoInicial = 0;
    private int probHieloInicial = 0;
    private int probVida = 0;
    private int probDefensa = 0;
    private int probAtaque = 0;
    private int probFuego = 0;
    private int probArcano = 0;
    private int probRayo = 0;
    private int probHielo = 0;
    private ViewGroup grupo;
    private ImageView anim;
    private int rellenar = 2;
    public ImageAdapter(Context c, TilesArray tiles, ImageView anim) {
        mContext = c;
        this.tiles = tiles;
        this.anim = anim;
        //Ataque Defensa Vida Fuego Hielo Rayo Arcano
        setProbabilidadesIniciales(30,25,20,15,10,0,0);
        setProbabilidades(20,20,20,20,20,0,0);
        rellenarTablero();
    }

    public int getCount() {
        return listaIdsImagenes.length;
    }

    /**
     * Devuelve el item de la posicion
     */
    public Object getItem(int position) {
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

    /**
     * Cambia el item de la posicion por el recurso pasado
     */
    public void changeItem(int position, int res){
        listaIdsImagenes[position] = res;
        notifyDataSetChanged();
    }

    /**
     * Elimina el item de la posicion
     */
    public void removeItem(int position){
        /*
         * Animacion de expandir
         */
        anim.setImageResource(listaIdsImagenes[position]);
        anim.bringToFront();
        Animation lanz = AnimationUtils.loadAnimation(mContext,R.anim.lanzar_hechizo);
        lanz.reset();
        anim.startAnimation(lanz);


        listaIdsImagenes[position] = 0;
        notifyDataSetChanged();
    }

    /**
     * Elimina los items del Array pasado
     */
    public void removeItems(ArrayList<Integer> seleccion){
        Iterator<Integer> iterator = seleccion.iterator();

        while(iterator.hasNext()){
            this.removeItem(iterator.next());
        }
    }

    /**
     * Elimina los items del array i mueve las tiles necesarias
     */
    public void realizarHechizo(ArrayList<Integer> seleccion){//En proceso
        this.removeItems(seleccion);

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
       /* for (int i = 0; i < 49; i++){
            Random num = new Random();
            if (listaIdsImagenes[i] == 0){
                int numero = num.nextInt(6-0+1)+0;
                listaIdsImagenes[i] = tiles.getArray().get(numero).getDrawableID();

               /*
                * Animacion de expandir
                */
               /* Animation trans = AnimationUtils.loadAnimation(mContext,R.anim.aparece);
                trans.reset();
                grupo.getChildAt(i).startAnimation(trans);
                /*
                 *
                 */
           /* }
        }*/
        rellenarVacios();

        /*int aux = listaIdsImagenes[0];
        listaIdsImagenes[0]=listaIdsImagenes[1];
        listaIdsImagenes[1]=aux;*/

        //Notificamos los cambios
        notifyDataSetChanged();



    }

    /**
     * Pone en modo seleccion la tile
     */
    public void seleccionarTile(int posicion){
        ImageView im = (ImageView) this.getItem(posicion);
        im.setAlpha(0.5f);
    }

    /**
     * Quita el modo seleccion de la lista de tiles
     */
    public void deseleccionarTile(ArrayList<Integer> seleccion){
        int i = 0;
        while(i < seleccion.size()){
            ImageView im = (ImageView) this.getItem(seleccion.get(i));
            im.setAlpha(1.0f);
            i++;
        }
    }

    /**
     * Rellena los huecos del tablero después de realizar un hechizo
     */
    private void rellenarVacios(){
        //Not that way
        int nVida = 0;
        int nDefensa = 0;
        int nAtaque = 0;
        int nFuego = 0;
        int nArcano = 0;
        int nRayo = 0;
        int nHielo = 0;
        /*
        * Cuenta el número de tiles que hay de cada tipo
        */
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
            else if(listaIdsImagenes[i] == tiles.getArray().get(6).getDrawableID()) {
                nVida++;
            }
        }

        int total = nAtaque+nDefensa+nFuego+nArcano+nHielo+nRayo+nVida; //numero de tiles que hay en el tablero

        Iterator<Integer> iterator = null;
        if(rellenar == 2){
            iterator = rellenarAux(probAtaque, probDefensa, probVida, probFuego, probHielo, probRayo, probArcano, 49-total).iterator();
            rellenar = 0;
        }
        else{
            /*Multiplicamos por dos, así es aleatorio (conservando las probabilidades), ya que el array tendra el doble de tiles necesitaads,
            * se reordenaran de forma aleatoria y se usaran solo la mitad*/
            int nAt=(probAtaqueInicial -nAtaque)*2;
            int nD=(probDefensaInicial -nDefensa)*2;
            int nV=(probVidaInicial -nVida)*2;
            int nF=(probFuegoInicial -nFuego)*2;
            int nH=(probHieloInicial -nHielo)*2;
            int nR=(probRayoInicial -nRayo)*2;
            int nAr=(probArcanoInicial -nArcano)*2;
            iterator = rellenarAux(nAt, nD, nV, nF, nH, nR, nAr, 49-total).iterator();
            rellenar++;
        }
        for (int i = 0; i < 49; i++) {
            if (listaIdsImagenes[i] == 0) {
                listaIdsImagenes[i]=iterator.next();
            }
        }
    }

    /**
     * Rellena el tablero en base a unas probabilidades
     */
    private void rellenarTablero(){
        Iterator<Integer> iterator = rellenarAux(probAtaqueInicial, probDefensaInicial, probVidaInicial, probFuegoInicial, probHieloInicial, probRayoInicial, probArcanoInicial, 49).iterator();
        for (int n=0; n<49; n++){
            listaIdsImagenes[n] = iterator.next();
        }
    }

    /**
     * Rellena el tablero
     *
     * @param numAtaque
     * @param numDefensa
     * @param numVida
     * @param numFuego
     * @param numHielo
     * @param numRayo
     * @param numArcano
     * @param total
     * @return
     */
    private ArrayList<Integer> rellenarAux(int numAtaque, int numDefensa, int numVida, int numFuego, int  numHielo, int numRayo, int numArcano, int total){
        int x = 0;
        ArrayList<Tile> arrayTiles = tiles.getArray();
        ArrayList<Integer> array = new ArrayList<>();

        for(int i = 0; i < numVida; i++){
            array.add(arrayTiles.get(6).getDrawableID());
            x+=1;
        }
        for(int i = 0; i < numDefensa; i++){
            array.add(arrayTiles.get(1).getDrawableID());
            x+=1;
        }
        for(int i = 0; i < numAtaque; i++){
            array.add(arrayTiles.get(0).getDrawableID());
            x += 1;
        }
        for(int i = 0; i < numFuego; i++){
            array.add(arrayTiles.get(2).getDrawableID());
            x+=1;
        }
        for(int i = 0; i < numArcano; i++){
            array.add(arrayTiles.get(3).getDrawableID());
            x+=1;
        }
        for(int i = 0; i < numRayo; i++){
            array.add(arrayTiles.get(5).getDrawableID());
            x+=1;
        }
        for(int i = 0; i < numHielo; i++){
            array.add(arrayTiles.get(4).getDrawableID());
            x+=1;
        }
        Random random = new Random();
        Collections.shuffle(array,random);
        return array;
    }

    /**
     * Metodo para fijar las probabilidades del relleno inicial
     * @param probAtaque
     * @param probDefensa
     * @param probVida
     * @param probFuego
     * @param probHielo
     * @param probRayo
     * @param probArcano
     */
    public void setProbabilidadesIniciales(int probAtaque, int probDefensa, int probVida, int probFuego, int probHielo, int probRayo, int probArcano){
        this.probAtaqueInicial =probAtaque;
        this.probDefensaInicial =probDefensa;
        this.probVidaInicial =probVida;
        this.probFuegoInicial =probFuego;
        this.probHieloInicial =probHielo;
        this.probRayoInicial =probRayo;
        this.probArcanoInicial =probArcano;
    }

    /**
     * Metodo para fijar las probabilidades del relleno de las fichas eliminadas
     * @param probAtaque
     * @param probDefensa
     * @param probVida
     * @param probFuego
     * @param probHielo
     * @param probRayo
     * @param probArcano
     */
    public void setProbabilidades(int probAtaque, int probDefensa, int probVida, int probFuego, int probHielo, int probRayo, int probArcano){
        this.probAtaque =probAtaque;
        this.probDefensa =probDefensa;
        this.probVida =probVida;
        this.probFuego =probFuego;
        this.probHielo =probHielo;
        this.probRayo =probRayo;
        this.probArcano =probArcano;
    }

}
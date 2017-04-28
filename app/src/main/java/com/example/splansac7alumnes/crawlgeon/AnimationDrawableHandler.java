package com.example.splansac7alumnes.crawlgeon;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;

/**
 * Created by Sergio Plans on 28/04/2017.
 */

public abstract class AnimationDrawableHandler extends AnimationDrawable {

    /** Handles the animation callback. */
    Handler mAnimationHandler;

    public AnimationDrawableHandler(AnimationDrawable aniDrawable) {
        /* Añadimos cada frame a la animacion */
        for (int i = 0; i < aniDrawable.getNumberOfFrames(); i++) {
            this.addFrame(aniDrawable.getFrame(i), aniDrawable.getDuration(i));
        }
    }

    @Override
    public void start() {
        super.start();
        /*
         * Primer llamamos al start de la clase padre ya que estamos haciendo Override
         * Añadimos un Handler para llamar onAnimationFinish cuando el tiempo de la animacion haya pasado
         */
        mAnimationHandler = new Handler();
        mAnimationHandler.postDelayed(new Runnable() {

            public void run() {
                onAnimationFinish();
            }
        }, getTotalDuration());

    }

    /**
     * Retorna la duracion total de la animacion
     *
     * @return The total duration.
     */
    public int getTotalDuration() {

        int iDuration = 0;

        for (int i = 0; i < this.getNumberOfFrames(); i++) {
            iDuration += this.getDuration(i);
        }

        return iDuration;
    }

    /**
     * Llamamos cuando termina la animacion (Se implementa como inner class en GameScreen)
     */
    abstract void onAnimationFinish();
}
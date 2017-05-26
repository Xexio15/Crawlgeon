package com.example.splansac7alumnes.crawlgeon;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jseguini12.alumnes on 26/05/17.
 */

public class Tutorial extends Dialog {
    private Context context;
    private Controller controlador;
    public Tutorial(Context context, int themeResId, Controller controlador) {
        super(context, themeResId);
        this.context = context;
        this.controlador = controlador;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Treiem el titol
        this.getWindow().setBackgroundDrawableResource(R.drawable.options_dialog_bg);//Posem el fons que volem
        this.setContentView(R.layout.tutorial_layout);//Posem el layout
        this.setCanceledOnTouchOutside(false);//Si clickem fora es tanca(Diria que no funciona be)
        this.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//Amb WrapContent evitem que es posi
        // la imatge a tota la pantalla, proba a canviar per MATCH_PARENT i veus que es posa a pantalla completa
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//Activem un fons semitransparent sota del dialog

        ((TextView) findViewById(R.id.textTutorialTitle)).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textoBarra)).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textTutorial1)).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textTutorial2)).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textTutorial3)).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/PixelFont.ttf"));
        ((TextView) findViewById(R.id.textTutorial4)).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/PixelFont.ttf"));

        Button next1 = (Button) findViewById(R.id.btnNext1);
        Button next2 = (Button) findViewById(R.id.btnNext2);
        Button next3 = (Button) findViewById(R.id.btnNext3);
        Button back2 = (Button) findViewById(R.id.btnBack2);
        Button back3 = (Button) findViewById(R.id.btnBack3);
        Button back4 = (Button) findViewById(R.id.btnBack4);
        Button play = (Button) findViewById(R.id.btntPlay);

        final ConstraintLayout t1 = (ConstraintLayout) findViewById(R.id.tutorial1);
        final ConstraintLayout t2 = (ConstraintLayout) findViewById(R.id.tutorial2);
        final ConstraintLayout t3 = (ConstraintLayout) findViewById(R.id.tutorial3);
        final ConstraintLayout t4 = (ConstraintLayout) findViewById(R.id.tutorial4);

        t1.setVisibility(View.VISIBLE);
        t1.setEnabled(true);
        t2.setVisibility(View.INVISIBLE);
        t2.setEnabled(false);
        t3.setVisibility(View.INVISIBLE);
        t3.setEnabled(false);
        t4.setVisibility(View.INVISIBLE);
        t4.setEnabled(false);

        ImageView hero = (ImageView) findViewById(R.id.imageHero);

        AnimationDrawable anim = (AnimationDrawable)context.getDrawable(R.drawable.tutorialanim);
        hero.setBackgroundResource(R.drawable.tutorialanim);
        AnimationDrawableHandler cad = new AnimationDrawableHandler(anim) {
            //cuando la animacion termine se ejecutara esto
            @Override
            void onAnimationFinish() {
            }
        };
        hero.setBackgroundDrawable(cad);
        //iniciamos la animacion
        cad.start();

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.playButtonSound(context);
                t1.setVisibility(View.INVISIBLE);
                t1.setEnabled(false);
                t2.setVisibility(View.VISIBLE);
                t2.setEnabled(true);
                t3.setVisibility(View.INVISIBLE);
                t3.setEnabled(false);
                t4.setVisibility(View.INVISIBLE);
                t4.setEnabled(false);
            }
        });

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.playButtonSound(context);
                t1.setVisibility(View.INVISIBLE);
                t1.setEnabled(false);
                t2.setVisibility(View.INVISIBLE);
                t2.setEnabled(false);
                t3.setVisibility(View.VISIBLE);
                t3.setEnabled(true);
                t4.setVisibility(View.INVISIBLE);
                t4.setEnabled(false);
            }
        });

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.playButtonSound(context);
                t1.setVisibility(View.INVISIBLE);
                t1.setEnabled(false);
                t2.setVisibility(View.INVISIBLE);
                t2.setEnabled(false);
                t3.setVisibility(View.INVISIBLE);
                t3.setEnabled(false);
                t4.setVisibility(View.VISIBLE);
                t4.setEnabled(true);
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.playButtonSound(context);
                t1.setVisibility(View.VISIBLE);
                t1.setEnabled(true);
                t2.setVisibility(View.INVISIBLE);
                t2.setEnabled(false);
                t3.setVisibility(View.INVISIBLE);
                t3.setEnabled(false);
                t4.setVisibility(View.INVISIBLE);
                t4.setEnabled(false);
            }
        });

        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.playButtonSound(context);
                t1.setVisibility(View.INVISIBLE);
                t1.setEnabled(false);
                t2.setVisibility(View.VISIBLE);
                t2.setEnabled(true);
                t3.setVisibility(View.INVISIBLE);
                t3.setEnabled(false);
                t4.setVisibility(View.INVISIBLE);
                t4.setEnabled(false);
            }
        });

        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.playButtonSound(context);
                t1.setVisibility(View.INVISIBLE);
                t1.setEnabled(false);
                t2.setVisibility(View.INVISIBLE);
                t2.setEnabled(false);
                t3.setVisibility(View.VISIBLE);
                t3.setEnabled(true);
                t4.setVisibility(View.INVISIBLE);
                t4.setEnabled(false);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.playButtonSound(context);
                controlador.saveData();
                dismiss();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        return false;
    }
}

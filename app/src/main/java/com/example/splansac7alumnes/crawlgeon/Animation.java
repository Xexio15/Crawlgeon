package com.example.splansac7alumnes.crawlgeon;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

/**
 * Created by Sergio Plans on 25/04/2017.
 */

public class Animation implements Runnable {

    private ImageView imageView;
    public Animation(ImageView imageView){
        this.imageView = imageView;
    }


    @Override
    public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        imageView.setBackgroundResource(R.drawable.static_orc_anim);
        imageView.post(new Runnable() {
            public void run() {
                AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
                anim.start();
            }
        });
    }
}

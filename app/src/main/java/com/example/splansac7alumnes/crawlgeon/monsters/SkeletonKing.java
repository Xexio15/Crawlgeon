package com.example.splansac7alumnes.crawlgeon.monsters;

import com.example.splansac7alumnes.crawlgeon.R;

import java.util.Random;

/**
 * Created by crreinal19.alumnes on 25/04/17.
 */

public class SkeletonKing extends Monster {
    public boolean wasCrit = false;
    public  int critAnim = R.drawable.skeletonkingcritattack;
    public int critDaño = 90;
    public SkeletonKing() {
        super(750, 35 , R.drawable.skeletonkingstatic1, R.drawable.skeletonkingstaticanim,R.drawable.skeletonkingattack,R.drawable.skeletonkingfallanim, R.raw.attack_skeleton, R.raw.attack_armor_skeleton, R.raw.pain_skeleton, R.raw.death_skeleton, "Fire");
    }

    public float getDaño(){
        Random r = new Random();
        int rand = r.nextInt(9);
        if(rand <= 9){
            wasCrit = true;
            return  critDaño;
        }else {
            return daño;
        }
    }

    public int getAttackAnim(){
        if( wasCrit){
            wasCrit = false;
            return critAnim;
        }else {
            return attackanim;
        }
    }

}

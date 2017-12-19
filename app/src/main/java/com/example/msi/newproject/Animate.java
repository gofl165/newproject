package com.example.msi.newproject;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Animate extends AppCompatActivity {
    ImageView mCountDown ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);

        mCountDown = (ImageView) findViewById(R.id.countdown);


    }
protected void onResume(){
        super.onResume();
    startAnimation();
    startPropertyAnimation();

}


    private void startAnimation() {
/*1*/    mCountDown.setBackgroundResource(R.drawable.frame_anim);

/*2*/    AnimationDrawable countdownAnim =
                (AnimationDrawable)mCountDown.getBackground();
/*3*/    countdownAnim.start();
    }

    private void startPropertyAnimation() {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(mCountDown, "alpha", 1, 0);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alpha);

        animatorSet.setDuration(2000);
        animatorSet.setStartDelay(2500);
        animatorSet.start();
        animatorSet.addListener(animatorListener);
    }
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                finish();
                startActivity(new Intent(getApplicationContext(), RestaurantMap.class));
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };
    }


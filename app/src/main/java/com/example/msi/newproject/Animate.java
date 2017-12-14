package com.example.msi.newproject;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by msi on 2017-12-12.
 */

public class Animate extends AppCompatActivity {

    ImageView mCountDown;
    private void startCountDownFrameAnimation() {
        mCountDown.setBackgroundResource(R.drawable.frame_anim);
        AnimationDrawable countdownAnim = (AnimationDrawable) mCountDown.getBackground();
        countdownAnim.start();
    }


}

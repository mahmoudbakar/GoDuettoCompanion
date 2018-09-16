package com.undecode.goduettocompanion.bakar.utils;

import android.animation.ObjectAnimator;
import android.view.View;

public class Animator
{
    public void vibrateView (View view)
    {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 5f, 0f, -5f, 0f); // rotate o degree then 20 degree and so on for one loop of rotation.
        // animateView (View object)
        rotate.setRepeatCount(5); // repeat the loop 20 times
        rotate.setDuration(40); // animation play time 100 ms
        rotate.start();
    }
}

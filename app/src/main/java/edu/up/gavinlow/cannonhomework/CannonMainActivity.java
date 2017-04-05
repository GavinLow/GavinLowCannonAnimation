package edu.up.gavinlow.cannonhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class CannonMainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cannon_main);

        // Create an animation canvas and place it in the main layout
        Animator ani = new CannonAnimator();
        AnimationCanvas canvas = new AnimationCanvas(this, ani);
        LinearLayout mainLayout = (LinearLayout)this.findViewById(R.id.activity_cannon_main);
        mainLayout.addView(canvas);

    }

}

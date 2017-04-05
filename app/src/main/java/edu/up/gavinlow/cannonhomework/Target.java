package edu.up.gavinlow.cannonhomework;

import android.graphics.Color;

/**
 * Created by gavinlow on 4/2/17.
 * @auuthor gavinlow
 * @date 4/4/17
 */

public class Target extends Circle
{
    //Instance variables for the target
    private static final int radius = 75;
    private int color;

    /**
     * Constructor for a target
     * @param x
     * @param y
     */
    public Target(int x, int y)
    {
        super(x,y,radius, Color.YELLOW);

        color = Color.YELLOW;
        paint.setColor(color);
    }

    /**
     * Changes targets color if target is hit
     *
     * @param hit true sets the target's color to red, yellow if fasle
     */
    public void hitTarget(boolean hit)
    {
        if(hit)
        {
            color = Color.RED;
        }
        else
        {
            color = Color.YELLOW;
        }
        paint.setColor(color);
    }


}

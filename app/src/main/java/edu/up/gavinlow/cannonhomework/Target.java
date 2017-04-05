package edu.up.gavinlow.cannonhomework;

import android.graphics.Color;

/**
 * Created by gavinlow on 4/2/17.
 */

public class Target extends Circle
{
    private static final int radius = 75;
    private int color;

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

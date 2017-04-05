package edu.up.gavinlow.cannonhomework;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by gavinlow on 4/2/17.
 */

public class CannonBall extends Circle
{
    private static final int radius = 50;

    private int vx;
    private int vy;
    private int ax;
    private int ay;

    public CannonBall(int initX, int initY, int initVx, int initVy, int initAx, int initAy)
    {
        super(initX, initY, radius, Color.BLACK);

        this.vx = initVx;
        this.vy = initVy;
        this.ax = initAx;
        this.ay = initAy;
    }

    public void updatePos()
    {
        x = x + vx;
        y = y + vy;
    }

    public void accelerateBall()
    {
        vx = vx + ax;
        vy = vy + ay;
    }

    public void checkCollision(int leftBound, int rightBound, int topBound, int bottomBound)
    {
        int left = x - radius;
        int right = x + radius;
        int top = y - radius;
        int bottom = y + radius;

        if((left < leftBound))
        {
            vx = -1*vx;
        }
        if((right > rightBound))
        {
            vx = -1*vx;
        }
        if((top > topBound))
        {
            vy = -1*vy;
        }
        if((bottom < bottomBound))
        {
            vy = -1*vy;
        }
    }
        public void drawMe(Canvas canvas)
        {
            canvas.drawCircle(x,y,radius, paint);
        }
}

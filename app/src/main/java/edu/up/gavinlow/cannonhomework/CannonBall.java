package edu.up.gavinlow.cannonhomework;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by gavinlow on 4/2/17.
 */

public class CannonBall extends Circle
{
    //sets the radius of the cannon ball
    private static final int radius = 50;

    //instance variables for the cannon ball
    private int vx;
    private int vy;
    private int ax;
    private int ay;

    /**
     * Constructor for the cannonball
     * @param initX
     * @param initY
     * @param initVx
     * @param initVy
     * @param initAx
     * @param initAy
     */
    public CannonBall(int initX, int initY, int initVx, int initVy, int initAx, int initAy)
    {
        super(initX, initY, radius, Color.BLACK);

        this.vx = initVx;
        this.vy = initVy;
        this.ax = initAx;
        this.ay = initAy;
    }

    /**
     * updates the position of the cannonball
     */
    public void updatePos()
    {
        x = x + vx;
        y = y + vy;
    }

    /**
     * adds the acceleration to the ball
     */
    public void accelerateBall()
    {
        vx = vx + ax;
        vy = vy + ay;
    }

    /**
     * Checks to see if the cannonball has collided with anything
     * @param leftBound
     * @param rightBound
     * @param topBound
     * @param bottomBound
     */
    public void checkCollision(int leftBound, int rightBound, int topBound, int bottomBound)
    {
        int left = x - radius;
        int right = x + radius;
        int top = y - radius;
        int bottom = y + radius;

        //inverts the velocity if it touches a screen boundary
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

    /**
     * Draws the cannonball on the canvas
     * @param canvas
     */
    public void drawMe(Canvas canvas)
    {
        canvas.drawCircle(x,y,radius, paint);
    }
}

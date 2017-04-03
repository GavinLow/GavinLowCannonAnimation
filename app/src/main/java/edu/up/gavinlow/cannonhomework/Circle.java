package edu.up.gavinlow.cannonhomework;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by gavinlow on 4/2/17.
 */

public abstract class Circle
{
    protected Paint paint = new Paint();
    protected int color;
    protected int radius;

    protected int x;
    protected int y;

    public Circle(int initX, int initY, int radius, int color)
    {
        this.x = initX;
        this.y = initY;
        this.radius = radius;
        this.color = color;
        paint.setColor(color);
    }

    public void drawMe(Canvas canvas)
    {
        canvas.drawCircle(x,y,radius, paint);
    }

    public boolean circleOverlap(Circle otherCircle)
    {
        int x1 = x;
        int x2 = otherCircle.getX();
        int y1 = y;
        int y2 = otherCircle.getY();

        int distance = (int)Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        return (distance < (radius + otherCircle.getRadius()));
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getRadius()
    {
        return  this.radius;
    }
}

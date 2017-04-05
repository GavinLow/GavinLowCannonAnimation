package edu.up.gavinlow.cannonhomework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by gavinlow on 4/2/17.
 */

public class Cannon
{
    //Initialize the cannon's velocity
    public static final int velocity = 175;

    //Initialize the cannon's position on the animation surface
    private static final int left = 0;
    private static final int right = 300;
    private static final int top = ((CannonAnimator.height-150));
    private static final int bottom = ((CannonAnimator.height-50));

    //initialize the pivot of the cannon
    private static final int pivot = CannonAnimator.height-100;

    //initialize the maximum pivot angle
    //its set to 60 to avoid weird graphical errors that were discovered through testing
    private static final int maxPivot = 60;

    //Instance Variables
    private RectF rect;
    private Path path;
    private Paint cannonPaint = new Paint();
    private int color;
    private int pivotAng;


    /**
     * Constructor for the cannon
     */
    public Cannon()
    {
        initCannon();

        rect = new RectF(left,top,right,bottom);
        path.addRect(rect, Path.Direction.CW);
    }

    /**
     * initializes the cannon instance variables
     */
    private void initCannon()
    {
        path = new Path();
        color = Color.BLACK;
        cannonPaint.setColor(color);
        pivotAng = 0;
    }

    /**
     * draws the cannon on the canvas
     * @param canvas
     */
    public void drawMe(Canvas canvas)
    {
        canvas.drawPath(path, cannonPaint);
    }

    /**
     * rotates the cannon
     * @param deg
     */
    public void rotate(float deg)
    {
        if((pivotAng <= maxPivot && deg > 0) || (pivotAng >= -maxPivot && deg < 0))
        {
            Matrix m = new Matrix();
            m.setRotate(deg, 0, pivot);
            path.transform(m);
            pivotAng += deg;
        }
    }

    /**
     * returns pivot angle
     * @return
     */
    public int getPivotAng()
    {
        return this.pivotAng;
    }

    /**
     * return Xcenter of the cannon
     * @return
     */
    public int getCenterX()
    {
        RectF temp = new RectF();
        path.computeBounds(temp, true);
        return (int)temp.centerX();
    }

    /**
     * return YCenter of the cannon
     * @return
     */
    public int getCenterY()
    {
        RectF temp = new RectF();
        path.computeBounds(temp, true);
        return (int)temp.centerY();
    }







}

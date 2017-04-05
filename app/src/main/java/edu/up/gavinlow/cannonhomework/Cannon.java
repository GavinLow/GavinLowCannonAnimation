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
    public static final int velocity = 175;

    private static final int left = 0;
    private static final int right = 300;
    private static final int top = ((CannonAnimator.height-150));
    private static final int bottom = ((CannonAnimator.height-50));

    private static final int pivot = CannonAnimator.height-100;

    private static final int maxPivot = 60;

    private RectF rect;
    private Path path;
    private Paint cannonPaint = new Paint();
    private int color;
    private int pivotAng;

    public Cannon()
    {
        initCannon();

        rect = new RectF(left,top,right,bottom);
        path.addRect(rect, Path.Direction.CW);
    }

    private void initCannon()
    {
        path = new Path();
        color = Color.BLACK;
        cannonPaint.setColor(color);
        pivotAng = 0;
    }

    public void drawMe(Canvas canvas)
    {
        canvas.drawPath(path, cannonPaint);
    }

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

    public int getPivotAng()
    {
        return this.pivotAng;
    }

    public int getCenterX()
    {
        RectF temp = new RectF();
        path.computeBounds(temp, true);
        return (int)temp.centerX();
    }

    public int getCenterY()
    {
        RectF temp = new RectF();
        path.computeBounds(temp, true);
        return (int)temp.centerY();
    }







}

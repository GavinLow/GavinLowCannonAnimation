package edu.up.gavinlow.cannonhomework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by gavinlow on 4/2/17.
 */

public class ControlRect
{
    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int FIRE = 0;

    private Paint rectPaint = new Paint();
    private Paint labelPaint = new Paint();
    private Rect rect;
    private String label;
    private int labelX;
    private int labelY;

    private int action;

    public ControlRect(int initLeft, int initTop, int initRight, int initBottom,
                       String label, int color, int action)
    {
        rect = new Rect(initLeft, initTop, initRight, initBottom);
        rectPaint.setColor(color);

        this.label = label;
        labelPaint.setColor(Color.BLACK);
        labelPaint.setTextSize(50);
        labelX = initLeft;
        labelY = rect.centerY();

        this.action = action;
    }

    public boolean containsPoint(int x, int y)
    {
        return rect.contains(x,y);
    }

    public void drawMe(Canvas canvas)
    {
        canvas.drawRect(rect, rectPaint);
        canvas.drawText(label, labelX, labelY, labelPaint);
    }

    public int getAction()
    {
        return this.action;
    }


}

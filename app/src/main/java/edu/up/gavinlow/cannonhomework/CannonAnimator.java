package edu.up.gavinlow.cannonhomework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import java.util.ArrayList;

/**
 * Created by gavinlow on 4/2/17.
 */

public class CannonAnimator implements Animator
{

    //Sets the size of the screen (landscape only)
    public static final int width = 2048;
    public static final int height = 1300;

    //sets the value of gravity
    private static final int gravity = 9;

    private Cannon cannon = null;

    private ArrayList<CannonBall> cannonBalls;
    private ArrayList<Target> targets;

    //Array with 3 indexes
    //0: control for aiming up
    //1: control for firing
    //2: control for aiming down
    private ControlRect[] cannonControls = null;

    public CannonAnimator()
    {
        cannonBalls = new ArrayList<>();
        targets = new ArrayList<>();
        cannon = new Cannon();

        initTargets();
        initControls();
    }

    private void initTargets()
    {
        Target t1 = new Target(1000,500);
        Target t2 = new Target(700,150);
        Target t3 = new Target(500,1000);
        Target t4 = new Target(1300,900);
        Target t5 = new Target(1600,300);



        targets.add(t1);
        targets.add(t2);
        targets.add(t3);
        targets.add(t4);
        targets.add(t5);
    }

    private void initControls()
    {
        cannonControls = new ControlRect[3];
        cannonControls[0] = new ControlRect(width - 200, 0, width, height / 3, "UP", Color.GRAY, ControlRect.UP);
        cannonControls[1] = new ControlRect(width - 200, height / 3, width, height * 2 / 3, "FIRE", Color.RED, ControlRect.FIRE);
        cannonControls[2] = new ControlRect(width - 200, height * 2 / 3, width, height, "DOWN", Color.GRAY, ControlRect.DOWN);

    }

    @Override
    public int interval()
    {
        return 30;
    }

    @Override
    public int backgroundColor()
    {
        return Color.WHITE;
    }

    @Override
    public boolean doPause()
    {
        return false;
    }

    @Override
    public boolean doQuit()
    {
        return false;
    }

    @Override
    public void tick(Canvas canvas)
    {
        //draw all targets
        for(Target t: targets)
        {
            t.drawMe(canvas);
            t.drawOtherTarget(canvas);
        }

        //update all cannonball positions
        //check if the cannonballs have hit a target
        for(CannonBall cb : cannonBalls)
        {
            if(cb.getX() > width)
            {
                cb = null;
                cannonBalls.remove(cb);
            }
            else
            {
                adjustVectors(cb);
                cb.drawMe(canvas);
                for(Target t : targets)
                {
                    if(cb.circleOverlap(t))
                    {
                        t.hitTarget(true);
                    }
                }
            }
        }

        cannon.drawMe(canvas);

        //Draws cannon control buttons
        for(ControlRect c : cannonControls)
        {
            c.drawMe(canvas);
        }

    }

    @Override
    public void onTouch(MotionEvent event)
    {
        int x = (int)event.getX();
        int y = (int)event.getY();

        for (ControlRect c: cannonControls)
            if(c.containsPoint(x,y))
            {
                action(event, c.getAction());
                break;
            }

    }

    private void action(MotionEvent event, int action)
    {
        switch(action)
        {
            case ControlRect.UP:
                cannon.rotate(-1);
                break;
            case ControlRect.FIRE:
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    shoot();
                }
                break;
            case ControlRect.DOWN:
                cannon.rotate(1);
                break;
        }
    }

    private void shoot()
    {
        double ang = Math.toRadians(cannon.getPivotAng());
        double vx = Math.cos(ang) * Cannon.velocity;
        double vy = Math.sin(ang) * Cannon.velocity;

        CannonBall newBall = new CannonBall(cannon.getCenterX(), cannon.getCenterY(), (int)vx, (int)vy, 0, gravity);
        cannonBalls.add(newBall);
    }

    private void adjustVectors(CannonBall cb)
    {
        cb.checkCollision(0, width*2, 0, height);
        cb.accelerateBall();
        cb.updatePos();
    }


}

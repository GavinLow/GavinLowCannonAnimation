package edu.up.gavinlow.cannonhomework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import java.util.ArrayList;

/**
 * Created by gavinlow on 4/2/17.
 *
 * @author gavinlow
 * @date 4/4/17
 */

public class CannonAnimator implements Animator
{

    //Sets the size of the screen (landscape only)
    public static final int width = 2048;
    public static final int height = 1300;

    //sets the value of gravity
    private static final int gravity = 9;

    //creates the cannon
    private Cannon cannon = null;

    //creates an arraylist of cannonballs and targets
    private ArrayList<CannonBall> cannonBalls;
    private ArrayList<Target> targets;

    //Array with 3 indexes
    //0: control for aiming up
    //1: control for firing
    //2: control for aiming down
    private ControlRect[] cannonControls = null;

    /**
     * constructor for the cannon animator
     */
    public CannonAnimator()
    {
        cannonBalls = new ArrayList<>();
        targets = new ArrayList<>();
        cannon = new Cannon();

        initTargets();
        initControls();
    }

    /**
     * initializes the targets
     */
    private void initTargets()
    {
        //creates 5 targets
        Target t1 = new Target(1000,500);
        Target t2 = new Target(700,150);
        Target t3 = new Target(500,1000);
        Target t4 = new Target(1300,900);
        Target t5 = new Target(1600,300);

        //adds the targets to the target array list
        targets.add(t1);
        targets.add(t2);
        targets.add(t3);
        targets.add(t4);
        targets.add(t5);
    }

    /**
     * creates the 3 control rectangles
     */
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

        //draws the cannon on the animation surface
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

        //if the control block is touched, perform the correct action
        for (ControlRect c: cannonControls)
            if(c.containsPoint(x,y))
            {
                action(event, c.getAction());
                break;
            }

    }

    /**
     * Performs the correct action when a the action block is pushed
     * @param event
     * @param action
     */
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

    /**
     * fires the cannon
     */
    private void shoot()
    {
        //calculates the necessary velocities to determine the arch of the ball
        double ang = Math.toRadians(cannon.getPivotAng());
        double vx = Math.cos(ang) * Cannon.velocity;
        double vy = Math.sin(ang) * Cannon.velocity;

        //creates a new cannonball with the correct horizontal and vertical velocities
        CannonBall newBall = new CannonBall(cannon.getCenterX(), cannon.getCenterY(), (int)vx, (int)vy, 0, gravity);
        cannonBalls.add(newBall);  //adds the ball to the cannonball array list
    }

    /**
     * adjusts the vectors of the cannonballs
     * @param cb
     */
    private void adjustVectors(CannonBall cb)
    {
        cb.checkCollision(0, width*2, 0, height);
        cb.accelerateBall();
        cb.updatePos();
    }


}

import bagel.Image;
import bagel.util.Point;

public abstract class ActiveTower extends Tower {

    private double drawAngle = 0;





    public abstract double getRadius();


    protected boolean cooldown = false;

    public void startCooldown() {

        cooldown = true;
    }

    public abstract void updateCooldown();

    public abstract Projectile shoot(Slicer target);

    public boolean getCooldown() {
        return cooldown;
    }

    public void setDrawAngle(double value)
    {

        drawAngle = value;
    }

    public double getDrawAngle() {
        return drawAngle;
    }



}

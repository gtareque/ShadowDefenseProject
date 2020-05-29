import bagel.Image;
import bagel.util.Point;

public abstract class Tower {
    private Point position;
    private double drawAngle = 0;
    private double prevAngle = 0;

    public void setPosition(Point position) {
        this.position = position;
    }
    public Point getPosition() {
        return position;
    }


    public abstract double getRadius();
    public abstract  int getPrice();
    public abstract Image getImage();
    public abstract void draw();
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

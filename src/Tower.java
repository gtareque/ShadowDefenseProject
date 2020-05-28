import bagel.Image;
import bagel.util.Point;

public abstract class Tower {
    private Point position;
    private boolean cooldown = false;
    private int cooldownFrames = 0;
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
    public void startCooldown() {
        cooldown = true;
    }
    public void updateCooldown() {
        if(cooldown) {
            cooldownFrames++;
            if((cooldownFrames % 60) == 0) {
                cooldown = false;
            }
        }
    }

    public abstract Projectile shoot(Slicer target);

    public boolean getCooldown() {
        return cooldown;
    }
}

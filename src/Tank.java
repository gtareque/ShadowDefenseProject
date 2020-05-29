import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;

public class Tank extends PassiveTower {
    private Image projectileImage = new Image("res/Images/tank_projectile.png");
    private static Image image = new Image("res/Images/tank.png");
    double radius = 100;
    private static final int price = 300;
    private final int COOLDOWN_PERIOD = 60;
    private int cooldownFrames = 0;

    public int getPrice() {
        return price;
    }

    public Image getImage() {
        return image;
    }

    public static Image getIcon() {
        return image;
    }



    public void draw() {

        image.draw(getPosition().x, getPosition().y, new DrawOptions().setRotation(getDrawAngle()));
        setDrawAngle(0);
    }

    public static int displayPrice() {
        return  price;
    }

    @Override
    public Projectile shoot(Slicer target) {
        return new Projectile(target, getPosition(), projectileImage);
    }

    @Override
    public double getRadius() {
        return radius;
    }
    public void updateCooldown() {
        if(cooldown) {
            cooldownFrames++;
            if((cooldownFrames % COOLDOWN_PERIOD) == 0) {
                cooldown = false;
            }
        }
    }
}

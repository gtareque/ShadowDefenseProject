import bagel.Image;
import bagel.util.Point;

public class AirSupport extends Tower {
    double radius = 100;
    private final int COOLDOWN_PERIOD = 60;
    private boolean cooldown = false;
    private int cooldownFrames = 0;
    private static Image image = new Image("res/images/airsupport.png");

    private static final int price = 300;

    public int getPrice() {
        return price;
    }

    public Image getImage() {
        return image;
    }


    public void draw() {
        image.draw(getPosition().x, getPosition().y);
    }

    public static Image getIcon() {
        return  image;
    }

    public static int displayPrice() {
        return  price;
    }

    @Override
    public Projectile shoot(Slicer target) {
        return null;
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

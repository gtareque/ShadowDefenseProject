import bagel.Image;
import bagel.util.Point;

public class SuperTank extends PassiveTower {
    private static Image image = new Image("res/images/supertank.png");
    private Image projectileImage = new Image("res/Images/supertank_projectile.png");
    private static final int price = 300;
    double radius = 150;



    public int getPrice() {
        return price;
    }

    @Override
    public Image getImage() {
        return image;
    }


    public void draw() {
        image.draw(getPosition().x, getPosition().y);
    }

    public static Image getIcon() {
        return image;
    }

    public static int displayPrice() {
        return  price;
    }
    public Projectile shoot(Slicer target) {
        return new Projectile(target, getPosition(), projectileImage);
    }

    @Override
    public double getRadius() {
        return radius;
    }
}

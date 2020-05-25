import bagel.Image;
import bagel.util.Point;

public class Tank extends PassiveTower {
    private static Image image = new Image("res/Images/tank.png");

    private static final int price = 300;

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
        image.draw(getPosition().x, getPosition().y);
    }

    public static int displayPrice() {
        return  price;
    }
}

import bagel.Image;
import bagel.util.Point;

public class SuperTank extends PassiveTower {
    private static Image image = new Image("res/images/supertank.png");
    private static final int price = 300;



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
}

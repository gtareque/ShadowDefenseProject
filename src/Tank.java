import bagel.Image;
import bagel.util.Point;

public class Tank extends Tower {
    private Image image = new Image("res/Images/tank.png");
    private Point position;
    private static final int price = 300;

    public int getPrice() {
        return price;
    }

    public Image getImage() {
        return image;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    public void draw() {
        image.draw(position.x, position.y);
    }
}

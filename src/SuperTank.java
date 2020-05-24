import bagel.Image;
import bagel.util.Point;

public class SuperTank extends Tower {
    private Image image = new Image("res/images/supertank.png");
    private final int price = 300;
    private Point position;


    public int getPrice() {
        return price;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }
    public void draw() {
        image.draw(position.x, position.y);
    }
}

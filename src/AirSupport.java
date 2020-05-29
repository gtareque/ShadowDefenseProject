import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Vector2;

public class AirSupport extends Tower implements Flyable{
    private Vector2 velocity = new Vector2(10 , 0);
    private Vector2 position;
    private boolean horizontal = false;

    private static Image image = new Image("res/images/airsupport.png");

    private static final int price = 300;

    public int getPrice() {
        return price;
    }

    public Image getImage() {
        return image;
    }
    public AirSupport() {
        horizontal = true;
    }

    public void draw() {
        if(horizontal == true) {
            image.draw(position.x, position.y, new DrawOptions().setRotation(Math.PI/2));
        } else {
            image.draw(position.x, position.y);
        }
    }

    public static Image getIcon() {
        return  image;
    }

    public static int displayPrice() {
        return  price;
    }

    public void move() {
        position = position.add(velocity);

    }
    public Point drop() {
        return null;
    }


    public void setFlyingPath(Point position) {
        this.position = new Vector2(0, position.y);


    }


}

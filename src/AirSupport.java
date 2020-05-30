import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Vector2;

import java.util.Random;

public class AirSupport extends Tower {
    Random rand = new Random();
    private Vector2 velocity = new Vector2(5 , 0);
    private Vector2 position;
    private boolean horizontal = false;
    private int framesToDetonate = 60;
    private int currentFrames = 0;

    public AirSupport() {
        framesToDetonate = rand.nextInt(180);
        horizontal = true;
    }

    private static Image image = new Image("res/images/airsupport.png");

    private static final int price = 300;

    public int getPrice() {
        return price;
    }

    public Image getImage() {
        return image;
    }


    public void draw() {
        if(horizontal) {
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
    public Bomb drop() {
        if(currentFrames == framesToDetonate) {
            currentFrames = 0;
            framesToDetonate = rand.nextInt(180);
            return new Bomb(position.asPoint());

        } else {
            currentFrames++;
        }
        return null;
    }


    public void setFlyingPath(Point position) {
        this.position = new Vector2(0, position.y);


    }


}

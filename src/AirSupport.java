import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Vector2;
import java.util.Random;


/**
 * The class contains all the meta data about the air support
 * Makes it move and drop explosives
 *
 */

public class AirSupport extends Tower {


    /* All variables */
    private Random rand = new Random();
    private Vector2 velocity;
    private Vector2 position;
    private boolean horizontal = false;
    private int framesToDetonate;  // time for drop
    private int currentFrames = 0;
    private static int scalar = 1;  // time scale
    private static Image image = new Image("res/images/airsupport.png");

    /* All constants */
    private static final int PRICE = 500;
    private  final int FRAMES_IN_THREE_SEC = 180;
    private final int SPEED = 5;


    /**
     * Constructor
     */
    public AirSupport() {
        framesToDetonate = rand.nextInt(FRAMES_IN_THREE_SEC);   // time to first drop

    }


    /**
     * Function is used to get price of the plane which is a static value
     * @return price of an Air support
     */

    public int getPrice() {
        return PRICE;
    }

    /**
     * Function used to get image of airsupport
     * @return Image of the airsupport
     */
    public Image getImage() {
        return image;
    }


    /**
     * Function renders the airsupport in each frame
     */
    public void draw() {
        if(horizontal) {
            image.draw(position.x, position.y, new DrawOptions().setRotation(Math.PI/2));
        } else {
            image.draw(position.x, position.y, new DrawOptions().setRotation(Math.PI));
        }
    }

    /**
     * static method to get the price to Buy Panel
     * @return price of the plane in integer
     */
    public static int displayPrice() {
        return  PRICE;
    }


    /**
     * Calculates the new position at each frame adjusting the timescale
     */
    public void move() {
        position = position.add(velocity.mul(scalar));

    }

    /**
     * updates the bomb drop functionality and returns a bomb when it's time
     * @return Bomb type, might be null
     */
    public Bomb drop() {
        if(currentFrames == (framesToDetonate/scalar)) {
            currentFrames = 0;
            framesToDetonate = rand.nextInt(FRAMES_IN_THREE_SEC);
            return new Bomb(position.asPoint());

        } else {
            currentFrames++;
        }
        return null;
    }

    /**
     * Sets the flying path - horizontal or vertical from the point
     * @param position Point which was clicked by user, has to be valid
     */

    public void setFlyingPath(Point position) {
        if (horizontal) {
            this.position = new Vector2(0, position.y);
            velocity = new Vector2(SPEED , 0);
        } else {
            this.position = new Vector2(position.x, 0);
            velocity = new Vector2(0 , SPEED);
        }

    }

    /**
     * Sets path depending on the index number of the air support in Level
     * @param isEven
     */
    public void setHorizontal(boolean isEven) {
        horizontal = isEven;
    }

    /**
     * Sets time scaler, updates each time L or K is pressed
     * @param value the time scale value
     */
    public static void setScaler(int value) {
        scalar = value;
    }
}

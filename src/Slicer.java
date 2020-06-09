/*
 *  Slicer Class - Slicer is the basic enemy of the game
 *  Code Author: G M ASIF TAREQUE - 1004497
 *  Last modification: 7/5/20
 *
 */

import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;
import java.util.List;
import bagel.DrawOptions;

public class Slicer {


    private Image slicerImage = new Image("res/images/slicer.png");
    private int health = 1;
    private double speed = 2;
    private double reward = 2;
    private int penalty = 1;



    /****************************/
    private int polylineIndex = 1;      // the targeted polyline point point index
    private double stepsCounter = 0;    // steps taken towards the target
    private double displacementLength;    // magnitude of the displacement between two vector points
    private Vector2 initVector;     // initial vector position
    private Vector2 finalVector;    // final vector position
    private Vector2 velocity;       // derived velocity
    private int targetFrames;       // the number of times the velocity must be added to initVector
    private boolean status = false;     // flag for checking if it has completed journey
    private static int scalar = 1;   // timescale multiplier
    private List<Point> polyLines;


    /**
     * Constructor
     * Gives the initial slicer properties such as velocity and target frames
     * @param polyLines The polyline generated from the map */
    public Slicer(List<Point> polyLines) {
        this.polyLines = polyLines;
        /* calculate displacement and velocity */
        initVector = new Vector2(polyLines.get(0).x, polyLines.get(0).y);
        finalVector = new Vector2(polyLines.get(1).x, polyLines.get(1).y);
        displacementLength = finalVector.sub(initVector).length();
        velocity = getVelocity(initVector, finalVector, speed).mul(scalar);

        /* calculate target frames */
        targetFrames = (int)(displacementLength / velocity.length());

        /* find and setup image rotation */
        double theta = getTheta(velocity);

    }



    public Slicer(List<Point> polyLines, Vector2 initVector, Vector2 finalVector, int polylineIndex) {
        this.polyLines = polyLines;
        this.initVector = initVector;
        this.finalVector = finalVector;
        displacementLength = finalVector.sub(initVector).length();
        velocity = getVelocity(initVector, finalVector, speed).mul(scalar);

        /* calculate target frames */
        targetFrames = (int)(displacementLength / velocity.length());

        /* find and setup image rotation */
        double theta = getTheta(velocity);
        this.polylineIndex = polylineIndex;
    }


    /**
     *
     * updates slicer's movements
     * @param polyLines The polyline generated from the map */
    public void updateSlicer() {
        /* keeps moving till end of polyline */
        if(polylineIndex < polyLines.size() - 1) {
            /* if a poly line point is reached */
            if (stepsCounter == targetFrames) {
                polylineIndex += 1;
                stepsCounter = 0;
                /* calculate displacement and velocity and target frames */
                initVector = finalVector;
                finalVector = new Vector2(polyLines.get(polylineIndex).x, polyLines.get(polylineIndex).y);
                displacementLength = finalVector.sub(initVector).length();
                velocity = getVelocity(initVector, finalVector, speed).mul(scalar);
                targetFrames = (int) (displacementLength / velocity.length());

            }

            /* if velocity too high, adding velocity it takes it beyond final vector */
            if(!outOfBounds(finalVector.sub(initVector), velocity)) {
                initVector = initVector.add(velocity);
                stepsCounter += 1;
            } else {
                initVector = finalVector;
                stepsCounter = targetFrames;

            }

            /* get the theta for rotation */
            double theta = getTheta(velocity);
            slicerImage.draw(initVector.x, initVector.y, new DrawOptions().setRotation(theta));
        }
        else {
            /* slicer has reached end */
            status = true;

        }

    }


    /**
     *
     * Calculates the velocity of the slicer
     * @param v1 initial vector
     * @param v2 final vector */


    public static Vector2 getVelocity(Vector2 v1, Vector2 v2, double speed) {
        Vector2 direction = v2.sub(v1);
        return direction.normalised().mul(speed);

    }

    /**
     *
     * Calculates the direction coordinates of the velocity
     * @param velocity The velocity whose direction is to be calculated */

    public static double getTheta(Vector2 velocity) {
        return Math.atan2(velocity.y, velocity.x);
   }


    /**
     *
     * Get method for slicer status */
    public boolean getStatus() {
        return status;
    }

    /**
     *
     * Updates slicer properties when scaler has been changed */
//    public void updateSlicer() {

//    }

    /**
     *
     * If velocity takes slicer beyond polyline point it checks if that is true
     * @param target  this is the destination polyline point
     * @param velocity current velocity of the slicer*/
    public static boolean outOfBounds(Vector2 target, Vector2 velocity) {
        if(target.length() < velocity.length()) {
            return true;
        }
        return false;
    }

    /**
     *
     * Set method for scaler
     * @param value The value to be given */
    public static void setScalar(int value) {
        scalar *= value;
    }


    public Point position(){
        return new Point(initVector.x, initVector.y);

    }

    void setHealth(int health) {
        this.health = this.health * health;
    }

    public int getHealth() {
        return health;
    }

    void setSpeed(double speed) {
        this.speed = this.speed * speed;
    }

    void setReward(double reward) {
        this.reward = reward;
    }

    void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    void setSlicerImage(Image image) {
        slicerImage = image;
    }

    public boolean kill(int damage) {
        health -= damage;
        System.out.println(health);
        if(health <= 0) {
            return true;
        }
        return false;
    }

    public Rectangle getBounds() {
        return slicerImage.getBoundingBoxAt(initVector.asPoint());
    }

    public List<Point> getPolyLines() {
        return polyLines;
    }

    public Vector2 getInitVector() {
        return initVector;
    }

    public Vector2 getFinalVector() {
        return finalVector;
    }

    public int getPolylineIndex() {
        return polylineIndex;
    }

    public static int getPenalty() {
        return 1;
    }

    public int penalize(){
        return 1;
    }

    public double getReward() {
        return reward;
    }
    public void updateVelocity() {
        displacementLength = finalVector.sub(initVector).length();
        stepsCounter = 0;
        velocity = getVelocity(initVector, finalVector, speed).mul(scalar);
        targetFrames = (int) (displacementLength / velocity.length());
    }
}

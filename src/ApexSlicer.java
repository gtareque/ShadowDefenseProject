import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.List;


/**
 *  Apex slicer is the enemy of the game
 * The class has function respawn and penalize
 * all the other functions are inherited from Slicer class
 */
public class ApexSlicer extends Slicer implements Respwanable {

    /* constants */
    private final static Image IMAGE = new Image("res/images/apexslicer.png");
    private final static int HEALTH = 25;
    private final static double SPEED = 0.5;
    private final static double REWARD = 150;
    private final static int PENALTY = 16;
    private final static int MAX_SPAWN = 4;


    /**
     * Constructor changes the health, reward, speed, penalty, image in Parent Class
     * @param polyLines Polyline points from the map
     */
    public ApexSlicer(List<Point> polyLines) {
        super(polyLines);
        setHealth(HEALTH);
        setSpeed(SPEED);
        setReward(REWARD);
        setPenalty(PENALTY);
        setSlicerImage(IMAGE);

    }

    /**
     * Called when slicer dies to respawn to child Slicer
     * @param array Arraylist containing all the slicer from Level
     */
    public void respawn(ArrayList<Slicer> array) {
        for (int i = 0; i < MAX_SPAWN; i ++) {
            array.add(new MegaSlicer(getPolyLines(), getInitVector(), getFinalVector(), getPolylineIndex()));
        }
    }


    /**
     * Called when slicer reaches end, to calculate penalty
     * Need this despite penalize to reach child slicer
     * Not a duplicate of penalize
     * @return The penalty value in integers
     */
    public static int getPenalty() {
        return MAX_SPAWN * MegaSlicer.getPenalty();
    }


    /**
     * Calls the static getPenalty() to calculate total penalty
     * @return the penalty value
     */
    public int penalize(){
        return getPenalty();
    }

}

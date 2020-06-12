import bagel.Image;
import bagel.util.Point;
import bagel.util.Vector2;

import java.util.ArrayList;
import java.util.List;

public class SuperSlicer extends Slicer implements Respwanable {
    private final static Image IMAGE = new Image("res/images/superslicer.png");
    private final static int HEALTH = 1;
    private final static double SPEED = 0.75;
    private final static double REWARD = 15;
    private final static int PENALTY = 2;


    /**
     * Constructor is called when it is sapwned from spawn event
     * The slicer is respawnable so implements Respawnable
     * @param polyLines of the map
     */

    public SuperSlicer(List<Point> polyLines) {
        super(polyLines);
        setHealth(HEALTH);
        setSpeed(SPEED);
        setReward(REWARD);
        setPenalty(PENALTY);
        setSlicerImage(IMAGE);
    }


    /**
     * Constructor is called when it's parent slicer is dead and is respawning
     * @param polyLines the polyline of the map
     * @param initVector the position where prvious slicer died
     * @param finalVector  the point toward which to go
     * @param polylineIndex
     */

    public SuperSlicer(List<Point> polyLines, Vector2 initVector, Vector2 finalVector, int polylineIndex) {
        super(polyLines, initVector, finalVector, polylineIndex);
        setHealth(HEALTH);
        setSpeed(SPEED);
        setReward(REWARD);
        setPenalty(PENALTY);
        setSlicerImage(IMAGE);
    }


    /**
     * Function called when this type of slicer dies and is to be respawned
     * @param array
     */
    public void respawn(ArrayList<Slicer> array) {
        for (int i = 0; i < 2; i ++) {
            array.add(new Slicer(getPolyLines(), getInitVector(), getFinalVector(), getPolylineIndex()));
        }
    }

    public static int getPenalty() {
       return 2 * Slicer.getPenalty();
    }

    public int penalize(){
        return getPenalty();
    }
}

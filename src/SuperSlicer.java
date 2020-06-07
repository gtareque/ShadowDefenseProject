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

    public SuperSlicer(List<Point> polyLines) {
        super(polyLines);
        setHealth(HEALTH);
        setSpeed(SPEED);
        setReward(REWARD);
        setPenalty(PENALTY);
        setSlicerImage(IMAGE);
    }




    public SuperSlicer(List<Point> polyLines, Vector2 initVector, Vector2 finalVector, int polylineIndex) {
        super(polyLines, initVector, finalVector, polylineIndex);
        setHealth(HEALTH);
        setSpeed(SPEED);
        setReward(REWARD);
        setPenalty(PENALTY);
        setSlicerImage(IMAGE);
    }

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

import bagel.Image;
import bagel.util.Point;

import java.util.List;

public class SuperSlicer extends Slicer {
    private final static Image IMAGE = new Image("res/images/superslicer.png");
    private final static int HEALTH = 1;
    private final static double SPEED = 1;
    private final static double REWARD = 2;
    private final static int PENALTY = 1;

    public SuperSlicer(List<Point> polyLines) {
        super(polyLines);
        setHealth(HEALTH);
        setSpeed(SPEED);
        setReward(REWARD);
        setPenalty(PENALTY);
        setSlicerImage(IMAGE);
    }


}

import bagel.Image;
import bagel.util.Point;

import java.util.List;

public class MegaSlicer extends Slicer {
    private final static Image IMAGE = new Image("res/images/megaslicer.png");
    private final static int HEALTH = 2;
    private final static double SPEED = 0.75;
    private final static double REWARD = 10;
    private final static int PENALTY = 4;
    public MegaSlicer(List<Point> polyLines) {

        super(polyLines);
        setHealth(HEALTH);
        setSpeed(SPEED);
        setReward(REWARD);
        setPenalty(PENALTY);
        setSlicerImage(IMAGE);

    }
}

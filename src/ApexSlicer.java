import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.List;

public class ApexSlicer extends Slicer implements Respwanable {
    private final static Image IMAGE = new Image("res/images/apexslicer.png");
    private final static int HEALTH = 25;
    private final static double SPEED = 0.5;
    private final static double REWARD = 150;
    private final static int PENALTY = 16;
    public ApexSlicer(List<Point> polyLines) {
        super(polyLines);
        setHealth(HEALTH);
        setSpeed(SPEED);
        setReward(REWARD);
        setPenalty(PENALTY);
        setSlicerImage(IMAGE);

    }
    public void respawn(ArrayList<Slicer> array) {
        for (int i = 0; i < 4; i ++) {
            array.add(new MegaSlicer(getPolyLines(), getInitVector(), getFinalVector(), getPolylineIndex()));
        }
    }

    public static int getPenalty() {
        return 4 * MegaSlicer.getPenalty();
    }

    public int penalize(){
        return getPenalty();
    }

}
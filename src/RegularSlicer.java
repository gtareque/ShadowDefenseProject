import bagel.Image;
import bagel.util.Point;

import java.util.List;

public class RegularSlicer extends Slicer {
    private Image slicerImage = new Image("res/images/slicer.png");
    private int health = 1;
    private double speed = 1;
    private double reward = 2;
    private int penalty = 1;

    public RegularSlicer(List<Point> polyLines){
        super(polyLines);
    };

}

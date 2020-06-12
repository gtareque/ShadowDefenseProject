import bagel.Image;
import bagel.util.Point;
import java.util.ArrayList;

/**
 * Bomb is the drop from AirSupport
 * When explodes kills everyone in the radius
 * Class has functions to update and remove dead slicers
 */

public class Bomb {

    private final int DAMAGE = 500;
    private final int TIME = 120;
    private final int RADIUS = 200;

    private int timeFrame = TIME;  // frames to explosion
    private Point position;
    private Image image = new Image("res/images/explosive.png");


    public Bomb(Point position){
        this.position = position;
    }


    /**
     * Updates the explosive each frame by updating the time to explosion
     * WHen explodes kills all slicers in the range
     * @param targets arraylist of spawned slicer
     * @return
     */
    public boolean update(ArrayList<Slicer> targets) {
        timeFrame--;
        image.draw(position.x, position.y);
        /* temporary array list */
        ArrayList<Slicer> dead = new ArrayList<>();

        if(timeFrame == 0) {
            /* explodes */
            for (Slicer target : targets)  {
                /* check slicers are in radius */
                if(position.distanceTo(target.position()) < RADIUS) {
                    boolean isDead = target.kill(DAMAGE);

                    if(isDead) {
                        dead.add(target);
                    }

                }
            }
            removeDead(dead, targets);
            return true;
        }
        return false;
    }


    /**
     * Removes the dead from the arraylist and hence the game
     * @param dead  array list of slicers to be romved
     * @param targets total array list of slicers
     */
    public static void removeDead(ArrayList<Slicer> dead, ArrayList<Slicer> targets) {
        for (Slicer slicer: dead) {
            if( slicer instanceof Respwanable) {
                /* respawn if respawnable */
                ((Respwanable)slicer).respawn(targets);
            }
            targets.remove(slicer);

        }
    }
}

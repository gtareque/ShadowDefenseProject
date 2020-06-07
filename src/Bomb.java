import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;

public class Bomb {
    private int timeFrame = 120;
    private Point position;
    private Image image = new Image("res/images/explosive.png");
    public Bomb(Point position){
        this.position = position;
    }
    public boolean update(ArrayList<Slicer> targets) {
        timeFrame--;
        image.draw(position.x, position.y);
        ArrayList<Slicer> dead = new ArrayList<>();
        if(timeFrame == 0) {
            for (Slicer target : targets)  {
                if(position.distanceTo(target.position()) < 200) {
                    boolean isDead = target.kill(500);
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

    public static void removeDead(ArrayList<Slicer> dead, ArrayList<Slicer> targets) {
        for (Slicer slicer: dead) {
            if( slicer instanceof Respwanable) {
                ((Respwanable)slicer).respawn(targets);
            }
            targets.remove(slicer);

        }
    }
}

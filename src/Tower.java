import bagel.Image;
import bagel.util.Point;

/**
 * Towers are all the defenses of the game
 */
public abstract class Tower {
    private Point position;
    public void setPosition(Point position) {
        this.position = position;
    }
    public abstract  int getPrice();
    public abstract Image getImage();
    public Point getPosition() {
        return position;
    }
    public abstract void draw();

}

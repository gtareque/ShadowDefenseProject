import bagel.Image;
import bagel.util.Point;

public abstract class Tower {
    private Point position;

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }



    public abstract  int getPrice();
    public abstract Image getImage();
    public abstract void draw();
}
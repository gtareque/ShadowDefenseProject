import bagel.Image;
import bagel.util.Point;

public abstract class Tower {


    public abstract  int getPrice();
    public abstract Image getImage();
    public abstract void setPosition(Point position);
    public abstract void draw();
}

import bagel.Image;
import bagel.util.Point;
import bagel.util.Vector2;

public class Projectile {
    private Image img = new Image("res/images/tank_projectile.png");
    private Slicer target;
    private Point startPosition;
    private Vector2 initVector;
    private double speed = 10;
    private Vector2 velocity;
    public Projectile(Slicer target, Point startPosition) {
        this.target = target;
        this.startPosition = startPosition;
        initVector = startPosition.asVector();
        velocity = getVelocity(target.position());
    }

    public void move() {
        initVector = initVector.add(velocity);
        img.draw(initVector.x, initVector.y);


    }
    public static Vector2 getVelocity(Point slicerPosition ) {
        double theta = Math.atan2(slicerPosition.y, slicerPosition.x);
        Vector2 velocity = new Vector2(10.0 * Math.cos(theta), 10 * Math.sin(theta));
        return velocity;
    }


}

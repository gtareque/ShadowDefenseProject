import bagel.Image;
import bagel.util.Point;
import bagel.util.Vector2;

public class Projectile {
    private Image img;
    private Slicer target;
    private Vector2 initVector;
    private double speed = 10.0;
    private Vector2 velocity;

    public Projectile(Slicer target, Point startPosition, Image img) {
        this.target = target;
        initVector = startPosition.asVector();
        this.img = img;

    }

    public void move() {
        velocity = getVelocity(target.position().asVector(), initVector, speed);
        initVector = initVector.add(velocity);
        img.draw(initVector.x, initVector.y);


    }

    public static Vector2 getVelocity(Vector2 slicerPosition, Vector2 projectilePosition, double speed ) {
        Vector2 displacement = slicerPosition.sub(projectilePosition);
        double theta = Math.atan2(displacement.y, displacement.x);
        Vector2 velocity = new Vector2(speed * Math.cos(theta), speed * Math.sin(theta));
        return velocity;
    }

    public Point getCenter() {
        return initVector.asPoint();
    }
}

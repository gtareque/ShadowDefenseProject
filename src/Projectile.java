import bagel.Image;
import bagel.util.Point;
import bagel.util.Vector2;

/**
 * Projectile is what a tank shoots
 */

public class Projectile {


    private Image img;
    private Slicer target;
    private Vector2 initVector;     // current position
    private double speed = 10.0;
    private Vector2 velocity;
    private static int scalar = 1;

    public Projectile(Slicer target, Point startPosition, Image img) {
        this.target = target;
        initVector = startPosition.asVector();
        this.img = img;

    }

    /**
     * Updates the projectile each fram
     */
    public void move() {
        velocity = getVelocity(target.position().asVector(), initVector, speed).mul(scalar);
        initVector = initVector.add(velocity);
        img.draw(initVector.x, initVector.y);


    }

    /**
     * calculate the velocity toards the target
     * @param slicerPosition target position
     * @param projectilePosition current position
     * @param speed speed at which it should move
     * @return  the velocity
     */
    public static Vector2 getVelocity(Vector2 slicerPosition, Vector2 projectilePosition, double speed ) {
        Vector2 displacement = slicerPosition.sub(projectilePosition);
        double theta = Math.atan2(displacement.y, displacement.x);
        Vector2 velocity = new Vector2(speed * Math.cos(theta), speed * Math.sin(theta));
        return velocity;
    }

    public Point getCenter() {
        return initVector.asPoint();
    }

    public static void setScalar(int value) {
        scalar = value;
    }
}

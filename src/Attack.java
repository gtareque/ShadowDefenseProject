import bagel.util.Vector2;
import java.util.ArrayList;

/**
 * Attack class handles the tower, projectile and the slicer involved in an attack
 * attacks are updated by moving projectile towards target
 */

public class Attack {

    Slicer target;
    Projectile projectile;
    Tank tower;
    double radius;      // attack radius of the tower
    double reward = 0;


    public Attack(Slicer target, Tank tower) {
        this.target = target;  // the slicer to be targeted
        /* create a new projectile */
        projectile = tower.shoot(target);
        this.tower = tower;
        radius = tower.getRadius();
    }


    /**
     * Function updates each attack by moving projectile towards it
     *
     * @param targets is arraylist of slicers that spawned if target is dead it is removed from targets
     * @return True if attack has reached end i.e projectile inside bounds of slicer
     * or the slicer went past the radius
     */

    public boolean updateAttack(ArrayList<Slicer> targets) {
        double distance = tower.getPosition().distanceTo(target.position());

        if(distance > radius || target.getHealth() <= 0) {
            /* slicer out of radius or dead */
            return true;
        } else {
            /* rotate tower toward target */
            Vector2 displacement = target.position().asVector().sub(tower.getPosition().asVector());
            tower.setDrawAngle(Math.atan2(displacement.y, displacement.x) + Math.PI/2);

            projectile.move();

            /* projectile has hit the target */
            if(target.getBounds().intersects(projectile.getCenter())) {
                boolean isDead = target.kill(tower.getDamage());
                if(isDead) {
                    /* respawn if possibble */
                    if(target instanceof Respwanable) {
                        ((Respwanable) target).respawn(targets);
                    }
                    reward = target.getReward();
                    targets.remove(target);
                    targets.trimToSize();

                }
                return true;
            }
            return false;
        }
    }


    /**
     * Get function for reward of this attack
     * @return the reward value
     */
    public double getReward() {
        return reward;
    }
}

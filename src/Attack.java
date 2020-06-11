import bagel.util.Vector2;
import java.util.ArrayList;

public class Attack {

    Slicer target;
    Projectile projectile;
    Tank tower;
    double radius;
    double reward = 0;


    public Attack(Slicer target, Tank tower) {
        this.target = target;
        projectile = tower.shoot(target);
        this.tower = tower;
        radius = tower.getRadius();
    }

    public boolean updateAttack(ArrayList<Slicer> targets) {

        double distance = tower.getPosition().distanceTo(target.position());
        if(distance > radius || target.getHealth() <= 0) {
            return true;
        } else {
            Vector2 displacement = target.position().asVector().sub(tower.getPosition().asVector());
            tower.setDrawAngle(Math.atan2(displacement.y, displacement.x) + Math.PI/2);
            projectile.move();
            if(target.getBounds().intersects(projectile.getCenter())) {
                boolean isDead = target.kill(tower.getDamage());
                if(isDead) {
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

    public double getReward() {
        return reward;
    }
}

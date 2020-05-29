import bagel.util.Vector2;

import java.util.Vector;

public class Attack {

    Slicer target;
    Projectile projectile;
    ActiveTower tower;
    double radius;
    public Attack(Slicer target, ActiveTower tower){
        this.target = target;
        projectile = tower.shoot(target);
        this.tower = tower;
        radius = tower.getRadius();
    }

    public boolean updateAttack() {

        double distance = tower.getPosition().distanceTo(target.position());
        if(distance > radius) {
            return true;
        } else {
            Vector2 displacement = target.position().asVector().sub(tower.getPosition().asVector());
            tower.setDrawAngle(Math.atan2(displacement.y, displacement.x) + Math.PI/2);
            projectile.move();
            return false;
        }
    }

}

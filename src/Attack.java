public class Attack {

    Slicer target;
    Projectile projectile;
    Tower tower;
    double radius;
    public Attack(Slicer target, Tower tower){
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
            projectile.move();
            return false;
        }
    }

}

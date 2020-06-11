import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Rectangle;
import java.awt.*;


public class Tank extends Tower {
    private Image projectileImage = new Image("res/Images/tank_projectile.png");
    private Image image = new Image("res/Images/tank.png");
    double radius = 100;
    private int damage = 1;
    private static final int price = 250;
    private int coolTime= 60;
    private int cooldownFrames = 0;
    private static int scaler = 1;
    public Tank(){

    }

    public Tank(Image projectileImage, Image image, double radius, int damage, int coolTime ) {
        this.projectileImage = projectileImage;
        this.image = image;
        this.radius = radius;
        this.damage = damage;
        this.coolTime = coolTime;
    }
    private double drawAngle = 0;



    protected boolean cooldown = false;

    public void startCooldown() {
        cooldown = true;
    }


    public boolean getCooldown() {
        return cooldown;
    }

    public int getPrice() {
        return price;
    }

    public Image getImage() {
        return image;
    }




    public void draw() {

        image.draw(getPosition().x, getPosition().y, new DrawOptions().setRotation(getDrawAngle()));

    }

    public static int displayPrice() {
        return  price;
    }


    public Projectile shoot(Slicer target) {
        return new Projectile(target, getPosition(), projectileImage);
    }


    public double getRadius() {
        return radius;
    }
    public void updateCooldown() {
        if(cooldown) {
            cooldownFrames++;
            if((cooldownFrames  % (coolTime/scaler)) == 0) {

                cooldown = false;
            }
        }
    }

    public int getDamage() {
        return  damage;
    }

    public void setDrawAngle(double value)
    {

        drawAngle = value;
    }

    public double getDrawAngle() {
        return drawAngle;
    }

    public static void setScaler(int value) {
        scaler = value;
    }

    public Rectangle getBoundingBox() {
        return image.getBoundingBoxAt(getPosition());

    }
}

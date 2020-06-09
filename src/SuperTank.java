import bagel.Image;

public class SuperTank extends Tank {
    private static Image image = new Image("res/images/supertank.png");
    private static Image projectileImage = new Image("res/Images/supertank_projectile.png");
    private static final int price = 600;
    private static double radius = 150;
    private static final int COOLDOWN_PERIOD = 30;
    private static int damage = 3;


    public SuperTank() {
        super(projectileImage, image, radius, damage, COOLDOWN_PERIOD );
    }

    public static int displayPrice() {
        return  price;
    }

}

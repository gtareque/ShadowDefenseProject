import bagel.*;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;

public class BuyPanel {
    private final int STARTING_CASH = 500;
    private final double TANK_IMAGE_X = 64;
    private final double AIRSUPPORT_IMAGE_X = 184 + 120;
    private final double SUPER_TANK_IMAGE_X = 184;
    private final Point CASH_POSITION = new Point(824, 65);
    private final String MESSAGE = "Key binds:\n\nS - start wave\nL - Increase Timescale\nK - Decrease Timescale";
    private Point messagePosition = new Point(450, 30);

    private Image panelImage = new Image("res/images/buypanel.png");
    private Image tankImage = new Image("res/images/tank.png");
    private Rectangle tankBounds;
    private Image superTankImage = new Image("res/images/supertank.png");
    private Rectangle superTankBounds;
    private Image airSupportImage = new Image("res/images/airsupport.png");
    private Rectangle airSupportBounds;
    private Rectangle panelBounds= panelImage.getBoundingBox();

    private int tankPrice = Tank.displayPrice();
    private int superTankPrice = SuperTank.displayPrice();
    private int airSupportPrice = AirSupport.displayPrice();
    private int cash = STARTING_CASH;


    private Font font = new Font("res/fonts/DejaVuSans-Bold.ttf", 15);
    private Font fontCash = new Font("res/fonts/DejaVuSans-Bold.ttf", 40);
    private Font infoFont = new Font("res/fonts/DejaVuSans-Bold.ttf", 12);
    Point panelCentre = panelBounds.centre();
    private final Point TANK_FONT_POSITION = new Point(50, panelCentre.y + 40);
    private final Point SUPER_TANK_FONT_POSITION = new Point(170, panelCentre.y + 40);
    private final Point AIRSUPPORT_FONT_POSITION = new Point(290, panelCentre.y + 40);
    private final double IMAGE_Y = panelCentre.y - 10;


    public void renderBuyPanel() {

        panelImage.drawFromTopLeft(0,0);
        tankImage.draw(TANK_IMAGE_X, IMAGE_Y);
        tankBounds = tankImage.getBoundingBoxAt(new Point(TANK_IMAGE_X, IMAGE_Y));

        superTankImage.draw(SUPER_TANK_IMAGE_X, IMAGE_Y);
        superTankBounds = superTankImage.getBoundingBoxAt(new Point(SUPER_TANK_IMAGE_X, IMAGE_Y));


        airSupportImage.draw(AIRSUPPORT_IMAGE_X, IMAGE_Y);
        airSupportBounds = airSupportImage.getBoundingBoxAt(new Point(AIRSUPPORT_IMAGE_X, IMAGE_Y));
        infoFont.drawString(MESSAGE, messagePosition.x, messagePosition.y);
        fontCash.drawString("$" + Integer.toString(cash), CASH_POSITION.x, CASH_POSITION.y);
        if(cash < Tank.displayPrice()) {
            font.drawString("$" +Integer.toString(Tank.displayPrice()), TANK_FONT_POSITION.x, TANK_FONT_POSITION.y, new DrawOptions().setBlendColour(Colour.RED));
        } else {
            font.drawString("$" +Integer.toString(Tank.displayPrice()), TANK_FONT_POSITION.x, TANK_FONT_POSITION.y);
        }
        if(cash < SuperTank.displayPrice()) {
            font.drawString("$" +Integer.toString(SuperTank.displayPrice()), SUPER_TANK_FONT_POSITION.x, SUPER_TANK_FONT_POSITION.y, new DrawOptions().setBlendColour(Colour.RED));
        } else {
            font.drawString("$" +Integer.toString(SuperTank.displayPrice()), SUPER_TANK_FONT_POSITION.x, SUPER_TANK_FONT_POSITION.y);
        }
        if(cash < AirSupport.displayPrice()) {
            font.drawString("$" + Integer.toString(AirSupport.displayPrice()), AIRSUPPORT_FONT_POSITION.x, AIRSUPPORT_FONT_POSITION.y, new DrawOptions().setBlendColour(Colour.RED));
        } else {
            font.drawString("$" +Integer.toString(AirSupport.displayPrice()),AIRSUPPORT_FONT_POSITION.x, AIRSUPPORT_FONT_POSITION.y);
        }
    }




    public Tank buyTank() {
        if (tankPrice <= cash) {
            return new Tank();
        }

        return null;
    }

    public SuperTank buySuperTank() {
        if(superTankPrice <= cash) {
            return new SuperTank();
        }
        System.out.println("Not Enough cash");
        return null;
    }

    public AirSupport buyAirSupport() {
        if(airSupportPrice <= cash) {
            return new AirSupport();
        }
        System.out.println("Not Enough cash");
        return null;
    }



    public void chargeMoney(int price) {
        cash -= price;
    }

    public Rectangle getTankBounds(){
        return tankBounds;
    }
    public Rectangle getSuperTankBounds() {
        return superTankBounds;
    }
    public Rectangle getAirSupportBounds() { return airSupportBounds; }
    public Rectangle getBuyPanelBounds() {
        return panelImage.getBoundingBox();
    }
    public void addReward(double reward) {
        cash += reward;
    }
}

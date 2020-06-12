import bagel.*;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * BuyPanel is the top panel of the game
 * Has cash record and the items to buy
 * Class has functions to render panel, buy items, get the item image bounds and add reward
 */

public class BuyPanel {

    /* constants */
    private final int STARTING_CASH = 500;
    private final double TANK_IMAGE_X = 64;
    private final double AIRSUPPORT_IMAGE_X = 184 + 120;
    private final double SUPER_TANK_IMAGE_X = 184;
    private final Point CASH_POSITION = new Point(824, 65);
    private final String MESSAGE = "Key binds:\n\nS - start wave\nL - Increase Timescale\nK - Decrease Timescale";
    private final Point MESSAGE_POSITION = new Point(450, 30);


    /* images and bounds */
    private Image panelImage = new Image("res/images/buypanel.png");
    private Image tankImage = new Image("res/images/tank.png");
    private Rectangle tankBounds;
    private Image superTankImage = new Image("res/images/supertank.png");
    private Rectangle superTankBounds;
    private Image airSupportImage = new Image("res/images/airsupport.png");
    private Rectangle airSupportBounds;
    private Rectangle panelBounds= panelImage.getBoundingBox();

    /* price and cash */
    private int tankPrice = Tank.displayPrice();
    private int superTankPrice = SuperTank.displayPrice();
    private int airSupportPrice = AirSupport.displayPrice();
    private int cash = STARTING_CASH;


    private Font font = new Font("res/fonts/DejaVuSans-Bold.ttf", 15);
    private Font fontCash = new Font("res/fonts/DejaVuSans-Bold.ttf", 40);
    private Font infoFont = new Font("res/fonts/DejaVuSans-Bold.ttf", 12);
    Point panelCentre = panelBounds.centre();

    /* constants */
    private final Point TANK_FONT_POSITION = new Point(50, panelCentre.y + 40);
    private final Point SUPER_TANK_FONT_POSITION = new Point(170, panelCentre.y + 40);
    private final Point AIRSUPPORT_FONT_POSITION = new Point(290, panelCentre.y + 40);
    private final double IMAGE_Y = panelCentre.y - 10;


    /**
     * Renders the buy panel and all the images and texts in it
     */

    public void renderBuyPanel() {

        panelImage.drawFromTopLeft(0,0);
        tankImage.draw(TANK_IMAGE_X, IMAGE_Y);
        tankBounds = tankImage.getBoundingBoxAt(new Point(TANK_IMAGE_X, IMAGE_Y));

        superTankImage.draw(SUPER_TANK_IMAGE_X, IMAGE_Y);
        superTankBounds = superTankImage.getBoundingBoxAt(new Point(SUPER_TANK_IMAGE_X, IMAGE_Y));


        airSupportImage.draw(AIRSUPPORT_IMAGE_X, IMAGE_Y);
        airSupportBounds = airSupportImage.getBoundingBoxAt(new Point(AIRSUPPORT_IMAGE_X, IMAGE_Y));
        infoFont.drawString(MESSAGE, MESSAGE_POSITION.x, MESSAGE_POSITION.y);
        fontCash.drawString("$" + Integer.toString(cash), CASH_POSITION.x, CASH_POSITION.y);
        if(cash < Tank.displayPrice()) {
            font.drawString("$" +Integer.toString(Tank.displayPrice()), TANK_FONT_POSITION.x,
                    TANK_FONT_POSITION.y, new DrawOptions().setBlendColour(Colour.RED));
        } else {
            font.drawString("$" +Integer.toString(Tank.displayPrice()), TANK_FONT_POSITION.x,
                    TANK_FONT_POSITION.y);
        }
        if(cash < SuperTank.displayPrice()) {
            font.drawString("$" +Integer.toString(SuperTank.displayPrice()), SUPER_TANK_FONT_POSITION.x,
                    SUPER_TANK_FONT_POSITION.y, new DrawOptions().setBlendColour(Colour.RED));
        } else {
            font.drawString("$" +Integer.toString(SuperTank.displayPrice()),
                    SUPER_TANK_FONT_POSITION.x, SUPER_TANK_FONT_POSITION.y);
        }
        if(cash < AirSupport.displayPrice()) {
            font.drawString("$" + Integer.toString(AirSupport.displayPrice()), AIRSUPPORT_FONT_POSITION.x,
                    AIRSUPPORT_FONT_POSITION.y, new DrawOptions().setBlendColour(Colour.RED));
        } else {
            font.drawString("$" +Integer.toString(AirSupport.displayPrice()),AIRSUPPORT_FONT_POSITION.x,
                    AIRSUPPORT_FONT_POSITION.y);
        }
    }


    /**
     * functions to start a purchase
     * check if enough cash
     * @return the instance of the item
     */
    public Tank buyTank() {
        if (tankPrice <= cash) {
            return new Tank();
        }

        return null;
    }

    /**
     * functions to start a purchase
     * check if enough cash
     * @return the instance of the item
     */

    public SuperTank buySuperTank() {
        if(superTankPrice <= cash) {
            return new SuperTank();
        }
        System.out.println("Not Enough cash");
        return null;
    }

    /**
     * functions to start a purchase
     * check if enough cash
     * @return the instance of the item
     */
    public AirSupport buyAirSupport() {
        if(airSupportPrice <= cash) {
            return new AirSupport();
        }
        System.out.println("Not Enough cash");
        return null;
    }

    /**
     * Function charges the price after purchase confirmed
     * @param price the price to be deducted
     */

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

    /**
     * Adds the reward to cash variable
     * @param reward The reward value
     */
    public void addReward(double reward) {
        cash += reward;
    }
}

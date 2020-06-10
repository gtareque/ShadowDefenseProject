import bagel.*;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;

public class BuyPanel {
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
    private int cash = 500;
    private Font font = new Font("res/fonts/DejaVuSans-Bold.ttf", 12);
    Point panelCentre = panelBounds.centre();


    public void renderBuyPanel() {

        panelImage.drawFromTopLeft(0,0);
        tankImage.draw(64, panelCentre.y);
        tankBounds = tankImage.getBoundingBoxAt(new Point(64, panelCentre.y));

        font.drawString("$250", 52, panelCentre.y + 40, new DrawOptions().setBlendColour(Colour.RED));
        superTankImage.draw(184, panelCentre.y);
        font.drawString("$600", 172, panelCentre.y + 40);
        superTankBounds = tankImage.getBoundingBoxAt(new Point(184, panelCentre.y));
        airSupportImage.draw(184 + 120, panelCentre.y);
        airSupportBounds = airSupportImage.getBoundingBoxAt(new Point(184 + 120, panelCentre.y));
        font.drawString("$500", 172 + 120, panelCentre.y + 40);
        font.drawString(Integer.toString(cash), 184 + 120 +120, panelCentre.y);
    }




    public Tank buyTank() {
        if (tankPrice <= cash) {
            return new Tank();
        }
        System.out.println("Not Enough cash");
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
        if(superTankPrice <= cash) {
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

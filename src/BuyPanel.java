import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

public class BuyPanel {
    private Image panelImage = new Image("res/images/buypanel.png");
    private Image tankImage = new Image("res/Images/tank.png");
    private Rectangle tankBounds;
    private Image superTankImage = new Image("res/Images/supertank.png");
    private Rectangle superTankBounds;
    private Image airSupportImage = new Image("res/Images/airsupport.png");
    private Rectangle airSupportBounds;
    private Rectangle panelBounds= panelImage.getBoundingBox();
    private int cash = 500;
    private int tankPrice = 200;
    private int superTankPrice = 300;
    Point panelCentre = panelBounds.centre();


    public void renderBuyPanel() {
        panelImage.drawFromTopLeft(0,0);
        tankImage.draw(64, panelCentre.y);
        tankBounds = tankImage.getBoundingBoxAt(new Point(64, panelCentre.y));
        superTankImage.draw(184, panelCentre.y);
        superTankBounds = tankImage.getBoundingBoxAt(new Point(184, panelCentre.y));
        airSupportImage.draw(184 + 120, panelCentre.y);
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

    public Image getTankImage() {
        return tankImage;
    }

    public Image getSuperTankImage() {
        return superTankImage;
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

    public Rectangle getBuyPanelBounds() {
        return panelImage.getBoundingBox();
    }
}

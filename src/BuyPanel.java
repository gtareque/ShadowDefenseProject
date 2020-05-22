import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

public class BuyPanel {
    private Image panelImage = new Image("res/images/buypanel.png");
    private Image tankImage = new Image("res/Images/tank.png");
    private Image superTankImage = new Image("res/Images/supertank.png");
    private Image airSupportImage = new Image("res/Images/airsupport.png");
    private Rectangle panelBounds= panelImage.getBoundingBox();
    private int cash = 500;
    private int tankPrice = 200;
    Point panelCentre = panelBounds.centre();

    public void renderBuyPanel() {
        panelImage.drawFromTopLeft(0,0);
        tankImage.draw(64, panelCentre.y);
        superTankImage.draw(184, panelCentre.y);
        airSupportImage.draw(184 + 120, panelCentre.y);
    }


    public Tank buyTank() {
        if (tankPrice <= cash) {
            return new Tank();
        }
        System.out.println("Not Enough cash");
        return null;
    }

    public Image getTankImage() {
        return tankImage;
    }
}

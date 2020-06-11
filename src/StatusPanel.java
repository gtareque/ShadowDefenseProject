import bagel.DrawOptions;
import bagel.Font;
import bagel.Image;
import bagel.util.Colour;
import bagel.util.Point;

class StatusPanel
{   private final Point IMG_POS = new Point(512 , 755.5);
    private final Point LIVES_POS = new Point(900, 760);
    private final Point SCALE_POS = new Point(300, 760);
    private final Point WAVE_POS = new Point(5, 760);
    private final Point STATUS_POS = new Point(450, 760);
    private static int scaler = 1;

    Image img = new Image("res/images/statuspanel.png");

    public int lives;
    private Font font = new Font("res/fonts/DejaVuSans-Bold.ttf", 15);

    public StatusPanel()
    {
        lives = 25;
    }



    public void renderStatusPanel(String status, int waveNum) {
        img.draw(IMG_POS.x, IMG_POS.y);
        font.drawString("Lives: " + lives, LIVES_POS.x, LIVES_POS.y);
        if(scaler > 1) {
            font.drawString("Time Scale: "+ scaler+".0", SCALE_POS.x, SCALE_POS.y,
            new DrawOptions().setBlendColour(Colour.GREEN));
        }else {
            font.drawString("Time Scale: " + scaler + ".0", SCALE_POS.x, SCALE_POS.y);
        }
        font.drawString("Status: " + status, STATUS_POS.x, STATUS_POS.y);
        font.drawString("Wave:" + (Integer.toString(waveNum)), WAVE_POS.x, WAVE_POS.y);

    }

    public boolean loseLife(int penalty) {
        lives -= penalty;
        return lives <=0;
    }

    public static void setScaler(int value) {
        scaler = value;
    }
}
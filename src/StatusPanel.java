import bagel.Font;
import bagel.Image;

class StatusPanel
{
    private int scaler = 1;

    Image img = new Image("res/images/statuspanel.png");

    public int lives;
    private Font font = new Font("res/fonts/DejaVuSans-Bold.ttf", 15);

    public StatusPanel()
    {
        lives = 25;
    }



    public void renderStatusPanel() {
        img.draw(512, 755.5);
        font.drawString("Lives: " + Integer.toString(lives), 900, 760);
        font.drawString("Scaler: "+Integer.toString(scaler), 450, 760);
    }

    public boolean loseLife(int penalty) {
        lives -= penalty;
        return lives == 0;
    }

    public void setScaler(int value) {
        scaler = value;
    }
}
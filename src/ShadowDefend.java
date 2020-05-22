/*
 *  Shadow Defend
 *  Code Author: G M ASIF TAREQUE - 1004497
 *  Last modification: 7/5/20
 *
 */



import bagel.*;
import bagel.map.TiledMap;
import bagel.util.Point;
import java.util.List;
import bagel.Input;
import bagel.util.Rectangle;

public class ShadowDefend extends AbstractGame {
    private TiledMap map;
    private int frameCount = 0;     // counts the number of frame updates in total
    private Waves currWave;     // stores the running wave in the game
    private List<Point> polyLines;
    private int slicerCount = 0;    // number of slicer created
    private boolean gameRunning;    // checks if S is pressed
    private static final int MAX_SLICERS = 5;
    private static final int FRAMES_IN_FIVE_SEC = 300;
    private int scaler = 1;     // default scaler value is one
    private BuyPanel buyPanel;
    private boolean buyMode = false;
    private Tower currentlyBuying;
    private List<Rectangle> mapBounds;
    /* Main */
    public static void main(String args[]) {

        new ShadowDefend().run();
    }


    /*
     *   Constructor
     *   Sets the game */
    public ShadowDefend(){

        map = new TiledMap("res/levels/1.tmx");
        polyLines = map.getAllPolylines().get(0);

        gameRunning = false;
        currWave = new Waves();
        buyPanel = new BuyPanel();

    }


    /**
     *
     * * Updates the game state approximately 60 times a second, potentially reading from input.
     * * @param input The input instance which provides access to keyboard/mouse state information.     */
    @Override
    protected void update(Input input) {
        /**********************************/
        frameCount++;
        map.draw(0,0,0,0, 1080.0,1080.0);
        buyPanel.renderBuyPanel();
        if(input.isDown(MouseButtons.LEFT)) {
            if(buyPanel.getTankImage().getBoundingBox().intersects(input.getMousePosition())) {

                currentlyBuying = buyPanel.buyTank();
                if(currentlyBuying != null) {
                    buyMode = true;
                }

            }
        }
        if(buyMode) {
            currentlyBuying.getImage().draw(input.getMouseX(), input.getMouseY());
        }
        if(buyMode && input.wasPressed(MouseButtons.LEFT)) {
           if(map.hasProperty((int)input.getMouseX(),(int)input.getMouseY(), "blocked")) {               buyMode = false;
            } else {
                /* buy it */
                buyMode = false;
            }
        }


        if(input.isDown(Keys.S) && !gameRunning) {
            gameRunning = true;
        }

        if(input.wasPressed(Keys.L) ) {

            scaler++;
            /* update already created slicer properties */
            currWave.updateCurrentSlicers(scaler);
        }
        if(input.wasPressed(Keys.K) ) {

            if (scaler > 1) {
                scaler--;
            }
            /* update already created slicer properties */
            currWave.updateCurrentSlicers(scaler);
        }

        if(gameRunning) {
            /* while in default scale(scale = 1) in 5 seconds 300 frames occur */
            if ((frameCount == 0 || (frameCount % (FRAMES_IN_FIVE_SEC / scaler)) == 0) && slicerCount < MAX_SLICERS) {
                currWave.createNewSlicer(polyLines);
                slicerCount++;
            }
            if (!currWave.isWaveComplete()) {
                /* move the slicer as wave not complete */
                currWave.updateSlicerPosition(polyLines);
            } else {
                /* game over */
                Window.close();
            }
        }


    }




}

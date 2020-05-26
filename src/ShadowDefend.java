/*
 *  Shadow Defend
 *  Code Author: G M ASIF TAREQUE - 1004497
 *  Last modification: 7/5/20
 *
 */



import bagel.*;
import bagel.map.TiledMap;
import bagel.util.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import bagel.Input;
import bagel.util.Rectangle;

public class ShadowDefend extends AbstractGame {
    private TiledMap mapOne;
    private TiledMap mapTwo;
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
    private ArrayList<Level> levels = new ArrayList<Level>();
    int currentLevelIndex = 0;
    /* Main */
    public static void main(String args[]) {

        new ShadowDefend().run();
    }


    /*
     *   Constructor
     *   Sets the game */
    public ShadowDefend(){
        mapOne = new TiledMap("res/levels/1.tmx");
        mapTwo = new TiledMap(("res/levels/2.tmx"));
        levels.add(new Level(mapOne));
        levels.add(new Level(mapTwo));
        polyLines = mapOne.getAllPolylines().get(0);

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
        frameCount++;

        /* tower placement position validation */
        boolean validPosition = !mapOne.hasProperty((int)input.getMouseX(),(int)input.getMouseY(), "blocked");
        boolean insideBuyPanelRectangle = buyPanel.getBuyPanelBounds().intersects(input.getMousePosition());
        /* render everything */
        levels.get(currentLevelIndex).renderLevel();
        buyPanel.renderBuyPanel();
        levels.get(currentLevelIndex).drawTowers();


        FileReader textInput = null;
        try {
            textInput = new FileReader("res/waves.txt");
        } catch (FileNotFoundException e) {
            System.out.println("flga1");
            e.printStackTrace();
        }
        /* buy panel controls */
        if(input.wasPressed(MouseButtons.LEFT) && insideBuyPanelRectangle) {
            if(buyPanel.getTankBounds().intersects(input.getMousePosition())) {
                currentlyBuying = buyPanel.buyTank();
            }

            if(buyPanel.getSuperTankBounds().intersects(input.getMousePosition())) {

                currentlyBuying = buyPanel.buySuperTank();
            }

            if(buyPanel.getAirSupportBounds().intersects(input.getMousePosition())) {

                currentlyBuying = buyPanel.buyAirSupport();
            }

            if(currentlyBuying != null) {
                buyMode = true;
            }
        }

        if((validPosition && !insideBuyPanelRectangle) && buyMode) {
            currentlyBuying.getImage().draw(input.getMouseX(), input.getMouseY());
        }

        if((input.wasPressed(MouseButtons.LEFT) && !insideBuyPanelRectangle) && buyMode) {
            buyMode =false;
            buyPanel.chargeMoney(currentlyBuying.getPrice());
            levels.get(currentLevelIndex).addTowers(currentlyBuying);
            currentlyBuying.setPosition(new Point(input.getMouseX(), input.getMouseY()));
        }


        /* scaler controls */
        if(input.wasPressed(Keys.S) && !gameRunning) {
            gameRunning = true;
            try {
                levels.get(currentLevelIndex).createWaves(textInput);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
//            if ((frameCount == 0 || (frameCount % (FRAMES_IN_FIVE_SEC / scaler)) == 0) && slicerCount < MAX_SLICERS) {
//                currWave.createNewSlicer(polyLines);
//                slicerCount++;
//            }
            levels.get(currentLevelIndex).playLevel();
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

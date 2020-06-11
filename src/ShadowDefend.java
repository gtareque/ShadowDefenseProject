/*
 *  Shadow Defend
 *  Code Author: G M ASIF TAREQUE - 1004497
 *  Last modification: 7/5/20
 *
 */



import bagel.*;
import bagel.map.TiledMap;
import bagel.util.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import bagel.Input;


public class ShadowDefend extends AbstractGame {

    private final int MAX_TIMESCALE = 5;
    private final int MIN_TIMESCALE = 1;

    /* Status messages */
    private final String WINNER_MESSAGE = "Winner!";
    private final String WAITING_MESSAGE = "Awaiting start";
    private final String WAVE_MESSAGE = "Wave in progress";
    private final String PLACING = "Placing";
    private final String GAME_OVER = "Game over!";
    private TiledMap mapOne;
    private TiledMap mapTwo;
    private boolean gameRunning;    // checks if S is pressed
    private int scaler = MIN_TIMESCALE;     // default scaler value is one
    private BuyPanel buyPanel;
    private boolean buyMode = false;
    private Tower currentlyBuying;
    private ArrayList<Level> levels = new ArrayList<Level>();
    private int currentLevelIndex = 0;
    private String status = "Awaiting start";
    private String prevStatus = status;
    private boolean gameOver = false;


    /* Main */
    public static void main(String[] args) {

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
        gameRunning = false;
        buyPanel = new BuyPanel();

    }


    /**
     *
     * * Updates the game state approximately 60 times a second, potentially reading from input.
     * * @param input The input instance which provides access to keyboard/mouse state information.     */
    @Override
    protected void update(Input input) {


        /* tower placement position validation */
        if(input != null) {
            boolean intersectingOther = levels.get(currentLevelIndex).checkTowerPosition(new Point(input.getMouseX(),
                    input.getMouseY()));
            boolean hasBlock = mapOne.hasProperty((int) input.getMouseX(), (int) input.getMouseY(), "blocked");
            boolean insideBuyPanelRectangle = buyPanel.getBuyPanelBounds().intersects(input.getMousePosition());
            boolean validPosition = (!hasBlock && !insideBuyPanelRectangle && !intersectingOther);


            /* render everything */
            levels.get(currentLevelIndex).renderLevel();
            buyPanel.renderBuyPanel();
            levels.get(currentLevelIndex).drawTowers();



            /* buy panel controls */
            if (input.wasPressed(MouseButtons.LEFT) && insideBuyPanelRectangle) {
                if (buyPanel.getTankBounds().intersects(input.getMousePosition())) {
                    currentlyBuying = buyPanel.buyTank();
                }

                if (buyPanel.getSuperTankBounds().intersects(input.getMousePosition())) {
                    currentlyBuying = buyPanel.buySuperTank();
                }

                if (buyPanel.getAirSupportBounds().intersects(input.getMousePosition())) {
                    currentlyBuying = buyPanel.buyAirSupport();
                }

                if (currentlyBuying != null) {

                    buyMode = true;
                    prevStatus = status;
                    status = PLACING;
                }
            }

            if (input.wasPressed(MouseButtons.RIGHT) && buyMode) {
                buyMode = false;
                currentlyBuying = null;
                status = prevStatus;
            }

            if ((validPosition) && buyMode) {
                currentlyBuying.getImage().draw(input.getMouseX(), input.getMouseY());
            }

            if ((input.wasPressed(MouseButtons.LEFT) && validPosition) && buyMode) {
                buyMode = false;
                status = prevStatus;
                buyPanel.chargeMoney(currentlyBuying.getPrice());
                levels.get(currentLevelIndex).addTowers(currentlyBuying);
                if (currentlyBuying instanceof AirSupport) {
                    ((AirSupport) currentlyBuying).setFlyingPath(new Point(input.getMouseX(), input.getMouseY()));
                    currentlyBuying = null;
                } else {
                    currentlyBuying.setPosition(new Point(input.getMouseX(), input.getMouseY()));
                    currentlyBuying = null;

                }
            }
        }

        FileReader textInput = null;
        try {
            textInput = new FileReader("res/waves.txt");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        if(!gameOver) {
            if(input.wasPressed(Keys.S) && !gameRunning) {
                gameRunning = true;
                try {
                    levels.get(currentLevelIndex).createWaves(textInput);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
            if(!levels.get(currentLevelIndex).isInWave()) {
                status = WAITING_MESSAGE;
            }

        /* scaler controls */

            if (input.wasPressed(Keys.S) && !levels.get(currentLevelIndex).isInWave() && gameRunning) {
                status = "wave in progress";
                levels.get(currentLevelIndex).waveStart();

            }
        }

        if(input.wasPressed(Keys.L) ) {
            if(scaler < MAX_TIMESCALE) {
                scaler++;
                Event.setScaler(scaler);
                Slicer.setScalar(scaler);
                StatusPanel.setScaler(scaler);
                Tank.setScaler(scaler);
                Projectile.setScalar(scaler);
                AirSupport.setScaler(scaler);
            }

        }
        if(input.wasPressed(Keys.K) ) {
            if(scaler > MIN_TIMESCALE ) {
                scaler--;
                Event.setScaler(scaler);
                Slicer.setScalar(scaler);
                StatusPanel.setScaler(scaler);

                Tank.setScaler(scaler);
                Projectile.setScalar(scaler);
                AirSupport.setScaler(scaler);

            }

        }



        if(gameRunning && levels.get(currentLevelIndex).isInWave()) {
            if (!levels.get(currentLevelIndex).getStatus()) {
                if(levels.get(currentLevelIndex).playLevel()) {
                    status = GAME_OVER;
                    gameOver = true;
                    gameRunning = false;
                };
                buyPanel.addReward(levels.get(currentLevelIndex).getReward());
            } else if(currentLevelIndex < levels.size() - 1) {
                levels.get(currentLevelIndex).isInWave();
                currentLevelIndex++;
                gameRunning = false;
            } else {
                /* game over */
                status = WINNER_MESSAGE;
            }
        }



        levels.get(currentLevelIndex).renderStatusPanel(status);

    }







}

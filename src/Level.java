import bagel.map.TiledMap;
import bagel.util.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Level {
    /**
     * Each level has map and waves and a new status panel
     */

    private static double reward;
    private ArrayList<Bomb> bombs = new ArrayList<>();      // drops by airsupport
    private TiledMap map;
    private ArrayList<Tank> tanks = new ArrayList<>();  // contains all the tanks
    private ArrayList<AirSupport> passiveTowers = new ArrayList<>();    //contains all the airsupports
    private LinkedList<Waves> waves = new LinkedList<>();   // has all the waves
    private Waves wave;     // current wave
    private boolean status = false;     // True if level complete
    private boolean waveRunning = false;
    private ArrayList<Attack> attacks = new ArrayList<>();
    StatusPanel statusPanel;
    int waveNumber = 0;     // used for status panel



    public Level(TiledMap map) {
        this.map = map;
        statusPanel = new StatusPanel();
    }

    /**
     * renders the map
     */
    public void renderLevel() {
        map.draw(0,0,0,0, 1024.0,1024.0);
    }

    /**
     * Add towers to the arraylist but first checks it's class
     * @param t the tower to be added
     */
    public void addTowers(Tower t) {

        if(t instanceof Tank) {
            tanks.add((Tank)t);
        } else {
            passiveTowers.add((AirSupport) t);
            ((AirSupport) t).setHorizontal((passiveTowers.size() - 1) % 2 == 0);
        }
    }


    /**
     * Renders the towers
     */
    public void drawTowers() {
        for (Tank tower : tanks) {
            tower.draw();
        }
    }


    /**
     * Reads the waves.txt file
     * @param file waves.txt
     * @throws FileNotFoundException If file is missing
     */
    public void createWaves(FileReader file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()) {
            int numSpawn = 0;
            String slicerType;
            String line = sc.nextLine();
            int waveIndex = Integer.parseInt(line.substring(0, line.indexOf(',')));

            if (waves.isEmpty()) {
                waves.add(new Waves());
            }

            if (waveIndex -1 > waves.size() - 1) {
                waves.add(new Waves());
            }

            line = line.substring(line.indexOf(',') + 1);
            String type = line.substring(0, line.indexOf(','));
            line = line.substring(line.indexOf(',') + 1);

            if (line.contains(",")) {
                /* spawn event */
                numSpawn = Integer.parseInt(line.substring(0, line.indexOf(',')));
                line = line.substring(line.indexOf(',') + 1);
                slicerType = line.substring(0, line.indexOf(','));
                int delay = Integer.parseInt(line.substring(line.indexOf(',') + 1));
                Spawn event = new Spawn(numSpawn, delay, slicerType, waves.get(waveIndex - 1), map.getAllPolylines().get(0));
                waves.get(waveIndex - 1).addEvent(event);
            }else {
                /* delay event */
                int delay = Integer.parseInt(line.substring(line.indexOf(',') + 1));
                Delay event = (new Delay(delay));
                waves.get(waveIndex - 1).addEvent((event));
            }

        }


    }

    /**
     * Updates waves, towers attacks in the current level
     * @return True if GAME IS OVER
     */
    public boolean playLevel() {


            if(waveRunning) {
                if(!wave.isWaveComplete()) {

                    if(wave.playWaves(statusPanel)) {
                        /* all lives lost */
                        return true;
                    }

                    /* attacks */
                    ArrayList<Slicer> slicers = wave.getSlicers();
                    setTarget(tanks, slicers, attacks);
                    updateAttacks(attacks, slicers);
                    updateAirAttacks(passiveTowers, bombs);
                    updateBombs(bombs, slicers);
                } else {
                    /* wave complete update reward and pause */
                    reward = waveNumber * 100 + 150;
                    waveRunning = false;
                }
            }

            if(waves.isEmpty() && wave.isWaveComplete()) {
                /* level complete */
                status = true;

            }
            return false;
    }


    public boolean getStatus() {
        return status;
    }

    /**
     * Sets target for towers which are in radius
     *
     * @param towers The tanks
     * @param slicers Potential targets
     * @param attacks The event of an attack
     */

    public static void setTarget(ArrayList<Tank> towers, ArrayList<Slicer> slicers, ArrayList<Attack> attacks) {
        for (Tank tower : towers) {
            /* update cooldown each frame */
            tower.updateCooldown();
            for (Slicer slicer : slicers) {
                double distance = tower.getPosition().distanceTo(slicer.position());
                if (distance < tower.getRadius() & !tower.getCooldown()) {
                    /* inside radius */
                    attacks.add(new Attack(slicer, tower));
                    tower.startCooldown();
                }
            }
        }
    }

    /**
     * Updates all the attacks each frame
     * @param attacks list of attacks
     * @param targets list of slicers
     */
    public static void updateAttacks(ArrayList<Attack> attacks, ArrayList<Slicer> targets) {
        reward = 0;
        for(int i = 0; i < attacks.size(); i++) {
            /* true if slicer dead */
            if(attacks.get(i).updateAttack(targets)) {
                reward += attacks.get(i).getReward();
                attacks.remove(i);
                attacks.trimToSize();

            }
        }


    }

    /**
     * Update the attacks bu the airsupport each frame
     * @param airSupports List of airsupports
     * @param bombs the dropped explosivves
     */
    public static void updateAirAttacks(ArrayList<AirSupport> airSupports, ArrayList<Bomb> bombs ) {
        for(AirSupport plane : airSupports) {
            plane.move();
            plane.draw();
            Bomb bomb = plane.drop();
            if(bomb != null) {
                bombs.add(bomb);
            }
        }
    }


    /**
     * Updates the explosive each frame
     * @param bombs the explosives list
     * @param slicers the spawned slicers list
     */
    public static void updateBombs(ArrayList<Bomb> bombs, ArrayList<Slicer> slicers) {

        bombs.removeIf(bomb -> bomb.update(slicers));


    }


    /**
     * Renders status panel and sends the status
     * @param status
     */
    public void renderStatusPanel(String status) {
        statusPanel.renderStatusPanel(status, waveNumber);

    }


    /**
     * Called when S key is pressed not called when wave is running
     */

    public void waveStart() {
        if(!waves.isEmpty()) {
            wave = waves.removeFirst();
            waveRunning = true;
            waveNumber++;
        }
    }

    public boolean isInWave() {
        return waveRunning;
    }


    /**
     * Checks if tower overlaps another tower
     * @param newTower the new tower to be placed
     * @return True if cant be placed
     */
    public boolean checkTowerPosition(Point newTower) {
        for(Tank t: tanks ) {
            if(t.getBoundingBox().intersects(newTower)) {
                return true;
            }
        }
        return false;
    }

    public double getReward() {
        return  reward;
    }

}

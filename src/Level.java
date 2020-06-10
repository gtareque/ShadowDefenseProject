import bagel.map.TiledMap;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Level {
    private static double reward;
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private TiledMap map;
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<Tank> tanks = new ArrayList<>();
    private ArrayList<AirSupport> activeTowers = new ArrayList<>();
    private LinkedList<Waves> waves = new LinkedList<>();
    private Waves wave;
    private boolean status = false;
    private boolean waveRunning = false;
    private ArrayList<Attack> attacks = new ArrayList<>();
    private boolean pause = true;
    StatusPanel statusPanel;
    int waveNumber = 0;
    public Level(TiledMap map) {
        this.map = map;
        statusPanel = new StatusPanel();
    }

    public void renderLevel() {
        map.draw(0,0,0,0, 1024.0,1024.0);
    }

    public void addTowers(Tower t) {
        towers.add(t);
        if(t instanceof Tank) {
            tanks.add((Tank)t);
        } else {
            activeTowers.add((AirSupport) t);
            ((AirSupport) t).setHorizontal((towers.size() - 1) % 2 == 0);
        }
    }

    public void drawTowers() {
        for (Tank tower : tanks) {
            tower.draw();
        }
    }

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
                numSpawn = Integer.parseInt(line.substring(0, line.indexOf(',')));
                line = line.substring(line.indexOf(',') + 1);
                slicerType = line.substring(0, line.indexOf(','));
                int delay = Integer.parseInt(line.substring(line.indexOf(',') + 1));
                Spawn event = new Spawn(numSpawn, delay, slicerType, waves.get(waveIndex - 1), map.getAllPolylines().get(0));
                waves.get(waveIndex - 1).addEvent(event);
            }else {
                int delay = Integer.parseInt(line.substring(line.indexOf(',') + 1));
                Delay event = (new Delay(delay));
                waves.get(waveIndex - 1).addEvent((event));
            }

        }


    }

    public void playLevel() {

            if(waveRunning) {
                if(!wave.isWaveComplete()) {
                    wave.playWaves(statusPanel);
                    ArrayList<Slicer> slicers = wave.getSlicers();
                    setTarget(tanks, slicers, attacks);
                    updateAttacks(attacks, slicers);
                    updateAirAttacks(activeTowers, bombs);
                    updateBombs(bombs, slicers);
                } else {
                    waveRunning = false;
                }
            }

    }

    public boolean getStatus() {
        return status;
    }



    public static void setTarget(ArrayList<Tank> towers, ArrayList<Slicer> slicers, ArrayList<Attack> attacks) {
        for (Tank tower : towers) {
            tower.updateCooldown();
            for (Slicer slicer : slicers) {
                double distance = tower.getPosition().distanceTo(slicer.position());
                if (distance < tower.getRadius() & !tower.getCooldown()) {

                    attacks.add(new Attack(slicer, tower));
                    tower.startCooldown();
                }
            }
        }
    }

    public static void updateAttacks(ArrayList<Attack> attacks, ArrayList<Slicer> targets) {
        reward = 0;
        for(int i = 0; i < attacks.size(); i++) {
            if(attacks.get(i).updateAttack(targets)) {
                reward += attacks.get(i).getReward();
                attacks.remove(i);
                attacks.trimToSize();

            }
        }


    }

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

    public static void updateBombs(ArrayList<Bomb> bombs, ArrayList<Slicer> slicers) {

        bombs.removeIf(bomb -> bomb.update(slicers));


    }
    public double getReward() {
        return  reward;
    }

    public void renderStatusPanel(String status) {
        statusPanel.renderStatusPanel(status, waveNumber);

    }
    public void updateScalar(int value) {
        wave.updateScaler();
        statusPanel.setScaler(value);

    }

    public boolean getPause() {
        return pause;
    }

    public void setPause(boolean value) {
        pause = value;
    }

    public void waveStart() {
        if(!waves.isEmpty()) {
            wave = waves.removeFirst();
            waveRunning = true;
        }
    }

    public boolean isInWave() {
        return waveRunning;
    }



}

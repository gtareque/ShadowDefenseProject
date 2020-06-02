import bagel.map.TiledMap;
import bagel.util.Point;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Level {
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private TiledMap map;
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<ActiveTower> passiveTowers = new ArrayList<>();
    private ArrayList<AirSupport> activeTowers = new ArrayList<>();
    private LinkedList<Waves> waves = new LinkedList<>();
    private Waves wave;
    private boolean status = false;
    private ArrayList<Slicer> slicers = new ArrayList<>();
    private ArrayList<Attack> attacks = new ArrayList<>();
    public Level(TiledMap map) {

        this.map = map;
    }

    public void renderLevel() {
        map.draw(0,0,0,0, 1080.0,1080.0);
    }

    public void addTowers(Tower t) {
        towers.add(t);
        if(t instanceof ActiveTower) {
            passiveTowers.add((ActiveTower)t);
        } else {

            activeTowers.add((AirSupport) t);
            ((AirSupport) t).setHorizontal((towers.size() - 1) % 2 == 0);
        }
    }

    public void drawTowers() {
        for (ActiveTower tower : passiveTowers) {
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
            }

            int delay = Integer.parseInt(line.substring(line.indexOf(',') + 1));
        }
        wave = waves.removeFirst();

    }

    public void playLevel() {

           if(wave.isWaveComplete() && !waves.isEmpty()) {

                wave = waves.removeFirst();
            }
           if(!wave.isWaveComplete()){
               Slicer s = wave.playWaves();

               if (s != null) {

                   slicers.add(s);
                    System.out.println("SLicers size: "+ slicers.size());
               }
           }
           for (int i = 0; i < slicers.size(); i++) {

               /* if slicer hasn't already reached and deleted */

               if (!slicers.get(i).getStatus()) {
                   slicers.get(i).updateSlicer();
               }

               /* if slicer has reached after update */
               if (slicers.get(i).getStatus()) {
                   /* slicer i is nulled when it has reached the end, else causes null pointer exception */
                   slicers.remove(i);
                   slicers.trimToSize();

               }

           }
           setTarget(passiveTowers, slicers, attacks);
           updateAttacks(attacks, slicers);
           updateAirAttacks(activeTowers, bombs);
           updateBombs(bombs, slicers);

    }

    public boolean getStatus() {
        return status;
    }

    public TiledMap getMap() {
        return map;
    }

    public static void setTarget(ArrayList<ActiveTower> towers, ArrayList<Slicer> slicers, ArrayList<Attack> attacks) {
        for (ActiveTower tower : towers) {
            tower.updateCooldown();
            for (Slicer slicer : slicers) {
                double distance = tower.getPosition().distanceTo(slicer.position());
                if (distance < tower.getRadius() & !tower.getCooldown()) {

                    attacks.add(new Attack(slicer, (ActiveTower)tower));
                    tower.startCooldown();
                }
            }
        }
    }

    public static void updateAttacks(ArrayList<Attack> attacks, ArrayList<Slicer> targets) {

        attacks.removeIf(attack -> attack.updateAttack(targets));
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


}

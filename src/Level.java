import bagel.map.TiledMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
    private TiledMap map;
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<Waves> waves = new ArrayList<>();
    public Level(TiledMap map) {

        this.map = map;
    }

    public void renderLevel() {
        map.draw(0,0,0,0, 1080.0,1080.0);
    }

    public void addTowers(Tower t) {
        towers.add(t);
    }

    public void drawTowers() {
        for (Tower tower : towers) {
            tower.draw();
        }
    }

    public void createWaves(FileReader file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
//        20,slicer,1000
        while(sc.hasNextLine()) {
            int numSpawn = 0;
            String slicerType;
            String line = sc.nextLine();
            int waveIndex = Integer.parseInt(line.substring(0, line.indexOf(',')));
            if(waves.isEmpty()) {
                waves.add(new Waves());
            }

            if(waveIndex > waves.size() - 1) {
                System.out.println("wave index" + waveIndex);
                System.out.println(waves.size());
                waves.add(new Waves());
            }

            line = line.substring(line.indexOf(',') + 1);
            System.out.println(line);
            String type = line.substring(0, line.indexOf(','));
            line = line.substring(line.indexOf(',') + 1);
            System.out.println(line);
            if(line.contains(",")) {


                numSpawn = Integer.parseInt(line.substring(0, line.indexOf(',')));
                line = line.substring(line.indexOf(',') + 1);
                slicerType = line.substring(0, line.indexOf(','));
                int delay = Integer.parseInt(line.substring(line.indexOf(',') + 1));
                Spawn event = new Spawn(numSpawn, delay, slicerType, waves.get(waveIndex), map.getAllPolylines().get(0));
                waves.get(waveIndex).addEvent(event);


            }

            int delay = Integer.parseInt(line.substring(line.indexOf(',') + 1));

        }


    }
    public void playLevel() {
        for (Waves wave: waves) {
            wave.updateSlicerPosition(map.getAllPolylines().get(0));

        }

    }



}

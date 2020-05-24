import bagel.map.TiledMap;

import java.util.ArrayList;

public class Level {
    private TiledMap map;
    private ArrayList<Tower> towers = new ArrayList<Tower>();
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
        for(int i = 0; i < towers.size(); i++) {
            towers.get(i).draw();
        }
    }

}

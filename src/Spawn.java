import bagel.util.Point;


import java.util.List;

public class Spawn extends Event  {
    List<Point> polyline;
    private int numSpawn;
    private String type;
    Waves wave;
    private int initDelay;
    public Spawn (int numSpawn, int delay, String type, Waves wave, List<Point> polyline) {
        super(delay);
        initDelay = delay;
        this.numSpawn = numSpawn;
        this.wave = wave;
        this.type = type;
        this.polyline = polyline;

    }

    public void update() {
        if (delay == 0 && numSpawn != 0) {
            delay = initDelay;
            Slicer s = createNew(type, polyline);
            wave.addSlicer((s));
         }
        numSpawn--;
        delay--;


    }

    public static Slicer createNew(String type, List<Point> polylines) {
        if(type.equalsIgnoreCase("slicer")) {
            return new Slicer(polylines);
        }
        return null;
    }





}

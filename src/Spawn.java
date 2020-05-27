import bagel.util.Point;


import java.util.List;

public class Spawn extends Event  {
    List<Point> polyline;
    private int numSpawn;
    private String type;
    Waves wave;
    private int initDelay;
    private boolean status = false;

    public Spawn (int numSpawn, int delay, String type, Waves wave, List<Point> polyline) {

        super( delay/1000 * (60));
        System.out.println("delay is" + delay);
        initDelay = delay/1000 * (60);
        System.out.println("init delay is =" + initDelay);
        this.numSpawn = numSpawn;
        this.wave = wave;
        this.type = type;
        this.polyline = polyline;

    }

    public Slicer update() {
        Slicer s = null;
        if (delay == 0 && numSpawn != 0) {
            delay = initDelay;
            s = createNew(type, polyline);

        }

        numSpawn--;
        delay--;
        if(numSpawn == 0) {

            status = true;
        }
        return s;
    }

    public static Slicer createNew(String type, List<Point> polylines) {

        if(type.equalsIgnoreCase("slicer")) {
            System.out.println("pink");
            return new Slicer(polylines);
        }
        return null;
    }

    public boolean getStatus() {
        return status;
    }



}

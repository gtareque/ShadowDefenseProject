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
        super(delay);
        initDelay = delay;
        this.numSpawn = numSpawn;
        this.wave = wave;
        this.type = type;
        this.polyline = polyline;
        Slicer s = createNew(type, polyline);
        wave.addSlicer((s));
        numSpawn--;
    }

    public void update() {

        if (delay == 0 && numSpawn != 0) {

            delay = initDelay;
            Slicer s = createNew(type, polyline);
            wave.addSlicer((s));
         }
        numSpawn--;
        delay--;
        if(numSpawn == 0){
            status = true;
        }

    }

    public static Slicer createNew(String type, List<Point> polylines) {
        System.out.println("type is" + type);
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

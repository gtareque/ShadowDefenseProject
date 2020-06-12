import bagel.util.Point;
import java.util.List;


public class Spawn extends Event  {
    List<Point> polyline;
    private int numSpawn;
    private String type;
    Waves wave;
    private boolean status = false;
    int frame = 0;

    /**
     * Constructor
     * @param numSpawn number of slicer to be spawned in this event
     * @param delay spawn delay in frames
     * @param type the type of slicer to be spawned
     * @param wave the wave this event is in
     * @param polyline
     */

    public Spawn (int numSpawn, int delay, String type, Waves wave, List<Point> polyline) {
        super( delay *60 / 1000);
        this.numSpawn = numSpawn;
        this.wave = wave;
        this.type = type;
        this.polyline = polyline;
    }

    /**
     * Updates event properties each frame
     * @return spawned slicer or null
     */
    public Slicer update() {
        Slicer s = null;

       if ((frame % (delay/scaler)) == 0 && numSpawn != 0) {
           s = createNew(type, polyline);
           numSpawn--;
       }
        frame++;
        if(numSpawn == 0) {
            status = true;
        }
        return s;
    }

    /**
     * Spawn slicers
     * @param type the type of slicer
     * @param polylines the polyline fo the map
     * @return spawned SLicer
     */

    public static Slicer createNew(String type, List<Point> polylines) {

        if(type.equalsIgnoreCase("slicer")) {
            return new Slicer(polylines);
        } else if(type.equals("superslicer")) {
            return new SuperSlicer(polylines);
        } else if(type.equals("megaslicer")) {
            return new MegaSlicer(polylines);
        } else if(type.equals("apexslicer")) {
            return  new ApexSlicer(polylines);
        } else {
            return  null;
        }

    }

    public boolean getStatus() {
        return status;
    }



}

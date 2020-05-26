/*
 *  Waves class - A wave is complete whenall slicers has reached end
 *  Code Author: G M ASIF TAREQUE - 1004497
 *  Last modification: 7/5/20
 *
 */



/* imports */
import bagel.util.Point;

import java.util.ArrayList;
import java.util.List;

public class Waves {


    private ArrayList<Slicer> slicers = new ArrayList<>();     // Array to store all the created slicers
    private ArrayList<Event> events = new ArrayList<>();
    private boolean waveComplete = false;       // wave status
    private int countTargetReached;     // counts the slicers that have reached end

    /* constructor */





    /**
     *
     * moves the slicer
     * @param polyLines The polyline generated from the map */
    public void updateSlicerPosition(List<Point> polyLines) {
        updateEvents(events);
        for(int i = 0; i < slicers.size(); i++) {
            /* if slicer hasn't already reached and deleted */
            if(!slicers.get(i).getStatus()) {
                slicers.get(i).updateSlicer(polyLines);
            }

            /* if slicer has reached after update */
            if(slicers.get(i).getStatus()) {
                /* slicer i is nulled when it has reached the end, else causes null pointer exception */
                slicers.remove(i);
                slicers.trimToSize();

            }

        }
        /* wave has reached end */
        if(slicers.isEmpty() ) {
            waveComplete = true;
        }
    }

    /**
     *
     * get method for waveComplete */

    public boolean isWaveComplete() {
        return waveComplete;
    }


    /**
     *
     * updates properties of slicers when scale changes
     * @param scaler The timescale multiplier from main */
    public void updateCurrentSlicers(int scaler) {
        Slicer.setScaler(scaler);
        for (Slicer slicer : slicers) {

            slicer.updateSlicer();

        }
    }

    public void addSlicer(Slicer newSlicer) {
        slicers.add(newSlicer);
    }

    public void addEvent(Event e) {
        events.add(e);
    }
    private static void updateEvents(ArrayList<Event> events) {
        for (Event event : events) {
            event.update();
        }
    }



}

/*
 *  Waves class - A wave is complete whenall slicers has reached end
 *  Code Author: G M ASIF TAREQUE - 1004497
 *  Last modification: 7/5/20
 *
 */



/* imports */
import bagel.util.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Waves {


    private ArrayList<Slicer> slicers = new ArrayList<>();     // Array to store all the created slicers
    private ArrayList<Event> events = new ArrayList<>();
    private boolean waveComplete = false;       // wave status
    private int countTargetReached;     // counts the slicers that have reached end
    private Event event;
    Queue<Event> queue = new LinkedList<>();





    /**
     *
     * moves the slicer
     * @param polyLines The polyline generated from the map */
    public void updateSlicerPosition(List<Point> polyLines) {
        /* checks if all wave complete and last wave is complete */
        if(queue.isEmpty() && event.getStatus()) {
            waveComplete = true;
        } else {
            if(event == null && !queue.isEmpty()) {
                event  = queue.remove();
                event.update();
            }else if(event.getStatus() && !queue.isEmpty()) {
                event = queue.remove();
                event.update();
            } else {
                event.update();
            }
        }



        for (int i = 0; i < slicers.size(); i++) {

            /* if slicer hasn't already reached and deleted */

            if (!slicers.get(i).getStatus()) {
                slicers.get(i).updateSlicer(polyLines);
            }

            /* if slicer has reached after update */
            if (slicers.get(i).getStatus()) {
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
        queue.add(e);
        System.out.println(queue.size());
    }




}

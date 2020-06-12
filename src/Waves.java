/*
 *  Waves class - A wave is complete whenall slicers has reached end
 *  Code Author: G M ASIF TAREQUE - 1004497
 *  Last modification: 7/5/20
 *
 */



/* imports */

import java.util.ArrayList;

/**
 * Waves are part of the levels
 * Waves contain events and slicers
 */

public class Waves {


    private boolean waveComplete = false;       // wave status
    int eventIndex = 0;
    ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Slicer> slicers = new ArrayList<>();


    /**
     * update wave each frame
     * @param panel the status panel at the bottom
     * @return True if game over
     */
    public boolean playWaves(StatusPanel panel) {

        Slicer slicer = null;

        if ((eventIndex < events.size())) {
            if (events.get(eventIndex).getStatus()) {
                /* move to next event */
                eventIndex++;
            } else {
                /* slicer can be null */
                slicer = events.get(eventIndex).update();
            }
        }
        if(slicer != null) {
            slicers.add(slicer);
        }

        /* update all slicer */
        if(moveSlicers(slicers, panel)) {
            /* GAME OVER */
            return true;
        }

        /* True if wave complete */
        if(slicers.size() == 0 && eventIndex >= events.size()) {
            waveComplete = true;

        }
        return false;
    }


    /**
     * Update all slicers each frame
     * @param slicers the spawned slicers
     * @param panel the status panel at the bottom
     * @return true if game over and life is below zero
     */
    public static boolean moveSlicers(ArrayList<Slicer> slicers, StatusPanel panel) {
        boolean isOver = false;
        for (int i = 0; i < slicers.size(); i++) {

            /* if slicer hasn't already reached and deleted */

            if (!slicers.get(i).getStatus()) {
                slicers.get(i).updateSlicer();
            }

            /* if slicer has reached after update */
            if (slicers.get(i).getStatus()) {
                /* slicer i is nulled when it has reached the end, else causes null pointer exception */
                isOver = panel.loseLife(slicers.get(i).penalize());
                slicers.remove(i);
                slicers.trimToSize();

            }

        }
        return isOver;
    }



    /**
      *
      * get method for waveComplete
     * */

    public boolean isWaveComplete() {
        return waveComplete;
    }





    public void addEvent(Event e) {
        events.add(e);
    }

    public ArrayList<Slicer> getSlicers() {
        return slicers;
    }


}

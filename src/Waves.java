/*
 *  Waves class - A wave is complete whenall slicers has reached end
 *  Code Author: G M ASIF TAREQUE - 1004497
 *  Last modification: 7/5/20
 *
 */



/* imports */

import java.util.ArrayList;

public class Waves {


         // Array to store all the created slicers

    private boolean waveComplete = false;       // wave status
    int eventIndex = 0;
    ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Slicer> slicers = new ArrayList<>();


    public boolean playWaves(StatusPanel panel) {

        Slicer s = null;
        if ((eventIndex < events.size())) {
            if (events.get(eventIndex).getStatus()) {
                eventIndex++;
            } else {
                s = events.get(eventIndex).update();
            }
        }
        if(s != null) {
            slicers.add(s);
        }
        if(moveSlicers(slicers, panel)) {
            return true;
        }

        if(slicers.size() == 0 && eventIndex >= events.size()) {
            waveComplete = true;

        }
        return false;
    }


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
     * get method for waveComplete */

    public boolean isWaveComplete() {
        return waveComplete;
    }



    /**
     *
     * updates properties of slicers when scale changes
     * @param scaler The timescale multiplier from main */


    public void addEvent(Event e) {
        events.add(e);
    }

    public ArrayList<Slicer> getSlicers() {
        return slicers;
    }


}

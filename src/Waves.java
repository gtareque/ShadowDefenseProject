/*
 *  Waves class - A wave is complete whenall slicers has reached end
 *  Code Author: G M ASIF TAREQUE - 1004497
 *  Last modification: 7/5/20
 *
 */



/* imports */
import bagel.util.Point;

import java.util.ArrayList;

public class Waves {


         // Array to store all the created slicers

    private boolean waveComplete = false;       // wave status
    private int countTargetReached;     // counts the slicers that have reached end
    int eventIndex = 0;
    ArrayList<Event> events = new ArrayList<>();




    public Slicer playWaves() {
        Slicer s = null;

        if ((eventIndex >= events.size())) {
            waveComplete = true;
        } else {
            if (events.get(eventIndex).getStatus()) {
                eventIndex++;
            } else {
                
                s = events.get(eventIndex).update();
            }
        }

        return s;



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




}

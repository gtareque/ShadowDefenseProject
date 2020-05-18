/*
 *  Waves class - A wave is complete whenall slicers has reached end
 *  Code Author: G M ASIF TAREQUE - 1004497
 *  Last modification: 7/5/20
 *
 */



/* imports */
import bagel.util.Point;
import java.util.List;

public class Waves {

    private final static int MAX_SLICERS = 5;
    private Slicer[] slicers = new Slicer[MAX_SLICERS];     // Array to store all the created slicers
    private int slicerCount = 0;        // keeps count of the slicers created
    private boolean waveComplete;       // wave status
    private int countTargetReached;     // counts the slicers that have reached end

    /* constructor */
    public Waves() {

        waveComplete = false;
    }

    /**
     *
     * create and stroes new slicer in the current wave
     * @param polyLines The polyline generated from the map */
    public void createNewSlicer(List<Point> polyLines) {

        slicers[slicerCount++] = new Slicer(polyLines);
    }


    /**
     *
     * moves the slicer
     * @param polyLines The polyline generated from the map */
    public void updateSlicerPosition(List<Point> polyLines) {

        for (int i = 0; i < slicerCount; i++) {
            /* if slicer hasn't already reached and deleted */
            if(slicers[i] != null && !slicers[i].getStatus()) {
                slicers[i].updateSlicer(polyLines);
            }

            /* if slicer has reached and not nulled yet */
            if(slicers[i] != null && slicers[i].getStatus()) {
                /* slicer i is nulled when it has reached the end, else causes null pointer exception */
                slicers[i] =null;
                countTargetReached++;
            }

        }
        /* wave has reached end */
        if(countTargetReached == MAX_SLICERS ) {
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
        for(int i = 0; i < slicerCount; i++) {
            if(slicers[i] != null) {
                slicers[i].updateSlicer();
            }
        }
    }



}

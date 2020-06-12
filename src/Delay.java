
public class Delay extends Event {
    /**
     * Delay is the Event where nothing is spawned
     * Belongs to a particular wave
     * Class has function to update event at each frame and inherits from Event
     */

    private boolean status = false;     // status true if event complete




    public Delay(int delay) {
        super( delay * 60 / 1000);

    }


    /**
     * Function updates the event at each frame
     * @return all events return Slicer, null cause this is delay event
     */
    @Override
    public Slicer update() {
        delay = delay - (scaler);
        if(delay <= 0) {
            status = true;
        }
        return null;
    }


    @Override
    public boolean getStatus() {
        return status;
    }
}

public abstract class Event {

    /**
     * Event is of type delay and spwan contained inside wave
     * Has children Spawn and Delay class
     */
    protected static int scaler = 1;
    protected int delay;
    public abstract Slicer update();

    public Event(int delay) {
        this.delay = delay;
    }
    public abstract boolean getStatus();


    public static void setScaler(int scaler) {
        Event.scaler = scaler;
    }



}

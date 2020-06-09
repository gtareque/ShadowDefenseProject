public abstract class Event {
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

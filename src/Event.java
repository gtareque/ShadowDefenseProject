public abstract class Event {

    protected int delay;
    public abstract Slicer update();



    public Event(int delay) {
        this.delay = delay;
    }

    public abstract boolean getStatus();




}

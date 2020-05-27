public abstract class Event {

    protected int delay;
    private boolean isOver = false;
    public abstract Slicer update();



    public Event(int delay) {
        this.delay = delay;
    }

    public abstract boolean getStatus();



    public void setIsOver() {
        isOver = true;
    }
}

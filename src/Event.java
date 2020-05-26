public abstract class Event {
    protected int delay;
    private boolean isOver = false;
    public abstract void update();
    public Event(int delay) {
        this.delay = delay;
    }





    public void setIsOver() {
        isOver = true;
    }
}

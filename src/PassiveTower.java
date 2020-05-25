public abstract class PassiveTower extends Tower {
    private Slicer target;
    public void setTarget(Slicer target) {
        this.target = target;
    }

    public Slicer getTarget() {
        return target;
    }
}

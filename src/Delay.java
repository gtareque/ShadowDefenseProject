public class Delay extends Event {
    private boolean status = false;
    int frame = 0;
    public Delay(int delay) {
        super( delay *60 / 1000);
    }

    @Override
    public Slicer update() {
        delay = (delay/scaler) - 1;
        if(delay == 0) {
            status = true;
        }
        return null;
    }

    @Override
    public boolean getStatus() {
        return status;
    }
}

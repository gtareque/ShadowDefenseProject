import java.util.ArrayList;

/**
 * interface only implemented by Slicers that can be respawned
 */
public interface Respwanable {
    public void respawn(ArrayList<Slicer> array);
    public int penalize();
}

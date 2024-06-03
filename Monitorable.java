/**
 * Simple counter type that is intended to be sub classed by hash table implementations
 * to support monitoring of the number of probes that occur during searches.
 *
 * @author Stephan Jamieson
 * @version 24/4/2023
 */
public class Monitorable {

    // Number of probes of the hash table that have occurred up to this point.
    private int probe_count;

    /**
     * Obtain the number of probes of the hash table structure that have occurred since start-up or the
     * last <code>resetProbeCount()</code>.
     */
    public int getProbeCount() { return probe_count; }

    /**
     * Increment the probe count.
     */
    public void incProbeCount() { probe_count++; }

    /**
     * Reset the probe counter to zero.
     */
    public void resetProbeCount() { probe_count=0; }
}

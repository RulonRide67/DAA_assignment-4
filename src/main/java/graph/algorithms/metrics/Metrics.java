package graph.algorithms.metrics;

import java.util.HashMap;
import java.util.Map;

public class Metrics {
    private long start;
    private long end;
    private final Map<String, Long> counters = new HashMap<>();

    public void startTimer() { start = System.nanoTime(); }
    public void stopTimer() { end = System.nanoTime(); }
    public long elapsedNano() { return end - start; }

    public void inc(String name) {
        counters.put(name, counters.getOrDefault(name, 0L) + 1);
    }

    public long get(String name) { return counters.getOrDefault(name, 0L); }
}

package graph.algorithms;

import graph.algorithms.core.Graph;
import graph.algorithms.util.JsonLoader;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;

public class GraphAlgorithmsTest {

    @Test
    void testSmallGraphs() throws Exception {
        var graphs = JsonLoader.loadArray(Path.of("data/small.json"));
        for (int i = 0; i < graphs.size(); i++) {
            Graph g = graphs.get(i);
            System.out.println("Small graph " + (i + 1) + ": " + g.getVertices() + " vertices");
        }
    }

    @Test
    void testMediumGraphs() throws Exception {
        var graphs = JsonLoader.loadArray(Path.of("data/medium.json"));
        for (int i = 0; i < graphs.size(); i++) {
            Graph g = graphs.get(i);
            System.out.println("Medium graph " + (i + 1) + ": " + g.getVertices() + " vertices");
        }
    }

    @Test
    void testLargeGraphs() throws Exception {
        var graphs = JsonLoader.loadArray(Path.of("data/large.json"));
        for (int i = 0; i < graphs.size(); i++) {
            Graph g = graphs.get(i);
            System.out.println("Large graph " + (i + 1) + ": " + g.getVertices() + " vertices");
        }
    }
}

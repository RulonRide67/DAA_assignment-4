package graph.algorithms;

import graph.algorithms.core.Graph;
import graph.algorithms.topo.TopologicalSort;
import graph.algorithms.util.JsonLoader;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.*;

public class TopoSortTest {

    private static List<List<Integer>> toAdj(Graph g) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < g.getVertices(); i++) adj.add(new ArrayList<>());
        for (int[] e : g.getEdges()) adj.get(e[0]).add(e[1]);
        return adj;
    }

    @Test
    void testTopologicalSort() throws Exception {
        List<Graph> allGraphs = new ArrayList<>();

        allGraphs.addAll(JsonLoader.loadArray(Path.of("data/small.json")));
        allGraphs.addAll(JsonLoader.loadArray(Path.of("data/medium.json")));
        allGraphs.addAll(JsonLoader.loadArray(Path.of("data/large.json")));

        for (int i = 0; i < allGraphs.size(); i++) {
            Graph g = allGraphs.get(i);
            var adj = toAdj(g);
            var order = TopologicalSort.sort(g.getVertices(), adj);
            System.out.println("Graph " + (i + 1) + " → Topo order: " + order);
        }
    }

}

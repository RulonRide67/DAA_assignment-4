package graph.algorithms;

import graph.algorithms.core.Graph;
import graph.algorithms.scc.TarjanSCC;
import graph.algorithms.util.JsonLoader;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.*;

public class SCCUnitTest {

    private static List<List<Integer>> toAdj(Graph g) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < g.getVertices(); i++) adj.add(new ArrayList<>());
        for (int[] e : g.getEdges()) adj.get(e[0]).add(e[1]);
        return adj;
    }

    @Test
    void testSCCOnSmallGraphs() throws Exception {
        var graphs = JsonLoader.loadArray(Path.of("data/small.json"));
        for (int i = 0; i < graphs.size(); i++) {
            Graph g = graphs.get(i);
            TarjanSCC scc = new TarjanSCC(g.getVertices(), toAdj(g));
            var result = scc.getSCCs();
            System.out.println("Graph " + (i + 1) + " → SCCs: " + result.size());
        }
    }
}

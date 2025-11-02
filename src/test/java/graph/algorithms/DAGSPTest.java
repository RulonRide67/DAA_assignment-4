package graph.algorithms;

import graph.algorithms.core.Graph;
import graph.algorithms.dagsp.DAGShortestPath;
import graph.algorithms.dagsp.DAGShortestPath.Edge;
import graph.algorithms.topo.TopologicalSort;
import graph.algorithms.util.JsonLoader;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.*;

public class DAGSPTest {

    private static List<List<Edge>> toWeightedAdj(Graph g) {
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < g.getVertices(); i++) adj.add(new ArrayList<>());
        for (int[] e : g.getEdges()) {
            adj.get(e[0]).add(new Edge(e[1], e[2]));
        }
        return adj;
    }

    @Test
    void testDAGShortestPath() throws Exception {
        var graphs = JsonLoader.loadArray(Path.of("data/large.json"));
        for (int i = 0; i < graphs.size(); i++) {
            Graph g = graphs.get(i);
            List<List<Edge>> adj = toWeightedAdj(g);

            List<Integer> order = TopologicalSort.sort(g.getVertices(), toSimpleAdj(g));

            double[] dist = DAGShortestPath.shortestPath(g.getVertices(), adj, 0, order);

            System.out.println("Graph " + (i + 1) + " → shortest paths: " + Arrays.toString(dist));
        }
    }

    private static List<List<Integer>> toSimpleAdj(Graph g) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < g.getVertices(); i++) adj.add(new ArrayList<>());
        for (int[] e : g.getEdges()) adj.get(e[0]).add(e[1]);
        return adj;
    }
}

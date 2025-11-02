package graph;

import graph.dagsp.DAGShortestPath;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class DAGSPTest {

    @Test
    public void testShortestAndLongest() {
        int n = 6;
        List<List<DAGShortestPath.Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        adj.get(0).add(new DAGShortestPath.Edge(1, 5));
        adj.get(0).add(new DAGShortestPath.Edge(2, 3));
        adj.get(1).add(new DAGShortestPath.Edge(3, 6));
        adj.get(1).add(new DAGShortestPath.Edge(2, 2));
        adj.get(2).add(new DAGShortestPath.Edge(4, 4));
        adj.get(2).add(new DAGShortestPath.Edge(5, 2));
        adj.get(2).add(new DAGShortestPath.Edge(3, 7));
        adj.get(3).add(new DAGShortestPath.Edge(4, -1));
        adj.get(4).add(new DAGShortestPath.Edge(5, -2));

        List<Integer> topo = List.of(0, 1, 2, 3, 4, 5);
        double[] shortest = DAGShortestPath.shortestPath(n, adj, 1, topo);
        double[] longest = DAGShortestPath.longestPath(n, adj, 1, topo);

        assertEquals(0.0, shortest[1]);
        assertTrue(longest[5] > shortest[5]);
        System.out.println("Shortest: " + Arrays.toString(shortest));
        System.out.println("Longest: " + Arrays.toString(longest));
    }
}

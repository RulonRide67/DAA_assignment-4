package graph.algorithms.dagsp;

import java.util.*;

public class DAGShortestPath {

    public static class Edge {
        public final int v;
        public final int w;
        public Edge(int v, int w) { this.v = v; this.w = w; }
    }

    public static double[] shortestPath(int n, List<List<Edge>> adj, int src, List<Integer> topo) {
        double[] dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[src] = 0;

        for (int u : topo) {
            if (dist[u] != Double.POSITIVE_INFINITY) {
                for (Edge e : adj.get(u)) {
                    if (dist[e.v] > dist[u] + e.w)
                        dist[e.v] = dist[u] + e.w;
                }
            }
        }
        return dist;
    }

    public static double[] longestPath(int n, List<List<Edge>> adj, int src, List<Integer> topo) {
        double[] dist = new double[n];
        Arrays.fill(dist, Double.NEGATIVE_INFINITY);
        dist[src] = 0;

        for (int u : topo) {
            if (dist[u] != Double.NEGATIVE_INFINITY) {
                for (Edge e : adj.get(u)) {
                    if (dist[e.v] < dist[u] + e.w)
                        dist[e.v] = dist[u] + e.w;
                }
            }
        }
        return dist;
    }
}

package graph.algorithms.core;

import com.google.gson.JsonObject;
import java.util.*;

public class Graph {
    private int n;
    private boolean directed;
    private List<int[]> edges = new ArrayList<>();

    public Graph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
    }

    public int getVertices() {
        return n;
    }

    public boolean isDirected() {
        return directed;
    }

    public List<int[]> getEdges() {
        return edges;
    }

    public static Graph fromJson(JsonObject obj) {
        boolean directed = obj.get("directed").getAsBoolean();
        int n = obj.get("n").getAsInt();
        Graph g = new Graph(n, directed);

        for (var e : obj.getAsJsonArray("edges")) {
            JsonObject edge = e.getAsJsonObject();
            int u = edge.get("u").getAsInt();
            int v = edge.get("v").getAsInt();
            int w = edge.get("w").getAsInt();
            g.edges.add(new int[]{u, v, w});
        }
        return g;
    }
}

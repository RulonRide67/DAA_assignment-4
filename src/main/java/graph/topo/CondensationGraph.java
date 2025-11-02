package graph.topo;

import java.util.*;

public class CondensationGraph {
    public static List<List<Integer>> build(List<List<Integer>> sccs, List<List<Integer>> adj, int n) {
        int[] comp = new int[n];
        for (int i = 0; i < sccs.size(); i++) {
            for (int v : sccs.get(i)) comp[v] = i;
        }

        List<Set<Integer>> dagSet = new ArrayList<>();
        for (int i = 0; i < sccs.size(); i++) dagSet.add(new HashSet<>());

        for (int u = 0; u < n; u++) {
            for (int v : adj.get(u)) {
                int cu = comp[u], cv = comp[v];
                if (cu != cv) dagSet.get(cu).add(cv);
            }
        }

        List<List<Integer>> dag = new ArrayList<>();
        for (var s : dagSet) dag.add(new ArrayList<>(s));
        return dag;
    }
}

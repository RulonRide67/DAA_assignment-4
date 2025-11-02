package graph.algorithms.scc;

import java.util.*;

public class TarjanSCC {
    private final int n;
    private final List<List<Integer>> adj;
    private int time = 0;
    private final int[] disc;
    private final int[] low;
    private final boolean[] stackMember;
    private final Deque<Integer> stack = new ArrayDeque<>();

    public TarjanSCC(int n, List<List<Integer>> adj) {
        this.n = n;
        this.adj = adj;
        disc = new int[n];
        low = new int[n];
        stackMember = new boolean[n];
        Arrays.fill(disc, -1);
    }

    public List<List<Integer>> getSCCs() {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) dfs(i, res);
        }
        return res;
    }

    private void dfs(int u, List<List<Integer>> res) {
        disc[u] = low[u] = ++time;
        stack.push(u);
        stackMember[u] = true;

        for (int v : adj.get(u)) {
            if (disc[v] == -1) {
                dfs(v, res);
                low[u] = Math.min(low[u], low[v]);
            } else if (stackMember[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (low[u] == disc[u]) {
            List<Integer> scc = new ArrayList<>();
            int w;
            do {
                w = stack.pop();
                stackMember[w] = false;
                scc.add(w);
            } while (w != u);
            res.add(scc);
        }
    }
}

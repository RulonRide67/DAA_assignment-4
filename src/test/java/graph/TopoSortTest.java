package graph;

import graph.topo.*;
import graph.scc.TarjanSCC;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class TopoSortTest {
    @Test
    public void testTopoOrder() {
        int n = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);

        TarjanSCC scc = new TarjanSCC(n, adj);
        var comps = scc.getSCCs();
        var dag = CondensationGraph.build(comps, adj, n);
        var order = TopologicalSort.kahn(dag.size(), dag);

        assertEquals(dag.size(), order.size());
        System.out.println("Topo order: " + order);
    }
}

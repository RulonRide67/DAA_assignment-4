package graph.algorithms;

import graph.algorithms.scc.TarjanSCC;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class SCCUnitTest {
    @Test
    public void testSimpleSCC() {
        int n = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(0);
        adj.get(2).add(3);

        TarjanSCC t = new TarjanSCC(n, adj);
        var sccs = t.getSCCs();
        assertTrue(sccs.size() > 0);
        System.out.println("SCC count: " + sccs.size());
        for (var s : sccs) System.out.println(s);
    }
}

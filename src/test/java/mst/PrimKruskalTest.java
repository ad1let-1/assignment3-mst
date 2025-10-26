package mst;

import mst.algorithms.Kruskal;
import mst.algorithms.Prim;
import mst.model.Edge;
import mst.model.Graph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrimKruskalTest {

    private Graph sample() {
        List<String> nodes = List.of("A", "B", "C", "D");
        List<Edge> edges = List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 4),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("B", "D", 5)
        );
        return new Graph(nodes, edges);
    }

    @Test
    void sameCostAndSize() {
        Graph g = sample();

        var primMetrics = new Prim().run(g);
        var kruskalMetrics = new Kruskal().run(g);

        assertEquals(primMetrics.cost, kruskalMetrics.cost, "MST costs must be equal");

        assertEquals(g.V() - 1, primMetrics.mst.size(), "Prim MST edges must be V-1");
        assertEquals(g.V() - 1, kruskalMetrics.mst.size(), "Kruskal MST edges must be V-1");

        assertTrue(primMetrics.ms >= 0);
        assertTrue(kruskalMetrics.ms >= 0);
        assertTrue(primMetrics.ops >= 0);
        assertTrue(kruskalMetrics.ops >= 0);
    }

    @Test
    void disconnectedGraphHandled() {
        Graph g = new Graph(
                List.of("A", "B", "C"),
                List.of(new Edge("A", "B", 1)) // C изолирована
        );

        var primMetrics = new Prim().run(g);
        var kruskalMetrics = new Kruskal().run(g);

        assertEquals(0, primMetrics.mst.size());
        assertEquals(0, kruskalMetrics.mst.size());
        assertEquals(0, primMetrics.cost);
        assertEquals(0, kruskalMetrics.cost);
    }
}


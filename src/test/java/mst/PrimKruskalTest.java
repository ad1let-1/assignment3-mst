package mst;

import mst.alg.*;
import mst.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimKruskalTest {
    @Test
    void testAlgorithms() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(1, 3, 15);
        g.addEdge(2, 3, 4);

        Result prim = Prim.run(g);
        Result kruskal = Kruskal.run(g);

        assertEquals(kruskal.totalWeight, prim.totalWeight);
    }
}
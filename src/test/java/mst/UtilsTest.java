package mst;

import mst.model.Edge;
import mst.model.Graph;
import mst.util.DisjointSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    void disjointSetBasic() {
        DisjointSet<String> dsu = new DisjointSet<>();
        dsu.makeSet("A");
        dsu.makeSet("B");
        dsu.makeSet("C");

        assertTrue(dsu.union("A", "B"), "Union of A-B should succeed");
        assertFalse(dsu.union("A", "B"), "Repeated union should fail");

        assertNotEquals(dsu.find("A"), dsu.find("C"), "A and C should be in different sets");

        assertTrue(dsu.union("B", "C"), "Union of B-C should succeed");
        assertEquals(dsu.find("A"), dsu.find("C"), "After union, A and C should be in same set");
    }

    @Test
    void graphConnectivity() {
        // Связный граф
        Graph connected = new Graph(
                List.of("A", "B", "C"),
                List.of(new Edge("A", "B", 1), new Edge("B", "C", 1))
        );
        assertTrue(connected.isConnected(), "Graph should be connected");

        // Несвязный граф
        Graph disconnected = new Graph(
                List.of("A", "B", "C"),
                List.of(new Edge("A", "B", 1)) // C изолирована
        );
        assertFalse(disconnected.isConnected(), "Graph should be disconnected");
    }
}

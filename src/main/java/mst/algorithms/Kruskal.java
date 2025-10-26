package mst.algorithms;

import mst.model.Edge;
import mst.model.Graph;
import mst.util.DisjointSet;

import java.util.*;

public class Kruskal {
    public static class Metrics {
        public List<Edge> mst = new ArrayList<>();
        public int cost = 0;
        public long ops = 0;
        public double ms = 0;
    }

    public Metrics run(Graph g) {
        long t0 = System.nanoTime();
        Metrics m = new Metrics();

        if (g.V() == 0) { m.ms = 0; return m; }
        if (!g.isConnected()) { m.ms = 0; return m; }

        List<Edge> sorted = new ArrayList<>(g.edges());
        Collections.sort(sorted);

        DisjointSet<String> dsu = new DisjointSet<>();
        for (String v : g.nodes()) dsu.makeSet(v);

        for (Edge e : sorted) {
            if (dsu.union(e.from, e.to)) {
                m.mst.add(e);
                m.cost += e.weight;
            }
        }
        m.ops = dsu.ops + sorted.size();

        long t1 = System.nanoTime();
        m.ms = (t1 - t0) / 1_000_000.0;
        return m;
    }
}


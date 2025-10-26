package mst.algorithms;

import mst.model.Edge;
import mst.model.Graph;

import java.util.*;

public class Prim {
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

        Set<String> in = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        String start = g.nodes().get(0);
        in.add(start);
        pq.addAll(g.adj().get(start));
        m.ops += g.adj().get(start).size();

        while (in.size() < g.V() && !pq.isEmpty()) {
            Edge e = pq.poll(); m.ops++;
            if (in.contains(e.to) && in.contains(e.from)) continue;
            String next = in.contains(e.from) ? e.to : e.from;
            if (in.contains(next)) continue;

            in.add(next);
            m.mst.add(e);
            m.cost += e.weight;

            for (Edge ne : g.adj().get(next)) { pq.add(ne); m.ops++; }
        }

        long t1 = System.nanoTime();
        m.ms = (t1 - t0) / 1_000_000.0;
        return m;
    }
}

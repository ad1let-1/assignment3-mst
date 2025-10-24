package mst.algorithms;

import mst.model.*;
import mst.util.DisjointSet;
import java.util.*;

public class Kruskal {
    public static Result run(Graph graph) {
        long start = System.currentTimeMillis();

        List<Edge> mst = new ArrayList<>();
        DisjointSet ds = new DisjointSet();

        for (int i = 0; i < graph.vertices; i++) ds.makeSet(i);
        Collections.sort(graph.edges);

        for (Edge edge : graph.edges) {
            int root1 = ds.find(edge.src);
            int root2 = ds.find(edge.dest);

            if (root1 != root2) {
                mst.add(edge);
                ds.union(root1, root2);
            }
        }

        int totalWeight = mst.stream().mapToInt(e -> e.weight).sum();
        long end = System.currentTimeMillis();

        return new Result("Kruskal", totalWeight, end - start, mst);
    }
}
package mst.algorithms;

import mst.model.*;
import java.util.*;

public class Prim {
    public static Result run(Graph graph) {
        long start = System.currentTimeMillis();

        boolean[] inMST = new boolean[graph.vertices];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        List<Edge> mst = new ArrayList<>();

        inMST[0] = true;
        for (Edge e : graph.edges)
            if (e.src == 0 || e.dest == 0) pq.add(e);

        while (!pq.isEmpty() && mst.size() < graph.vertices - 1) {
            Edge edge = pq.poll();
            if (inMST[edge.src] && inMST[edge.dest]) continue;

            mst.add(edge);
            int next = inMST[edge.src] ? edge.dest : edge.src;
            inMST[next] = true;

            for (Edge e : graph.edges) {
                if ((e.src == next && !inMST[e.dest]) || (e.dest == next && !inMST[e.src])) {
                    pq.add(e);
                }
            }
        }

        int totalWeight = mst.stream().mapToInt(e -> e.weight).sum();
        long end = System.currentTimeMillis();

        return new Result("Prim", totalWeight, end - start, mst);
    }
}
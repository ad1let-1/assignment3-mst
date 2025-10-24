package mst;

import mst.alg.*;
import mst.model.*;
import mst.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Graph g = IOUtils.readGraph("data/input_small.json", 6);

        Result prim = Prim.run(g);
        Result kruskal = Kruskal.run(g);

        System.out.println("Prim weight: " + prim.totalWeight + ", time: " + prim.executionTimeMs + " ms");
        System.out.println("Kruskal weight: " + kruskal.totalWeight + ", time: " + kruskal.executionTimeMs + " ms");

        IOUtils.writeResult("results/output.json", new Result[]{prim, kruskal});
    }
}
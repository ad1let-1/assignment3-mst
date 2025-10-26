package mst.util;

import mst.algorithms.Kruskal;
import mst.algorithms.Prim;
import mst.model.Result;
import mst.model.Graph;

import java.util.*;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = args.length > 0 ? args[0] : "data/input_small.json";
        String outJson = "results/output.json";
        String outCsv  = "results/summary.csv";

        var inputs = IOUtils.readInput(input);
        List<Result> results = new ArrayList<>();

        for (var ig : inputs) {
            Graph g = IOUtils.toGraph(ig);

            Result r = new Result();
            r.graph_id = ig.id;
            r.input_stats.vertices = g.V();
            r.input_stats.edges = g.E();

            var pm = new Prim().run(g);
            r.prim.mst_edges = pm.mst;
            r.prim.total_cost = pm.cost;
            r.prim.operations_count = pm.ops;
            r.prim.execution_time_ms = pm.ms;

            var km = new Kruskal().run(g);
            r.kruskal.mst_edges = km.mst;
            r.kruskal.total_cost = km.cost;
            r.kruskal.operations_count = km.ops;
            r.kruskal.execution_time_ms = km.ms;

            results.add(r);
        }

        IOUtils.writeOutputJson(outJson, results);
        IOUtils.writeSummaryCsv(outCsv, results);
        System.out.printf("OK -> %s, %s%n",
                Path.of(outJson).toAbsolutePath(), Path.of(outCsv).toAbsolutePath());
    }
}


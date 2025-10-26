package mst.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import mst.model.Edge;
import mst.model.Graph;
import mst.model.Result;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class IOUtils {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static class InputGraph {
        public int id;
        public List<String> nodes;
        public List<Map<String, Object>> edges;
    }
    public static class InputRoot { public List<InputGraph> graphs; }

    public static List<InputGraph> readInput(String path) throws IOException {
        try (Reader r = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8)) {
            Type t = new TypeToken<InputRoot>(){}.getType();
            InputRoot root = gson.fromJson(r, t);
            return root.graphs;
        }
    }

    public static Graph toGraph(InputGraph ig) {
        List<Edge> edges = new ArrayList<>();
        for (Map<String, Object> e : ig.edges) {
            edges.add(new Edge(
                    (String)e.get("from"),
                    (String)e.get("to"),
                    ((Number)e.get("weight")).intValue()
            ));
        }
        return new Graph(ig.nodes, edges);
    }

    public static void writeOutputJson(String path, List<Result> results) throws IOException {
        Map<String, Object> root = new LinkedHashMap<>();
        root.put("results", results);
        String json = gson.toJson(root);
        Files.createDirectories(Path.of(path).getParent());
        Files.writeString(Path.of(path), json, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void writeSummaryCsv(String path, List<Result> results) throws IOException {
        Files.createDirectories(Path.of(path).getParent());
        try (BufferedWriter w = Files.newBufferedWriter(Path.of(path), StandardCharsets.UTF_8)) {
            w.write("graph_id,vertices,edges,prim_cost,kruskal_cost,prim_ms,kruskal_ms,prim_ops,kruskal_ops");
            w.newLine();
            for (Result r : results) {
                w.write(String.format(Locale.US, "%d,%d,%d,%d,%d,%.3f,%.3f,%d,%d",
                        r.graph_id, r.input_stats.vertices, r.input_stats.edges,
                        r.prim.total_cost, r.kruskal.total_cost,
                        r.prim.execution_time_ms, r.kruskal.execution_time_ms,
                        r.prim.operations_count, r.kruskal.operations_count));
                w.newLine();
            }
        }
    }
}


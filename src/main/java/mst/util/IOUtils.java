package mst.util;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mst.model.Graph;
import mst.model.Edge;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class IOUtils {
    public static Graph readGraph(String path, int vertices) throws Exception {
        Gson gson = new Gson();
        Type edgeListType = new TypeToken<List<Edge>>(){}.getType();
        List<Edge> edges = gson.fromJson(new FileReader(path), edgeListType);
        Graph graph = new Graph(vertices);
        for (Edge e : edges) graph.addEdge(e.src, e.dest, e.weight);
        return graph;
    }

    public static void writeResult(String path, Object result) throws Exception {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(result, writer);
        }
    }
}
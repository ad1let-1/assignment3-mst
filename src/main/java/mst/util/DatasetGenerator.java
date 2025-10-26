package mst.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class DatasetGenerator {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void generate(String outPath, int graphs, int minV, int maxV, double density, long seed) throws IOException {
        Random rnd = new Random(seed);
        Map<String, Object> root = new LinkedHashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        for (int gid = 1; gid <= graphs; gid++) {
            int v = rnd.nextInt(maxV - minV + 1) + minV;
            List<String> nodes = new ArrayList<>();
            for (int i = 0; i < v; i++) nodes.add(String.valueOf((char)('A' + i)));
            List<Map<String, Object>> edges = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                for (int j = i + 1; j < v; j++) {
                    if (rnd.nextDouble() <= density) {
                        int w = rnd.nextInt(9) + 1;
                        edges.add(Map.of("from", nodes.get(i), "to", nodes.get(j), "weight", w));
                    }
                }
            }
            list.add(Map.of("id", gid, "nodes", nodes, "edges", edges));
        }
        root.put("graphs", list);
        Files.createDirectories(Path.of(outPath).getParent());
        Files.writeString(Path.of(outPath), gson.toJson(root));
    }
}


package mst.model;

import java.util.List;

public class Result {
    public static class AlgoMetrics {
        public List<Edge> mst_edges;
        public int total_cost;
        public long operations_count;
        public double execution_time_ms;
    }
    public static class GraphStats {
        public int vertices;
        public int edges;
    }

    public int graph_id;
    public GraphStats input_stats = new GraphStats();
    public AlgoMetrics prim = new AlgoMetrics();
    public AlgoMetrics kruskal = new AlgoMetrics();
}

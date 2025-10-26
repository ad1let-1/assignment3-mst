package mst.model;

import java.util.*;

public class Graph {
    private final List<String> nodes;
    private final List<Edge> edges;
    private final Map<String, List<Edge>> adj;

    public Graph(List<String> nodes, List<Edge> edges) {
        this.nodes = List.copyOf(nodes);
        this.edges = List.copyOf(edges);
        this.adj = new HashMap<>();
        for (String v : nodes) adj.put(v, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.from).add(e);
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight)); // зеркальная
        }
    }

    public List<String> nodes() { return nodes; }
    public List<Edge> edges() { return edges; }
    public Map<String, List<Edge>> adj() { return adj; }

    public int V() { return nodes.size(); }
    public int E() { return edges.size(); }

    public boolean isConnected() {
        if (nodes.isEmpty()) return true;
        Set<String> seen = new HashSet<>();
        Deque<String> dq = new ArrayDeque<>();
        dq.add(nodes.get(0));
        while (!dq.isEmpty()) {
            String v = dq.removeFirst();
            if (!seen.add(v)) continue;
            for (Edge e : adj.get(v)) dq.add(e.to);
        }
        return seen.size() == nodes.size();
    }
}


package mst.model;

import java.util.Objects;

public class Edge implements Comparable<Edge> {
    public final String from;
    public final String to;
    public final int weight;

    public Edge(String from, String to, int weight) {
        if (from.equals(to)) throw new IllegalArgumentException("Self-loop not allowed");
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override public int compareTo(Edge o) { return Integer.compare(this.weight, o.weight); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge e = (Edge) o;
        return weight == e.weight &&
                ((from.equals(e.from) && to.equals(e.to)) || (from.equals(e.to) && to.equals(e.from)));
    }

    @Override public int hashCode() {
        String a = from.compareTo(to) <= 0 ? from : to;
        String b = from.compareTo(to) <= 0 ? to : from;
        return Objects.hash(a, b, weight);
    }

    @Override public String toString() { return "(" + from + "-" + to + ":" + weight + ")"; }
}

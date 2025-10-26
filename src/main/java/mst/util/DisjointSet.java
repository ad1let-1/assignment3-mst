package mst.util;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<T> {
    private final Map<T, T> parent = new HashMap<>();
    private final Map<T, Integer> rank = new HashMap<>();
    public long ops = 0;

    public void makeSet(T x) {
        parent.put(x, x);
        rank.put(x, 0);
    }

    public T find(T x) {
        ops++;
        T p = parent.get(x);
        if (p.equals(x)) return x;
        T r = find(p);
        parent.put(x, r);
        return r;
    }

    public boolean union(T a, T b) {
        ops++;
        T ra = find(a), rb = find(b);
        if (ra.equals(rb)) return false;
        int rA = rank.get(ra), rB = rank.get(rb);
        if (rA < rB) parent.put(ra, rb);
        else if (rA > rB) parent.put(rb, ra);
        else { parent.put(rb, ra); rank.put(ra, rA + 1); }
        return true;
    }
}


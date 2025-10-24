package mst.util;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {
    private Map<Integer, Integer> parent = new HashMap<>();

    public void makeSet(int item) {
        parent.put(item, item);
    }

    public int find(int item) {
        if (parent.get(item) != item)
            parent.put(item, find(parent.get(item)));
        return parent.get(item);
    }

    public void union(int set1, int set2) {
        int root1 = find(set1);
        int root2 = find(set2);
        parent.put(root1, root2);
    }
}
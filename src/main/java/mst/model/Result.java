package mst.model;

import java.util.List;

public class Result {
    public String algorithm;
    public int totalWeight;
    public long executionTimeMs;
    public List<Edge> mstEdges;

    public Result(String algorithm, int totalWeight, long executionTimeMs, List<Edge> mstEdges) {
        this.algorithm = algorithm;
        this.totalWeight = totalWeight;
        this.executionTimeMs = executionTimeMs;
        this.mstEdges = mstEdges;
    }
}
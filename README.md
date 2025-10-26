# Assignment 3 – Minimum Spanning Tree (MST)

## Project idea
This project is about building roads between all city districts so every place is connected,  
but with the **minimum total cost**.  
To do that, I used two algorithms: **Prim** and **Kruskal**.

They both find the same minimum spanning tree (MST),  
but work in a bit different ways.

---

## What my program does
1. Reads graph data from JSON files in the `data/` folder.  
   Each file has city names (nodes) and roads with costs (edges).
2. Runs both **Prim** and **Kruskal** algorithms.
3. Saves the results (total cost, time, operations) into  
   `results/output.json` and `results/summary.csv`.
4. Then I compared both algorithms to see which one is faster.

---

## Example of input
```json
{
  "nodes": ["A", "B", "C", "D"],
  "edges": [
    {"from": "A", "to": "B", "weight": 4},
    {"from": "A", "to": "C", "weight": 3},
    {"from": "B", "to": "C", "weight": 2},
    {"from": "C", "to": "D", "weight": 7}
  ]
}
````

---

## Results Summary

| Graph ID | Vertices | Edges | Prim Cost | Kruskal Cost | Prim (ms) | Kruskal (ms) | Prim Ops | Kruskal Ops |
| -------- | -------- | ----- | --------- | ------------ | --------- | ------------ | -------- | ----------- |
| 1        | 5        | 7     | 16        | 16           | 0.536     | 1.321        | 23       | 35          |
| 2        | 4        | 5     | 6         | 6            | 0.039     | 0.032        | 15       | 25          |

 Both algorithms give the same MST cost.
 Prim used fewer operations, Kruskal used a bit more.

---

## Charts

### Execution Time vs Graph Size

<img width="780" height="372" alt="Execution Time vs Graph Size" src="https://github.com/user-attachments/assets/582429b4-e819-46d2-a1ca-df1f8876baff" />


### Operation Count vs Graph Size

<img width="741" height="355" alt="Operation Count vs Graph Size" src="https://github.com/user-attachments/assets/df9dba32-865b-476a-8b03-5728d405ef71" />


---

## What I learned

* Both Prim and Kruskal find the same minimum cost.
* **Prim** is faster when the graph has many connections (dense graph).
* **Kruskal** is easier to code and works well when there are fewer edges.
* For my test data, Prim did a bit less work overall.

---

## Folder structure

```
data/
 ├── input_small.json
 ├── input_medium.json
 └── input_large.json
results/
 ├── output.json
 └── summary.csv
src/
 └── main/java/mst/
     ├── algorithms/
     │   ├── Prim.java
     │   └── Kruskal.java
     ├── model/
     │   ├── Graph.java
     │   └── Edge.java
     ├── util/
     │   ├── DisjointSet.java
     │   └── IOUtils.java
     └── cli/Run.java
docs/
 ├── mst_time.png
 ├── mst_ops.png
 └── MST_Summary_Data.csv
```

---

## Tests

There are simple tests in `test/java/mst/` that check:

* both algorithms return the same cost,
* MST has V - 1 edges,
* there are no cycles,
* and disconnected graphs are handled correctly.

---


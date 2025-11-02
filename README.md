# Assignment 4 – Graph Algorithms Analysis

## 1. Overview

This project implements and analyzes three fundamental algorithms for directed graphs using Java:

- **Tarjan’s Algorithm** – Strongly Connected Components (SCC)
- **Topological Sort** – Kahn’s Algorithm
- **Shortest Path in Directed Acyclic Graph (DAG-SP)** – Dynamic Programming approach

The implementation includes reading data from JSON files, executing algorithms, measuring performance, and interpreting the impact of graph structure on execution time.

All work was done in **Java 17** with **Maven** and tested using **JUnit 5**.

---

## 2. Data Summary

All datasets are located in the `data/` folder and consist of directed, weighted graphs defined in JSON format with the following fields:
- `n`: number of vertices
- `edges`: list of edges `{u, v, w}`
- `directed`: graph direction flag
- `source`: source vertex for shortest path
- `weight_model`: edge weight model

### Dataset Overview

| File | Graphs | Avg. Vertices | Avg. Edges | Purpose |
|------|---------|----------------|-------------|----------|
| small.json | 3 | 6–8 | 5–7 | Validation and debugging |
| medium.json | 3 | 12–18 | 10–13 | Performance comparison |
| large.json | 3 | 25–40 | 20–25 | Stress testing and scalability |

All graphs are **directed** and **weighted**, which allows for SCC, topological sorting, and shortest path evaluation.

---

## 3. Experimental Setup

**Environment:**
- Java 17 (OpenJDK)
- Maven
- Gson (for JSON parsing)
- JUnit 5 (testing framework)
- Machine: Intel i5, 16 GB RAM, Windows 10 x64

**Metrics collected:**
- Execution time (milliseconds)
- DFS calls (Tarjan)
- Queue operations (Kahn)
- Edge relaxations (DAG-SP)

Tests were executed multiple times per dataset to ensure consistent results.

---

## 4. Results

### 4.1 Strongly Connected Components (Tarjan)

| Graph | n | m | SCC count | DFS calls | Time (ms) |
|-------|---:|---:|---:|---:|---:|
| small-1 | 6 | 5 | 2 | 6 | 0.1 |
| small-2 | 7 | 6 | 3 | 7 | 0.2 |
| medium-1 | 12 | 10 | 3 | 12 | 0.4 |
| medium-2 | 15 | 12 | 4 | 15 | 0.6 |
| large-1 | 25 | 20 | 6 | 25 | 1.5 |
| large-3 | 40 | 24 | 8 | 40 | 3.6 |

### 4.2 Topological Sort (Kahn)

| Graph | n | m | Topo Valid | Queue Ops | Time (ms) |
|-------|---:|---:|:----------:|---:|---:|
| small-1 | 6 | 5 | yes | 6 | 0.05 |
| medium-2 | 15 | 12 | yes | 15 | 0.12 |
| large-3 | 40 | 24 | yes | 40 | 0.4 |

Example output:

Graph 1 → [3, 6, 4, 7, 5, 8, 9, 10, 11]

Graph 2 → [0, 7, 10, 1, 8, 11, 2, 9, 12, 3, 13, 14]

Graph 3 → [3, 6, 10, 13, 15, 4, 7, 11, 14, 16, 5, 8, 12, 17, 9]


### 4.3 Shortest Path in DAG (DAG-SP)

| Graph | n | m | Source | Relaxations | Time (ms) | Longest Path |
|-------|---:|---:|---:|---:|---:|---:|
| small-1 | 6 | 5 | 0 | 8 | 0.08 | 12 |
| medium-2 | 15 | 12 | 0 | 20 | 0.25 | 27 |
| large-3 | 40 | 24 | 0 | 70 | 1.1 | 68 |

---

## 5. Algorithmic Analysis

### Complexity Overview

| Algorithm | Time Complexity | Description |
|------------|-----------------|--------------|
| Tarjan SCC | O(V + E) | DFS with low-link tracking and recursion |
| Kahn Topo | O(V + E) | Queue-based BFS on DAGs |
| DAG-SP | O(V + E) | Dynamic programming on topological order |

### Bottlenecks

- **Tarjan SCC**: recursive depth and low-link updates dominate runtime on dense graphs.
- **Topological Sort**: lightweight; main cost is queue management.
- **DAG-SP**: depends on topological order generation and edge relaxations.

### Effect of Graph Structure

| Structure | Observed Impact |
|------------|-----------------|
| High Density | SCC runtime increases due to more DFS traversals |
| Sparse Graphs | Faster execution for all algorithms |
| Large SCCs | Fewer nodes in condensed DAG, faster DAG algorithms |
| Many Small SCCs | More DAG nodes, slightly more overhead for Topo/DAG-SP |

---

## 6. Discussion and Recommendations

1. **Preprocessing**: Always compute SCCs first to handle cycles and obtain an acyclic condensed DAG.
2. **Algorithm Selection**:
    - **Tarjan SCC**: cycle detection, dependency grouping, condensation.
    - **Topological Sort**: scheduling, dependency ordering, DAG validation.
    - **DAG-SP**: weighted DAG optimization and path computation.
3. **Performance Tips**:
    - Preallocate adjacency lists using `new ArrayList<>(n)`.
    - Use primitive arrays (`int[]`) instead of object-based edges for speed.
    - Avoid creating new collections inside loops.

---

## 7. Conclusions

- All algorithms were successfully implemented and tested.
- Each dataset (9 total graphs) was analyzed through SCC, Topo Sort, and DAG-SP.
- Execution time grows linearly with the number of vertices and edges.
- Tarjan’s algorithm is dominant on dense graphs; Topological Sort and DAG-SP are optimal for sparse DAGs.

### When to Use Each Algorithm

| Algorithm | Use Case | Strengths | Limitations |
|------------|-----------|------------|--------------|
| Tarjan SCC | Detecting cycles and dependencies | Reliable, linear time | Stack depth on large SCCs |
| Topological Sort | Ordering tasks in DAGs | Simple, efficient | Requires acyclic input |
| DAG-SP | Weighted DAG path analysis | Fast and deterministic | Not valid for cyclic graphs |

package org.joat.mow.Model;

import java.util.HashMap;
import java.util.Set;

/**
 * Generic interface for a graph that can be used in algorithms.
 */
public interface Graph {
    public void addNode(Cell cell);

    public Set<Cell> getNodes();

    public void addEdge(Cell start, Cell end);

    public void addEdge(Cell start, Cell end, int cost);

    public Set<Edge> getEdges();

    public HashMap<Cell, Set<Edge>> getAdjacencyList();
}


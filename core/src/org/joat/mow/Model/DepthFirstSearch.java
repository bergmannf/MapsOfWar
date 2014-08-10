package org.joat.mow.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of a depth first search.
 */
public class DepthFirstSearch {
    private final Graph graph;

    public DepthFirstSearch(Graph g) {
        this.graph = g;
    }

    /**
     * Returns a collection of all cells reachable from the start in maxDepth-steps.
     * @param start The start cell.
     * @param maxDepth The maximum depth to traverse.
     * @return A collection of reachable cells.
     */
    public Collection<Cell> search(Cell start, int maxDepth) {
        Set<Cell> cells = new HashSet<Cell>();
        this.search(start, maxDepth, cells);
        return cells;
    }

    private void search(Cell start, int maxDepth, Set<Cell> accumulator) {
        if (maxDepth == 0) {
            return;
        }
        Set<Edge> edges = this.graph.getAdjacencyList().get(start);
        for (Edge edge : edges) {
            accumulator.add(edge.end);
            this.search(edge.end, maxDepth - edge.cost, accumulator);
        }
    }
}

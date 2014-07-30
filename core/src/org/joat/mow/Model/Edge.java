package org.joat.mow.Model;

/**
 * Represents an edge in a graph.
 */
public class Edge {
    /**
     * Start-node of the edge
     */
    public final Cell start;
    /**
     * End-node of the edge
     */
    public final Cell end;
    public final int cost;

    public Edge(Cell start, Cell end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}

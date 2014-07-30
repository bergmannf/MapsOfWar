package org.joat.mow.Model;

import java.util.List;

/**
 * Interface for a search algorithm.
 */
public interface SearchAlgorithm {
    List<Cell> shortestPath(Graph g, Cell start, Cell end);
}

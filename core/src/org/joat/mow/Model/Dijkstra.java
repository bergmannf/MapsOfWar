package org.joat.mow.Model;

import java.util.*;

/**
 * Implements Dijkstra's single source shortest path search.
 */
public class Dijkstra implements SearchAlgorithm {
    private Queue<Cell> openSet;
    private HashMap<Cell, Integer> costMap;
    private HashMap<Cell, Cell> previous;
	private Graph graph;

	public Dijkstra(Graph graph) {
		this.init(graph);
	}

	private void init(Graph graph) {
        this.graph = graph;
        Set<Cell> visited = new HashSet<Cell>();
		this.openSet = new ArrayDeque<Cell>();
        this.costMap = new HashMap<Cell, Integer>();
        this.previous = new HashMap<Cell, Cell>();
	}

	@Override
	public List<Cell> shortestPath(Graph g, Cell start, Cell end) {
		this.init(g);
        this.costMap.put(start, 0);
        for (Cell c : this.graph.getNodes()) {
            if (!c.equals(start)) {
                this.costMap.put(c, Integer.MAX_VALUE);
            }
            this.openSet.add(c);
        }
        while (!this.openSet.isEmpty()) {
            Cell minCell = this.extractMin();
            this.openSet.remove(minCell);
            for (Edge e : this.graph.getAdjacencyList().get(minCell)) {
                int nextDist = this.costMap.get(minCell) + e.cost;
                if (nextDist < this.costMap.get(e.end)) {
                    this.costMap.put(e.end, nextDist);
                    this.previous.put(e.end, minCell);
                }
            }
        }
        return buildPath(start, end);
	}

    private Cell extractMin() {
        Cell minCell = null;
        int cost = Integer.MAX_VALUE;
        for (Cell c : this.openSet) {
            int cellCost = this.costMap.get(c);
            if (cellCost < cost) {
                minCell = c;
            }
        }
        return minCell;
    }

    private List<Cell> buildPath(Cell start, Cell end) {
        List<Cell> path = new ArrayList<Cell>();
        path.add(end);
        Cell current = end;
        do{
            Cell previous = this.previous.get(current);
            path.add(previous);
            current = previous;
        } while (!current.equals(start));
        return path;
    }
}

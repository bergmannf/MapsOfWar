package org.joat.mow.Model;

import com.badlogic.gdx.Gdx;

import java.util.*;

/**
 * A simple rectangular Grid.
 */
public class Grid implements Graph {
    /**
     * An adjacency list of all edges in the graph.
     */
    private final HashMap<Cell, Set<Edge>> adjacencyList;

    /**
     * A set of all edges in the graph.
     */
    private final Set<Edge> edges;

    /**
     * Number of grid cells used for rows.
     */
    private int rows;

    /**
     * Number of grid cells used for columns.
     */
    private int columns;

    /**
     * Individual cells of the Grid.
     */
    private Set<Cell> nodes;

    private SearchAlgorithm searchAlgorithm;

    /**
     * Create a grid for a given rows and columns with a given cellSize.
     *
     * @param gameWidth  - Number of columns in the grid.
     * @param gameHeight - Number of rows in the grid.
     */
    public Grid(int gameWidth, int gameHeight) {
        this.rows = gameHeight;
        this.columns = gameWidth;
        this.edges = new HashSet<Edge>();
        this.nodes = new HashSet<Cell>();
        this.adjacencyList = new HashMap<Cell, Set<Edge>>();
        initializeGridCells();
        initializeGridEdges();
        Gdx.app.debug("DEBUG", "Rows: " + rows);
        Gdx.app.debug("DEBUG", "Columns: " + columns);
    }

    private void initializeGridCells() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                Cell c = new Cell(x, y);
                this.addNode(c);
            }
        }
    }

    private void initializeGridEdges() {
        int[] moveNorth = new int[]{0, 1};
        int[] moveEast = new int[]{1, 0};
        int[] moveSouth = new int[]{0, -1};
        int[] moveWest = new int[]{-1, 0};
        List<int[]> movements = new ArrayList<int[]>();
        movements.add(moveNorth);
        movements.add(moveEast);
        movements.add(moveSouth);
        movements.add(moveWest);
        for (Cell node : getNodes()) {
            for (int[] movement : movements) {
                int newX = node.getX() + movement[0];
                int newY = node.getY() + movement[1];
                if (newX >= 0 && newX < this.columns) {
                    if (newY >= 0 && newY < this.rows) {
                        Cell target = this.getCell(newX, newY);
                        this.addEdge(node, target);
                    }
                }
            }
        }
    }

    public Set<Cell> neighbors(Cell cell) {
        Set<Cell> neighbors = new HashSet<Cell>();
        final HashMap<Cell, Set<Edge>> edges = this.getAdjacencyList();
        final Set<Edge> paths = edges.get(cell);
        for (Edge path : paths) {
            neighbors.add(path.end);
        }
        return neighbors;
    }

    /**
     * Returns the cell at the X and Y coordinates.
     * Indexing starts at 0.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Cell getCell(int x, int y) {
        for (Cell node : this.getNodes()) {
            if (node.getX() == x && node.getY() == y) {
                return node;
            }
        }
        throw new IllegalArgumentException("No node found at coordinates: " + x + " " + y);
    }

    public List<Cell> shortestPath(Cell start, Cell end) {
    	this.searchAlgorithm = new Dijkstra(this);
        return this.searchAlgorithm.shortestPath(this, start, end);
    }

    @Override
    public void addNode(Cell cell) {
        this.nodes.add(cell);
    }

    @Override
    public Set<Cell> getNodes() {
        return this.nodes;
    }

    @Override
    public void addEdge(Cell start, Cell end) {
        this.addEdge(start, end, 1);
    }

    @Override
    public void addEdge(Cell start, Cell end, int cost) {
        Set<Edge> paths;
        if (this.adjacencyList.containsKey(start)) {
            paths = this.adjacencyList.get(start);
        } else {
            paths = new HashSet<Edge>();
        }
        Edge e = new Edge(start, end, cost);
        this.edges.add(e);
        paths.add(e);
        this.adjacencyList.put(start, paths);
    }

    @Override
    public Set<Edge> getEdges() {
        return this.edges;
    }

    @Override
    public HashMap<Cell, Set<Edge>> getAdjacencyList() {
        return this.adjacencyList;
    }
}


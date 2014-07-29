package org.joat.mow.Model;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple rectangular Grid.
 */
public class Grid {
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
    private Cell[][] cells;

    /**
     * Create a grid for a given rows and columns with a given cellSize.
     *
     * @param gameWidth  - Number of columns in the grid.
     * @param gameHeight - Number of rows in the grid.
     */
    public Grid(int gameWidth, int gameHeight) {
        this.rows = gameWidth;
        this.columns = gameHeight;
        Gdx.app.debug("DEBUG", "Rows: " + rows);
        Gdx.app.debug("DEBUG", "Columns: " + columns);
        initializeGridCells();
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    private void initializeGridCells() {
        this.cells = new Cell[rows][columns];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                this.cells[y][x] = new Cell(x, y);
            }
        }
    }

    public List<Cell> neighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<Cell>();
        for (int i = -1; i <= 1; i++) {
            if (i == 0) {
                continue;
            }
            int newX = (int) cell.position.x + i;
            int newY = (int) cell.position.y;
            if (newX >= 0
                    && newY >= 0
                    && newX < columns
                    && newY < rows) {
                neighbors.add(this.getCells()[newY][newX]);
            }
            newX = (int) cell.position.x;
            newY = (int) cell.position.y + i;
            if (newX >= 0
                    && newY >= 0
                    && newX < columns
                    && newY < rows) {
                neighbors.add(this.getCells()[newY][newX]);
            }
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
        return this.cells[x][y];
    }
}


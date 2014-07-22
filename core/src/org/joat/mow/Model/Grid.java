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

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    /**
     * Create a grid for a given rows and columns with a given cellSize.
     * @param gameHeight - Number of cells in height.
     * @param gameWidth - Number of cells in width.
     */
    public Grid(int gameHeight, int gameWidth) {
        this.rows = gameHeight;
        this.columns = gameWidth;
        Gdx.app.debug("DEBUG", "Rows: " + rows);
        Gdx.app.debug("DEBUG", "Columns: " + columns);
        initializeGridCells();
    }

    private void initializeGridCells() {
        this.cells = new Cell[rows][columns];
        for (int y = 0; y < rows; y ++) {
            for (int x = 0; x < columns; x++) {
                this.cells[y][x] = new Cell(x, y);
            }
        }
    }

    public List<Cell> neighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<Cell>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int newX = (int) cell.position.x + i;
                int newY = (int) cell.position.y + i;
                if (newX >= 0
                        && newY >= 0
                        && newX < columns
                        && newY < rows) {
                    neighbors.add(this.getCells()[newY][newX]);
                }
            }
        }
        return neighbors;
    }
}


package org.joat.mow;

import com.badlogic.gdx.Gdx;

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

    private int cellSize;

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
     * @param gameHeight - Height of the canvas.
     * @param gameWidth - Width of the canvas.
     * @param cellSize - Size of a (square) grid-cell.
     */
    public Grid(int gameHeight, int gameWidth, int cellSize) {
        this.rows = gameHeight / cellSize;
        this.columns = gameWidth / cellSize;
        Gdx.app.debug("DEBUG", "Rows: " + rows);
        Gdx.app.debug("DEBUG", "Columns: " + columns);
        this.cellSize = cellSize;
        initializeGridCells();
    }

    private void initializeGridCells() {
        this.cells = new Cell[rows][columns];
        for (int y = 0; y < rows; y ++) {
            for (int x = 0; x < columns; x++) {
                this.cells[y][x] = new Cell(x, y, this.cellSize);
            }
        }
    }
}


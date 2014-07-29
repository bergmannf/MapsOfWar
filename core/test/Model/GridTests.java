package Model;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import org.joat.mow.MapsOfWar;
import org.joat.mow.Model.Cell;
import org.joat.mow.Model.Grid;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;


/**
 * Created by florian on 17/07/14.
 */
public class GridTests {
    private Grid grid;

    @BeforeClass
    public static void setUp() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        config.renderInterval = 1f/60;
        HeadlessApplication app = new HeadlessApplication(new MapsOfWar(), config);
    }

    @Test
    public void neighborsTest() {
        Grid grid = new Grid(3, 3);
        Cell[][] cells = grid.getCells();
        Cell cell = new Cell(1, 1);
        List<Cell> n = grid.neighbors(cell);
        Assert.assertEquals(4, n.size());
        Assert.assertThat(n, hasItem(cells[1][0]));
        Assert.assertThat(n, hasItem(cells[0][1]));
        Assert.assertThat(n, hasItem(cells[2][1]));
        Assert.assertThat(n, hasItem(cells[1][2]));

        cell = new Cell(0, 0);
        n = grid.neighbors(cell);
        Assert.assertEquals(2, n.size());
        Assert.assertThat(n, hasItem(cells[1][0]));
        Assert.assertThat(n, hasItem(cells[0][1]));
    }

    @Test
    public void cellAccess() {
        Grid grid = new Grid(4, 2);
        Assert.assertNotNull(grid.getCell(0, 0));
        Assert.assertNotNull(grid.getCell(3, 0));
        Assert.assertNotNull(grid.getCell(0, 1));
        Assert.assertNotNull(grid.getCell(3, 1));
    }

    @Test
    public void pathTest() {
        Grid grid = new Grid(3, 3);
    }
}

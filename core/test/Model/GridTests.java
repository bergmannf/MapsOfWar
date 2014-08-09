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
import java.util.Set;


/**
 * Created by florian on 17/07/14.
 */
public class GridTests {
    @BeforeClass
    public static void setUp() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        config.renderInterval = 1f/60;
        HeadlessApplication app = new HeadlessApplication(new MapsOfWar(), config);
    }

    @Test
    public void gridSetupTest() {
        Grid grid = new Grid(3, 3);
        Assert.assertEquals(grid.getNodes().size(), 9);
        Assert.assertEquals(grid.getEdges().size(), 24);
    }

    @Test
    public void neighborsTest() {
        Grid grid = new Grid(3, 3);
        Cell cell = grid.getCell(1, 1);
        Set<Cell> n = grid.neighbors(cell);
        Assert.assertEquals(4, n.size());
        Assert.assertThat(n, hasItem(grid.getCell(1, 0)));
        Assert.assertThat(n, hasItem(grid.getCell(0, 1)));
        Assert.assertThat(n, hasItem(grid.getCell(2, 1)));
        Assert.assertThat(n, hasItem(grid.getCell(1, 2)));

        cell = grid.getCell(0, 0);
        n = grid.neighbors(cell);
        Assert.assertEquals(2, n.size());
        Assert.assertThat(n, hasItem(grid.getCell(1, 0)));
        Assert.assertThat(n, hasItem(grid.getCell(0, 1)));
    }

    @Test
    public void cellAccessTest() {
        Grid grid = new Grid(4, 2);
        Assert.assertNotNull(grid.getCell(0, 0));
        Assert.assertNotNull(grid.getCell(3, 0));
        Assert.assertNotNull(grid.getCell(0, 1));
        Assert.assertNotNull(grid.getCell(3, 1));
    }

    @Test
    public void pathTest() {
        Grid grid = new Grid(3, 3);
        List<Cell> path = grid.shortestPath(grid.getCell(0, 0), grid.getCell(0, 2));
        Assert.assertEquals(2, path.size());
        Assert.assertEquals(path.get(0), grid.getCell(0, 1));
        Assert.assertEquals(path.get(1), grid.getCell(0, 2));
    }
}

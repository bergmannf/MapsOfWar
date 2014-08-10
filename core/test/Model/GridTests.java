package Model;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import org.joat.mow.MapsOfWar;
import org.joat.mow.Model.Cell;
import org.joat.mow.Model.Grid;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;


/**
 * Created by florian on 17/07/14.
 */
public class GridTests {
    @BeforeClass
    public static void setUp() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        config.renderInterval = 1f/60;
        new HeadlessApplication(new MapsOfWar(), config);
    }

    @Test
    public void gridSetupTest() {
        Grid grid = new Grid(3, 3);
        Assert.assertEquals(9, grid.getNodes().size());
        Assert.assertEquals(24, grid.getEdges().size());
        grid = new Grid(4, 2);
        Assert.assertEquals(8, grid.getNodes().size());
        Assert.assertEquals(20, grid.getEdges().size());
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
        Assert.assertEquals(3, path.size());
        Assert.assertEquals(path.get(0), grid.getCell(0, 2));
        Assert.assertEquals(path.get(1), grid.getCell(0, 1));
        Assert.assertEquals(path.get(2), grid.getCell(0, 0));
    }
    
    @Test
    public void reachabilityTest() {
        // Set<Cell> reachable = grid.reachableFrom(grid.getCell(0, 0), 2);
    	Assert.fail();
    }
}

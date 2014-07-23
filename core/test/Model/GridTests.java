package Model;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import junit.framework.Assert;
import org.joat.mow.MapsOfWar;
import org.joat.mow.Model.Cell;
import org.joat.mow.Model.Grid;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        Cell cell = new Cell(1, 1);
        List<Cell> n = grid.neighbors(cell);
        Assert.assertEquals(4, n.size());
    }
}

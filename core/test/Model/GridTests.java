package Model;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import junit.framework.Assert;
import org.joat.mow.MapsOfWar;
import org.joat.mow.Model.Grid;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by florian on 17/07/14.
 */
public class GridTests {
    private Grid grid;

    @Before
    public void setUp() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        config.renderInterval = 1f/60;
        HeadlessApplication app = new HeadlessApplication(new MapsOfWar(), config);
    }

    @Test
    public void neighborsTest() {
        Assert.assertTrue(true);
    }
}

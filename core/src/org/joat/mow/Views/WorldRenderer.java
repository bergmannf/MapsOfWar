package org.joat.mow.Views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import org.joat.mow.Constants;
import org.joat.mow.Controller.WorldController;
import org.joat.mow.Model.AbstractGameObject;
import org.joat.mow.Model.Cell;
import org.joat.mow.Model.Map;

/**
 * Created by florian on 29/04/14.
 */
public class WorldRenderer implements Disposable {
    public static final String TAG = WorldRenderer.class.getName();
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;
	private PlacementUI placementUi;

    public WorldRenderer(WorldController controller) {
        this.worldController = controller;
        init();
    }

    private void init() {
        this.batch = new SpriteBatch();
        this.camera = (OrthographicCamera) this.worldController.getCamera();
        this.camera.position.set(10, 10, 0);
        this.camera.update();
        this.placementUi = new PlacementUI(worldController);
    }

    /**
     * Renders the complete user interface for a map, including the UI.
     * The UI is rendered after the map to maintain visibility.
     */
    public void render() {
    	renderMap();
    	renderUi();
    }

    public void resize(int width, int height) {
        this.camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        this.camera.update();
        this.placementUi.resize(width, height);
    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }
    
    private void renderMap() {
        this.worldController.getCameraHelper().applyTo(camera);
        this.batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
        Map m = worldController.getMap();
        SpriteRenderer renderer = new SpriteRenderer();
        for (Cell cell : m.getGrid().getNodes()) {
            renderer.render(this.batch, cell);
        }
        for (AbstractGameObject actor : m.getActors()) {
            renderer.render(this.batch, actor);
        }
        batch.end();
    }
    
    private void renderUi() {
        this.placementUi.render();
    }
}

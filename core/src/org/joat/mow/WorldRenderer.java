package org.joat.mow;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import org.joat.mow.Controller.WorldController;
import org.joat.mow.Model.AbstractGameObject;
import org.joat.mow.Model.Map;

/**
 * Created by florian on 29/04/14.
 */
public class WorldRenderer implements Disposable {
    public static final String TAG = WorldRenderer.class.getName();
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;

    public WorldRenderer(WorldController controller) {
        this.worldController = controller;
        init();
    }

    private void init() {
        this.batch = new SpriteBatch();
        this.camera = (OrthographicCamera) this.worldController.getCamera();
        this.camera.position.set(10, 10, 0);
        this.camera.update();
    }

    public void render() {
        renderTestObjects();
    }

    public void resize(int width, int height) {
        this.camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        this.camera.update();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }

    private void renderTestObjects() {
        this.worldController.getCameraHelper().applyTo(camera);
        this.batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
        Map m = worldController.getMap();
        m.getGrid().render(batch);
        for (AbstractGameObject actor : m.getActors()) {
            actor.render(batch);
        }
        batch.end();
    }

}

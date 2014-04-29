package org.joat.mow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by florian on 29/04/14.
 */
public class WorldRenderer implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;

    public WorldRenderer(WorldController controller) {
        this.worldController = controller;
        init();
    }

    private void init(){
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        this.camera.position.set(0, 0, 0);
        this.camera.update();
    }

    public void render(){
        renderTestObjects();
/*        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (Cell[] row : this.gridMap.getCells()) {
            for (int i = 0; i < row.length; i++) {
                float xStart = row[i].getX();
                float yStart = row[i].getY();
                shapeRenderer.rect(xStart, yStart, 20, 20);
            }
        }
        shapeRenderer.end();*/
    }

    public void resize(int width, int height){
        this.camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        this.camera.update();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }

    private void renderTestObjects() {
        this.batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
        for (Sprite sprite : worldController.testSprites) {
            sprite.draw(batch);
        }
        batch.end();
    }

}

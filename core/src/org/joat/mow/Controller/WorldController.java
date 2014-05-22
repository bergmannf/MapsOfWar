package org.joat.mow.Controller;

import org.joat.mow.CameraHelper;
import org.joat.mow.Constants;
import org.joat.mow.Model.GameObject;
import org.joat.mow.Model.Map;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;

/**
 * Created by florian on 29/04/14.
 */
public class WorldController {
    public class DebugKeyListener extends InputAdapter {
        @Override
        public boolean keyUp(int keycode) {
            if (keycode == Keys.R) {
                init();
                Gdx.app.debug(TAG, "Game world resetted.");
            } else if (keycode == Keys.SPACE) {
                selectedSprite = (selectedSprite + 1) % testSprites.length;
                if (cameraHelper.hasTarget()) {
                    cameraHelper.setTarget(testSprites[selectedSprite]);
                }
                Gdx.app.debug(TAG, "Sprite #" + selectedSprite + " selected.");
            } else if (keycode == Keys.ENTER) {
                cameraHelper.setTarget(cameraHelper.hasTarget() ? null : testSprites[selectedSprite]);
                Gdx.app.debug(TAG, "Camera follow enabled:" + cameraHelper.hasTarget());
            }
            return false;
        }
    }
    private static final String TAG = WorldController.class.getName();
    public Sprite[] testSprites;
    public int selectedSprite;
    private InputMultiplexer inputMultiplexer;
    private CameraHelper cameraHelper;
    private Map map;

    private Camera camera;
	private GridCellController gridCellController;

    public WorldController() {
        init();
    }

    public Camera getCamera() {
        return camera;
    }

    public CameraHelper getCameraHelper() {
        return cameraHelper;
    }

    public Map getMap() {
        return map;
    }

    private void handleDebugInput(float deltaTime) {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) {
            return;
        }
        float spriteMoveSpeed = 5 * deltaTime;
        if (Gdx.input.isKeyPressed(Keys.A)) {
            moveSelectedSprite(-spriteMoveSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            moveSelectedSprite(spriteMoveSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.W)) {
            moveSelectedSprite(0, spriteMoveSpeed);
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            moveSelectedSprite(0, -spriteMoveSpeed);
        }
        float camMoveSpeed = 5 * deltaTime;
        float camMoveSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
            camMoveSpeed *= camMoveSpeedAccelerationFactor;
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            moveCamera(-camMoveSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            moveCamera(camMoveSpeed, 0);
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            moveCamera(0, camMoveSpeed);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            moveCamera(0, -camMoveSpeed);
        }
        if (Gdx.input.isKeyPressed(Keys.BACKSLASH)) {
            cameraHelper.setPosition(0, 0);
        }
        float camZoomSpeed = 1 * deltaTime;
        float camZoomSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
            camZoomSpeed *= camZoomSpeedAccelerationFactor;
        }
        if (Gdx.input.isKeyPressed(Keys.COMMA)) {
            cameraHelper.addZoom(camZoomSpeed);
        }
        if (Gdx.input.isKeyPressed(Keys.PERIOD)) {
            cameraHelper.addZoom(-camZoomSpeed);
        }
        if (Gdx.input.isKeyPressed(Keys.SLASH)) {
            cameraHelper.setZoom(1);
        }
    }

    public void init() {
        map = new Map();
        cameraHelper = new CameraHelper();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        initMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void initMultiplexer() {
        inputMultiplexer = new InputMultiplexer();
        gridCellController = new GridCellController(getCamera(), map);
        inputMultiplexer.addProcessor(new ActorController(getCamera(), map));
        inputMultiplexer.addProcessor(this.gridCellController);
        inputMultiplexer.addProcessor(new GestureDetector(new GestureController(this.cameraHelper)));
        inputMultiplexer.addProcessor(new DebugKeyListener());
    }

    private void moveCamera(float x, float y) {
        float newX = cameraHelper.getPosition().x + x;
        float newY = cameraHelper.getPosition().y + y;
        cameraHelper.setPosition(newX, newY);
    }

    private void moveSelectedSprite(float x, float y) {
        testSprites[selectedSprite].translate(x, y);
    }

    public void update(float deltaTime) {
        handleDebugInput(deltaTime);
        cameraHelper.update(deltaTime);
    }

    /**
     * Prepares a new actor that will be added to the map with the next click on a cell.
     * @param string The path to the internal image of the actor.
     */
	public void newActor(String string) {
		// TODO: make the parameter the image and maybe add more options.
		GameObject o = new GameObject(string);
		this.gridCellController.setObjectToPlace(o);
	}
}

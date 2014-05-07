package org.joat.mow;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by florian on 29/04/14.
 */
public class WorldController {
    private static final String TAG = WorldController.class.getName();
    private InputAdapter debugListener;

    public Sprite[] testSprites;
    public int selectedSprite;
    private CameraHelper cameraHelper;

    public CameraHelper getCameraHelper() {
        return cameraHelper;
    }


    public WorldController() {
        init();
    }

    public void init(){
        debugListener = new DebugKeyListener();
        Gdx.input.setInputProcessor(debugListener);
        cameraHelper = new CameraHelper();
        initTestObjects();
    }

    public void update(float deltaTime) {
        handleDebugInput(deltaTime);
        updateTestObjects(deltaTime);
        cameraHelper.update(deltaTime);
    }

    private void initTestObjects() {
        testSprites = new Sprite[5];
        int width = 32;
        int height = 32;
        Pixmap pixmap = createProceduralPixmap(width, height);
	AssetManager am = new AssetManager();
	am.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
	am.finishLoading();
	TextureAtlas atlas = am.get(Constants.TEXTURE_ATLAS_OBJECTS);
	AtlasRegion a = atlas.findRegion("HumanFighter");
        TextureRegion texture = a;
        for (int i = 0; i < testSprites.length; i++) {
            Sprite spr = new Sprite(texture);
            spr.setSize(1, 1);
            spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
            float randomX = MathUtils.random(-2.0f, 2.0f);
            float randomY = MathUtils.random(-2.0f, 2.0f);
            spr.setPosition(randomX, randomY);
            testSprites[i] = spr;
        }
        selectedSprite = 0;
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

    private void moveCamera(float x, float y) {
        float newX = cameraHelper.getPosition().x + x;
        float newY = cameraHelper.getPosition().y + y;
        cameraHelper.setPosition(newX, newY);
    }

    private void moveSelectedSprite(float x, float y) {
        testSprites[selectedSprite].translate(x, y);
    }

    private void updateTestObjects(float deltaTime) {
        float rotation = testSprites[selectedSprite].getRotation();
        rotation += 90 * deltaTime;
        rotation %= 360;
        testSprites[selectedSprite].setRotation(rotation);
    }

    private Pixmap createProceduralPixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 0, 0, 0.5f);
        pixmap.fill();
        pixmap.setColor(1, 1, 0, 1.0f);
        pixmap.drawLine(0, 0, width, height);
        pixmap.drawLine(width, 0, 0, height);
        pixmap.setColor(0, 1, 1, 1);
        pixmap.drawRectangle(0, 0, width, height);
        return pixmap;
    }

    public class DebugKeyListener extends InputAdapter {
        @Override
        public boolean keyUp(int keycode) {
            if (keycode == Keys.R) {
                init();
                Gdx.app.debug(TAG, "Game world resetted.");
            }
            else if (keycode == Keys.SPACE){
                selectedSprite = (selectedSprite + 1) % testSprites.length;
                if (cameraHelper.hasTarget()) {
                    cameraHelper.setTarget(testSprites[selectedSprite]);
                }
                Gdx.app.debug(TAG, "Sprite #" + selectedSprite + " selected.");
            }
            else if (keycode == Keys.ENTER) {
                cameraHelper.setTarget(cameraHelper.hasTarget() ? null : testSprites[selectedSprite]);
                Gdx.app.debug(TAG, "Camera follow enabled:" + cameraHelper.hasTarget());
            }
            return false;
        }
    }
}

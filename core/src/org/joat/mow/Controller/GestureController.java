package org.joat.mow.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

import org.joat.mow.CameraHelper;
import org.joat.mow.Constants;

/**
 * Created by florian on 18/05/14.
 */
public class GestureController extends GestureDetector.GestureAdapter {
    public static final String TAG = GestureController.class.getName();
    private CameraHelper cameraHelper;
    private Camera camera;

    /**
     * This controller only controls zoom and move gestures and applies them to the cameraHelper.
     * 
     * @param camera The camera of the UI that can be scrolled by a gesture.
     * @param cameraHelper The cameraHelper that will be modified.
     */
    public GestureController(Camera camera, CameraHelper cameraHelper) {
    	this.camera = camera;
        this.cameraHelper = cameraHelper;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
    	Vector3 startCoords = new Vector3(x, y, 0);
    	Vector3 deltaCoords = new Vector3(x + deltaX, y + deltaY, 0);
    	Vector3 unprojStartCoords = camera.unproject(startCoords);
    	Vector3 unprojDeltaCoords = camera.unproject(deltaCoords);
    	Vector3 delta = unprojStartCoords.sub(unprojDeltaCoords);
    	Gdx.app.debug(TAG, "X-Start " + unprojStartCoords.x);
    	Gdx.app.debug(TAG, "Y-Start " + unprojStartCoords.y);
    	Gdx.app.debug(TAG, "X-Delta " + unprojDeltaCoords.x);
    	Gdx.app.debug(TAG, "Y-Delta " + unprojDeltaCoords.y);
        float oldX = cameraHelper.getPosition().x;
        float oldY = cameraHelper.getPosition().y;
        cameraHelper.setPosition(oldX + (delta.x), oldY + (delta.y));
        return super.pan(x, y, deltaX, deltaY);
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        Gdx.app.debug(TAG, "Zoom event: " + initialDistance + " " + distance);
        float zoomDelta = initialDistance - distance;
        float maxDim = Math.max(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float zoomed = zoomDelta / maxDim;
        this.cameraHelper.addZoom(zoomed / 10);
        return super.zoom(initialDistance, distance);
    }
}

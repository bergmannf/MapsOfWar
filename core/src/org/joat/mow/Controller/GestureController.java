package org.joat.mow.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import org.joat.mow.CameraHelper;
import org.joat.mow.Constants;

/**
 * Created by florian on 18/05/14.
 */
public class GestureController extends GestureDetector.GestureAdapter {
    public static final String TAG = GestureController.class.getName();
    private CameraHelper cameraHelper;

    /**
     * This controller only controls zoom and move gestures and applies them to the
     * cameraHelper.
     * @param cameraHelper The cameraHelper that will be modified.
     */
    public GestureController(CameraHelper cameraHelper) {
        this.cameraHelper = cameraHelper;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        float oldX = cameraHelper.getPosition().x;
        float oldY = cameraHelper.getPosition().y;
        cameraHelper.setPosition(oldX + ((-1) * deltaX / Constants.VIEWPORT_WIDTH), oldY + (deltaY / Constants.VIEWPORT_HEIGHT));
        return super.pan(x, y, deltaX, deltaY);
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        Gdx.app.debug(TAG, "Zoom event: " + initialDistance + " " + distance);
        float zoomDelta = initialDistance - distance;
        float maxDim = Math.max(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float zoomed = zoomDelta / maxDim;
        this.cameraHelper.addZoom(zoomed);
        return super.zoom(initialDistance, distance);
    }
}

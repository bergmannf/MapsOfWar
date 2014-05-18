package org.joat.mow;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Helper class for camera controls:
 * - allows tracking of an object.
 * - allows zooming of a camera.
 * - allows movement of a camera.
 */
public class CameraHelper {
    private static final String TAG = CameraHelper.class.getName();
    private final float MAX_ZOOM_IN = 0.25f;
    private final float MAX_ZOOM_OUT = 10.0f;

    private final Vector2 position;
    private float zoom;
    private Sprite target;

    public CameraHelper() {
        this.position = new Vector2(Constants.VIEWPORT_WIDTH / 2f, Constants.VIEWPORT_HEIGHT / 2f);
        this.zoom = 1.0f;
    }

    public void update(float deltaTime) {
        if (!hasTarget()) return;
        position.x = target.getX() + target.getOriginX();
        position.y = target.getY() + target.getOriginY();
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public void addZoom(float amount) {
        setZoom(zoom + amount);
    }

    public Sprite getTarget() {
        return target;
    }

    public void setTarget(Sprite target) {
        this.target = target;
    }

    public boolean hasTarget() {
        return this.target != null;
    }

    public boolean hasTarget(Sprite target) {
        return hasTarget() && this.target.equals(target);
    }

    public void applyTo(OrthographicCamera camera) {
        camera.position.x = this.position.x;
        camera.position.y = this.position.y;
        camera.zoom = zoom;
        camera.update();
    }
}

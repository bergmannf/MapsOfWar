package org.joat.mow.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import org.joat.mow.Model.AbstractGameObject;
import org.joat.mow.Model.Map;

/**
 * Created by florian on 10/05/14.
 */
public class ActorController extends InputAdapter {
    public static final String TAG = ActorController.class.getName();
    private Map map;
    private Camera activeCamera;

    public ActorController(Camera camera, Map map) {
        this.map = map;
        this.activeCamera = camera;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Gdx.app.debug(TAG, "Touch coordinates: " + screenX + " " + screenY);
        Vector3 coordinates = new Vector3(screenX, screenY, 0);
        Vector3 unprojected = activeCamera.unproject(coordinates);
        Gdx.app.debug(TAG, "Unprojected Touch coordinates: " + unprojected.x + " " + unprojected.y);
        for (AbstractGameObject actor : map.getActors()) {
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }
}

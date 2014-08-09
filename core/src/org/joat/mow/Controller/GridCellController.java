package org.joat.mow.Controller;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

import org.joat.mow.Model.Cell;
import org.joat.mow.Model.GameObject;
import org.joat.mow.Model.Map;

/**
 * Created by florian on 10/05/14.
 */
public class GridCellController extends InputAdapter {
    public static final String TAG = GridCellController.class.getName();
    private GameObject objectToPlace;
    private final Map map;

    /**
     * Create a new controller for a given map and a given camera.
     *
     * @param activeCamera
     * @param map
     */
    public GridCellController(Camera activeCamera, Map map) {
        this.activeCamera = activeCamera;
        this.map = map;
    }

    /**
     * The camera is needed to unproject coordinates.
     */
    private Camera activeCamera;

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 coordinates = new Vector3(screenX, screenY, 0);
        Vector3 unprojected = activeCamera.unproject(coordinates);
        for (Cell cell : map.getGrid().getNodes()) {
            cell.selected = cell.hit(unprojected.x, unprojected.y);
            if (cell.selected && null != objectToPlace) {
                map.addActor(objectToPlace, cell);
                objectToPlace = null;
            }
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }

	public void setObjectToPlace(GameObject o) {
		this.objectToPlace = o;
	}
}

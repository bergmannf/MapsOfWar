package org.joat.mow.Model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by florian on 10/05/14.
 */
public class Map {
    private GameObject selectedActor;
    private List<GameObject> actors;
    private Grid grid;

    public Map() {
        grid = new Grid(25, 25);
        actors = new ArrayList<GameObject>();

        GameObject a = new GameObject("units-fighters/HumanFighter");
        a.position = new Vector2(0, 0);
        actors.add(a);
        a = new GameObject("units-fighters/HumanFighter2");
        a.position = new Vector2(4, 4);
        actors.add(a);
    }

    public GameObject getSelectedActor() {
        return selectedActor;
    }

    public void setSelectedActor(GameObject selectedActor) {
        this.selectedActor = selectedActor;
    }

    public Grid getGrid() {
        return grid;
    }

    public List<GameObject> getActors() {
        return actors;
    }

	public void addActor(GameObject newActor, Cell c) {
		newActor.position = c.position;
		actors.add(newActor);
	}
}

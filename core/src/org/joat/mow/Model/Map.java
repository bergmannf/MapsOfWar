package org.joat.mow.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by florian on 10/05/14.
 */
public class Map {
    private GameObject selectedActor;
    private List<GameObject> actors;
    private Grid grid;
    private String backgroundImage;

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

    public Collection<Cell> reachableCells() {
        if (selectedActor == null) { return new ArrayList<Cell>(); }
        else {
            int x = selectedActor.getX();
            int y = selectedActor.getY();
            Cell c = grid.getCell(x, y);
            return grid.reachableCells(c, selectedActor.getAvailableSteps());
        }
    }

	public void addActor(GameObject newActor, Cell c) {
		newActor.position = c.position;
		actors.add(newActor);
	}
}

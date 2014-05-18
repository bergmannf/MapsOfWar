package org.joat.mow.Model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by florian on 10/05/14.
 */
public class Map {
    private GameObject selectedActor;
    private GameObject[] actors;
    private Grid grid;

    public Map() {
        grid = new Grid(5, 5);
        actors = new GameObject[2];
        actors[0] = new GameObject("HumanFighter");
        actors[0].position = new Vector2(0, 0);
        actors[1] = new GameObject("HumanFighter2");
        actors[1].position = new Vector2(4, 4);
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

    public GameObject[] getActors() {
        return actors;
    }
}

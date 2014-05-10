package org.joat.mow.Model;

/**
 * Created by florian on 10/05/14.
 */
public class Map {
    private AbstractGameObject[] actors;
    private Grid grid;

    public Map() {
        actors = new AbstractGameObject[1];
        grid = new Grid(5, 5);
    }

    public Grid getGrid() {
        return grid;
    }

    public AbstractGameObject[] getActors() {
        return actors;
    }
}

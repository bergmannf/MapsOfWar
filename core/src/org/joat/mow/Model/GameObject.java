package org.joat.mow.Model;

import com.badlogic.gdx.math.Vector2;

/**
 * A (default) game object that represents an actor in the model part
 * of the application.
 */
public class GameObject extends AbstractGameObject {

    private int availableSteps;

    public GameObject(String spriteName) {
        this.spriteName = spriteName;
        this.dimension = new Vector2(1, 1);
        this.scale = new Vector2(1, 1);
        this.availableSteps = 8;
    }
}

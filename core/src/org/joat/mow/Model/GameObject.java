package org.joat.mow.Model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by florian on 14/05/14.
 */
public class GameObject extends AbstractGameObject {

    public GameObject(String spriteName) {
        this.spriteName = spriteName;
        this.dimension = new Vector2(1, 1);
        this.scale = new Vector2(1, 1);
    }
}

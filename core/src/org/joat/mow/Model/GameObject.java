package org.joat.mow.Model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by florian on 14/05/14.
 */
public class GameObject extends AbstractGameObject {

    public GameObject(String spriteName) {
        this.spriteName = spriteName;
        this.dimension = new Vector2(1, 1);
        this.scale = new Vector2(1, 1);
        this.initGraphics();
    }

    @Override
    public void render(SpriteBatch batch) {
        assert this.sprite != null;
        float sizeX = this.dimension.x * this.scale.x;
        float sizeY = this.dimension.y * this.scale.y;
        batch.draw(sprite, this.position.x, this.position.y, sizeX, sizeY);
    }
}

package org.joat.mow.Model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by florian on 08/05/14.
 */
public abstract class AbstractGameObject {
    public Vector2 position;
    public Vector2 dimension;
    public Vector2 origin;
    public Vector2 scale;
    public float rotation;
    public boolean selected;

    public AbstractGameObject() {
        this.position = new Vector2();
        this.dimension = new Vector2();
        this.origin = new Vector2();
        this.scale = new Vector2(1, 1);
        this.rotation = 0;
    }

    public void update(float deltaTime) {}

    public abstract void render(SpriteBatch batch);

    public boolean hit(float x, float y) {
        int xLow = (int) this.position.x;
        int xHigh = (int) (this.position.x + this.scale.x);
        int yLow = (int) this.position.y;
        int yHigh = (int) (this.position.y + this.scale.y);
        if (xLow < x && x < xHigh) {
            if (yLow < y && y < yHigh) {
                return true;
            }
        }
        return false;
    }
}

package org.joat.mow.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Cell extends AbstractGameObject {
    public static final String TAG = Cell.class.getName();

    private boolean blocked;
    private boolean blocksSight;

    public Cell(int x, int y) {
        super();
        this.position = new Vector2(x, y);
        this.dimension = new Vector2(1, 1);
        this.blocked = false;
        this.blocksSight = false;
        this.spriteName = "GridBorder";
    }

    public int getX() {
        final int x = (int) Math.floor(this.position.x);
        return x;
    }

    public int getY() {
        final int x = (int) Math.floor(this.position.y);
        return x;
    }

    @Override
    public String toString() {
        return "Cell (X: " + this.getX() + " Y: " + this.getY() + ")";
    }
}

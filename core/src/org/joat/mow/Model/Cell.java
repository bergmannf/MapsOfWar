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
}

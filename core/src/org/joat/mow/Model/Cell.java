package org.joat.mow.Model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Cell extends AbstractGameObject {
    public static final String TAG = Cell.class.getName();

    private Sprite cellSprite;
    private Pixmap gridImage;

    public Sprite getCellSprite() {
        return cellSprite;
    }


    public Cell(int x, int y) {
        this.position = new Vector2(x, y);
        init();
    }

    public void init() {
        generateSprite();
    }

    @Override
    public void render(SpriteBatch batch) {
        generateSprite();
        this.cellSprite.draw(batch);
    }

    private void generateSprite() {
        this.gridImage = generateGridImage();
        Texture t = new Texture(gridImage);
        this.cellSprite = new Sprite(t);
        this.cellSprite.setSize(1, 1);
        this.cellSprite.setPosition(position.x, position.y);
    }

    private Pixmap generateGridImage() {
        Pixmap p = new Pixmap(124, 124, Pixmap.Format.RGBA8888);
        if (this.selected) {
            p.setColor(1, 1, 0, 0.75f);
            p.fillRectangle(0, 0, 124, 124);
        } else {
            p.setColor(Color.BLACK);
            p.drawRectangle(0, 0, 124, 124);
        }
        return p;
    }

}

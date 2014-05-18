package org.joat.mow.Model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Cell extends AbstractGameObject {
    public static final String TAG = Cell.class.getName();

    private Sprite defaultCellSprite;
    private Sprite selectedCellSprite;
    private boolean blocked;
    private boolean blocksSight;

    public Sprite getDefaultCellSprite() {
        return defaultCellSprite;
    }


    public Cell(int x, int y) {
        this.position = new Vector2(x, y);
        this.blocked = false;
        this.blocksSight = false;
        init();
    }

    public void init() {
        generateSprite();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (selected) {
            this.selectedCellSprite.draw(batch);
        } else {
            this.defaultCellSprite.draw(batch);
        }
    }

    private void generateSprite() {
        Pixmap gridImage = generateGridImage();
        Texture t = new Texture(gridImage);
        this.defaultCellSprite = new Sprite(t);
        this.defaultCellSprite.setSize(1, 1);
        this.defaultCellSprite.setPosition(position.x, position.y);

        gridImage = generateGridImageSelected();
        t = new Texture(gridImage);
        this.selectedCellSprite = new Sprite(t);
        this.selectedCellSprite.setSize(1, 1);
        this.selectedCellSprite.setPosition(position.x, position.y);
    }

    private Pixmap generateGridImage() {
        Pixmap unselected = new Pixmap(124, 124, Pixmap.Format.RGBA8888);
        unselected.setColor(Color.BLACK);
        unselected.drawRectangle(0, 0, 124, 124);
        return unselected;
    }

    private Pixmap generateGridImageSelected() {
        Pixmap selected = new Pixmap(124, 124, Pixmap.Format.RGBA8888);
        selected.setColor(1, 1, 0, 0.75f);
        selected.fillRectangle(0, 0, 124, 124);
        return selected;
    }

}

package org.joat.mow.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Cell extends AbstractGameObject {
    public static final String TAG = Cell.class.getName();

    private Sprite selectedCellSprite;
    private boolean blocked;
    private boolean blocksSight;

    public Cell(int x, int y) {
        super();
        this.position = new Vector2(x, y);
        this.dimension = new Vector2(1, 1);
        this.blocked = false;
        this.blocksSight = false;
        this.spriteName = "GridBorder";
        initGraphics();
    }

    public void init() {
        generateSprite();
    }

    @Override
    public void render(SpriteBatch batch) {
        Color c = new Color(batch.getColor());
        if (selected) {
            batch.setColor(1, 1, 0, 1);
        } else {
            batch.setColor(0, 0, 0, 1);
        }
        batch.draw(this.sprite, this.position.x, this.position.y, this.dimension.x, this.dimension.y);
        batch.setColor(c);
    }

    private void generateSprite() {
        Gdx.files.internal(spriteName);
        Pixmap gridImage = generateGridImage();
        Texture t = new Texture(gridImage);
        this.sprite = new Sprite(t);
        this.sprite.setSize(1, 1);
        this.sprite.setPosition(position.x, position.y);

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

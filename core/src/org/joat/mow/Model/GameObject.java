package org.joat.mow.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.joat.mow.Assets;

/**
 * Created by florian on 14/05/14.
 */
public class GameObject extends AbstractGameObject {
    private String spriteName;

    public Sprite getSprite() {
        return sprite;
    }

    private Sprite sprite;

    public GameObject(String spriteName) {
        this.spriteName = spriteName;
        this.dimension = new Vector2(1, 1);
        this.scale = new Vector2(1, 1);
        this.initGraphics();
    }

    @Override
    public void render(SpriteBatch batch) {
        assert this.sprite != null;
        batch.draw(sprite, this.position.x, this.position.y, this.dimension.x, this.dimension.y);
    }

    private void initGraphics() {
        TextureAtlas atlas = Assets.instance.getAtlas();
        TextureRegion region = atlas.findRegion(spriteName);
        if (region != null) {
            Sprite s = new Sprite(region);
            s.setScale(this.scale.x, this.scale.y);
            this.sprite = s;
        } else {
            throw new IllegalArgumentException("sprite does not exist " + this.spriteName);
        }
    }

}

package org.joat.mow.Views;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.joat.mow.Assets;
import org.joat.mow.Model.AbstractGameObject;

/**
 * Renders a sprite to a given location.
 */
public class SpriteRenderer {

    public void render(SpriteBatch batch, AbstractGameObject o) {
        final Vector2 position = o.position;
        final Vector2 dimension = o.dimension;
        final Sprite sprite = initGraphics(o);
        batch.draw(sprite, position.x, position.y, dimension.x, dimension.y);
    }

    protected Sprite initGraphics(AbstractGameObject o) {
        TextureAtlas atlas = Assets.instance.getUnitSpriteAtlas();
        TextureRegion region = atlas.findRegion(o.getSpriteName());
        if (region != null) {
            Sprite s = new Sprite(region);
            s.setScale(o.scale.x, o.scale.y);
            return s;
        } else {
            throw new IllegalArgumentException("sprite does not exist "
                    + o.getSpriteName());
        }
    }

}

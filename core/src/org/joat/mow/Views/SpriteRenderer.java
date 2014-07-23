package org.joat.mow.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.joat.mow.Assets;
import org.joat.mow.Model.AbstractGameObject;
import sun.misc.Cache;

import java.util.HashMap;

/**
 * Renders a sprite to a given location.
 */
public class SpriteRenderer {
    private final String TAG = SpriteRenderer.class.getName();
    private final HashMap<String, Sprite> spriteCache;

    public SpriteRenderer() {
        spriteCache = new HashMap<String, Sprite>();
    }

    public void render(SpriteBatch batch, AbstractGameObject o) {
        final Vector2 position = o.position;
        final Vector2 dimension = o.dimension;
        final Sprite sprite = getSprite(o);
        batch.draw(sprite, position.x, position.y, dimension.x, dimension.y);
    }

    protected Sprite getSprite(AbstractGameObject o) {
        Sprite sprite;
        final String spriteKey = o.getSpriteName();
        sprite = spriteCache.get(spriteKey);
        if (sprite != null) {
            Gdx.app.log(TAG, "Cache hit for sprite " + spriteKey);
            return sprite;
        }
        TextureAtlas atlas = Assets.instance.getUnitSpriteAtlas();
        TextureRegion region = atlas.findRegion(o.getSpriteName());
        if (region != null) {
            sprite = new Sprite(region);
            sprite.setScale(o.scale.x, o.scale.y);
            spriteCache.put(spriteKey, sprite);
            return sprite;
        } else {
            throw new IllegalArgumentException("sprite does not exist "
                    + o.getSpriteName());
        }
    }

}

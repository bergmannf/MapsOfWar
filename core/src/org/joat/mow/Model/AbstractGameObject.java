package org.joat.mow.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.joat.mow.Assets;

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
	protected Sprite sprite;
	protected String spriteName;

	public AbstractGameObject() {
		this.position = new Vector2();
		this.dimension = new Vector2();
		this.origin = new Vector2();
		this.scale = new Vector2(1, 1);
		this.rotation = 0;
	}

	public void setSpriteName(String spriteName) {
		this.spriteName = spriteName;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void update(float deltaTime) {
	}

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

	protected void initGraphics() {
		TextureAtlas atlas = Assets.instance.getUnitSpriteAtlas();
		TextureRegion region = atlas.findRegion(spriteName);
		if (region != null) {
			Sprite s = new Sprite(region);
			s.setScale(this.scale.x, this.scale.y);
			this.sprite = s;
		} else {
			throw new IllegalArgumentException("sprite does not exist "
					+ this.spriteName);
		}
	}

}

package org.joat.mow.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import org.joat.mow.Assets;
import org.joat.mow.Controller.WorldController;

/**
 * This UI will allow the placement of units on the map.
 * 
 * @author florian
 * 
 */
public class PlacementUI implements Disposable {
	private static final String TAG = PlacementUI.class.getName();
	private Stage stage;
	private Skin skin;
	private InputMultiplexer multiplexer;
	private WorldController worldController;

	public PlacementUI(WorldController controller) {
		this.worldController = controller;
		init();
	}

	@Override
	public void dispose() {
		multiplexer.removeProcessor(stage);
	}

	/**
	 * Reinitializes the placement view.
	 */
	public final void init() {
		try {
			this.multiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
		} catch (ClassCastException e) {
			throw new IllegalStateException("No InputMultiplexer found.");
		}
		this.skin = Assets.instance.getSkin();
		this.stage = new Stage();
		final Window window = new Window("Units", skin);
		window.setClip(true);
		addButtons(window);
		window.pack();
		window.debugTable();
		this.stage.addActor(window);
		this.multiplexer.addProcessor(0, this.stage);
	}

	private void addButtons(final Window window) {
		TextureAtlas atlas = Assets.instance.getUnitSpriteAtlas();
		for (final AtlasRegion r : atlas.getRegions()){
			if (!r.name.startsWith("unit")) { continue; }
			TextureRegionDrawable d = new TextureRegionDrawable(r);
			ImageButton b = new ImageButton(d);
			b.setColor(1, 0, 0, 1);
			b.addListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {
					worldController.newActor(r.name);
				}
				
			});
			Cell<ImageButton> c = window.add(b);
			c.width(64);
			c.height(64);
		}
	}

	public void render() {
		this.stage.act();
		this.stage.draw();
	}

	public void resize(int width, int height) {
		this.stage.getViewport().update(width, height, true);
	}

}

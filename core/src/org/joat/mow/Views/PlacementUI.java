package org.joat.mow.Views;

import org.joat.mow.Assets;
import org.joat.mow.Controller.WorldController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;

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
		Button b = new TextButton("Unit", skin);
		ChangeListener l = new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				worldController.newActor("HumanFighter");
			}
			
		};
		b.addListener(l);
		window.add(b);
		window.row();
		window.pack();
		window.debugTable();
		this.stage.addActor(window);
		this.multiplexer.addProcessor(0, this.stage);
	}

	public void render() {
		this.stage.act();
		this.stage.draw();
	}

	public void resize(int width, int height) {
		this.stage.getViewport().update(width, height, true);
	}

}

package org.joat.mow;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MapsOfWar extends ApplicationAdapter {

	private static final String TAG = MapsOfWar.class.getName();
	private boolean paused;
	private WorldController worldController;
	private WorldRenderer worldRenderer;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Gdx.app.LOG_DEBUG);
		this.worldController = new WorldController();
		this.worldRenderer = new WorldRenderer(worldController);
		this.paused = false;
	}

	@Override
	public void pause() {
		this.paused = true;
	}

	@Override
	public void resume() {
		this.paused = false;
	}

	@Override
	public void render() {
		if (!this.paused) {
			this.worldController.update(Gdx.graphics.getDeltaTime());
		}
		Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.worldRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		this.worldRenderer.resize(width, height);
	}

	@Override
	public void dispose() {
		this.worldRenderer.dispose();
	}
}

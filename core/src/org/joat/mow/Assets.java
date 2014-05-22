/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.joat.mow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

/**
 * Manages the assets available in the game.
 * TODO: how to handle assets that can be added by users?
 *
 * @author florian
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    private AssetManager assetManager;
    private TextureAtlas unitSpriteAtlas;
    private TextureAtlas uiSpriteAtlas;
    private Skin uiSkin;

    private Assets() {
    }

    @Override
    public void dispose() {
        this.assetManager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Could not load asset: '" + asset + "'" + throwable);
    }

    public TextureAtlas getUnitSpriteAtlas() {
        return unitSpriteAtlas;
    }

    public TextureAtlas getUiSpriteAtlas() {
		return uiSpriteAtlas;
	}

	public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        this.assetManager.setErrorListener(this);
        this.assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        this.assetManager.finishLoading();
        Gdx.app.debug(TAG, "Loaded #" + this.assetManager.getAssetNames().size + "assets");
        for (String a : this.assetManager.getAssetNames()) {
            Gdx.app.debug(TAG, "Asset:" + a);
        }
        this.unitSpriteAtlas = this.assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        this.setUiSpriteAtlas(new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas")));
        this.uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        this.uiSkin.addRegions(this.getUiSpriteAtlas());
    }

	public void setUiSpriteAtlas(TextureAtlas uiSpriteAtlas) {
		this.uiSpriteAtlas = uiSpriteAtlas;
	}

	public Skin getSkin() {
		return this.uiSkin;
	}
}

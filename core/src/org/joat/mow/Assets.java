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

    private Assets() {
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
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Could not load asset: '" + asset + "'" + throwable);
    }

    @Override
    public void dispose() {
        this.assetManager.dispose();
    }
}

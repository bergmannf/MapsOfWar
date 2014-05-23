package org.joat.mow.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import org.joat.mow.MapsOfWar;

public class DesktopLauncher {

	private static boolean rebuildAtlas = false;
	private static boolean drawDebugOutline = true;

	public static void main(String[] arg) {
		if (rebuildAtlas) {
			String atlasTarget = "./images";
			Settings settings = new Settings();
			settings.maxWidth = 512;
			settings.maxHeight = 512;
			settings.debug = drawDebugOutline;
			settings.combineSubdirectories = true;
			TexturePacker.process(settings, 
				"../assets-raw/images",
				"./images",
				"MapsOfWar.pack");
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SampleApplication";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new MapsOfWar(), config);
	}
}

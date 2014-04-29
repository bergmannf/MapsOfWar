package org.joat.mow.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.joat.mow.MapsOfWar;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "SampleApplication";
        config.width = 800;
        config.height = 480;
		new LwjglApplication(new MapsOfWar(), config);
	}
}

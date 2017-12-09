package com.sulient.pixelvoyager;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sulient.pixelvoyager.Main;
import com.sulient.pixelvoyager.tools.DesktopGoogleServices;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Pixel Voyager";
		config.vSyncEnabled = true;
		config.width = 540;
		config.height = 960;
		new LwjglApplication(new Main(new DesktopGoogleServices()), config);
	}
}

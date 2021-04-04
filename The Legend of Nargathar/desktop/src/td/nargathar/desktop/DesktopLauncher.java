package td.nargathar.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import td.nargathar_remake.MainMenu;
import td.nargathar_remake.Nargathar;

public class DesktopLauncher {
	private static final int WINDOW_HEIGHT = 480;
	private static final int WINDOW_WIDTH = 480;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "The Legend of Nargathar";
		config.width = WINDOW_HEIGHT;
		config.height = WINDOW_WIDTH;
		//config.resizable = false;
		new LwjglApplication(new Nargathar(), config);
		//new LwjglApplication(new MainMenu(), config);
	}
}
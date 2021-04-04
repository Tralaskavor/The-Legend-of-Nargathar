package td.nargathar_remake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenu extends ApplicationAdapter implements Screen {
	
	private static final int EXIT_BUTTON_WIDTH = 100;
	private static final int EXIT_BUTTON_HEIGHT = 50;
	private static final int PLAY_BUTTON_WIDTH = 100;
	private static final int PLAY_BUTTON_HEIGHT = 50;
	private static final int EXIT_BUTTON_Y = 50;
	private static final int PLAY_BUTTON_Y = 100;
	
	
	Texture playButtonActive;
	Texture playButtonInactive;
	Texture exitButtonActive;
	Texture exitButtonInactive;
	
	Nargathar game;
	
	public MainMenu(Nargathar game) {
		this.game = game;
		playButtonActive = new Texture("playButton_active.png");
		playButtonInactive = new Texture("playButton.png");
		exitButtonActive = new Texture("exitButton_active.png");
		exitButtonInactive = new Texture("exitButton.png");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

package td.nargathar_remake;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Nargathar extends ApplicationAdapter {
	
	OrthographicCamera cam;
	SpriteBatch batch;
	TiledGameMap firstMap, secondMap;
	MainMenu mainmenu;
	private Music map1Music; // Music for long tracks, Sound for short
	
	@Override
	public void create () {
		
		// game music (doesn't loop?)
		map1Music = Gdx.audio.newMusic(Gdx.files.internal("in-the-city.ogg"));
		map1Music.setLooping(true);
		map1Music.play();
		
		batch = new SpriteBatch();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		
		firstMap = new TiledGameMap();

	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cam.update();
		firstMap.update(Gdx.graphics.getDeltaTime());
		firstMap.render(cam, batch);
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			System.exit(0);
		}
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		firstMap.dispose();
	}

}

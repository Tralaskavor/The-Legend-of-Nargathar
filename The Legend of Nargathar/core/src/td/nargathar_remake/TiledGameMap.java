package td.nargathar_remake;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;

public class TiledGameMap extends GameMap {
	
	public TiledGameMap () {
		
		// entity creation
		entities = new ArrayList<Entity>();
		entities.add(new Player(40, 200, this)); // entity start location on map (x, y)
		entities.add(new PlayerShadow(80, 200, this));
		tiledMap = new TmxMapLoader().load("map1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		// ANIMATED TILES THAT DON'T WORK YET
		Array<StaticTiledMapTile> frameTiles = new Array<StaticTiledMapTile>(2);
		
		// iterates through all tiles in the "testtiles" tileset
		Iterator<TiledMapTile> tiles = tiledMap.getTileSets().getTileSet("testtiles2").iterator();
		
		// check all tiles in "testtiles" for animation key and flower value
		while(tiles.hasNext()) {
			TiledMapTile tile = tiles.next();
			if(tile.getProperties().containsKey("animated") && tile.getProperties().get("animated", String.class).equals("flower"))
				frameTiles.add((StaticTiledMapTile)tile);
		}
		
		// create animated tile
		AnimatedTiledMapTile animatedTile = new AnimatedTiledMapTile(2/3f, frameTiles);
		
		// create layer from Tiled background to iterate through
		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("background");
		
		animateCells(layer, animatedTile);
		
	}
	
	// iterate through all x and y position cells, check for animated key and flower value, replace static tile with animated tile
	public void animateCells(TiledMapTileLayer layer, AnimatedTiledMapTile animatedTile) {
		for(int x = 0; x < layer.getWidth(); x++) {
			System.out.println("Checking x coordinates in background layer...");
			for(int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				if(cell.getTile().getProperties().containsKey("animated") && cell.getTile().getProperties().get("animated", String.class).equals("flower")) {
					cell.setTile(animatedTile);
				}
			}
		}
	}

	@Override
	public void show() {

	}

	@Override
	public void render(OrthographicCamera camera, SpriteBatch batch) {
		tiledMapRenderer.setView(camera); // tells renderer what camera to use
		tiledMapRenderer.render();
		
		AnimatedTiledMapTile.updateAnimationBaseTime();
		//tiledMapRenderer.render(new int[] {0});
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		super.render(camera, batch); // renders entities
		batch.end();
		//tiledMapRenderer.render(new int[] {1});
	}

	@Override
	public void update(float delta) {
		super.update(delta);
	}

	@Override
	public void dispose() {
		tiledMap.dispose();
		// testMap.dispose();
	}

	// return tile type based on Tiled cell
	public TileType getTileTypeByCoordinate(int layer, int col, int row) {
		Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col,  row);
		if (cell != null) {
			TiledMapTile tile = cell.getTile();
			if (tile != null) {
				int id = tile.getId();
				return TileType.getTileTypeById(id); // get tile object based on integer id
			}
		}
		return null;
	}

	@Override
	public int getWidth() {
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
	}

	@Override
	public int getLayers() {
		// TODO Auto-generated method stub
		return tiledMap.getLayers().getCount();
	}
	
	@Override
	public void changeMap(int map) {
		Gdx.app.postRunnable(() -> {
//			entities.clear(); // reset entities to initial coordinates on map change
//			entities = new ArrayList<Entity>();
//			entities.add(new Player(40, 200, this));
//			entities.add(new PlayerShadow(80, 200, this));
			tiledMap.dispose();
			tiledMapRenderer.getMap().dispose();
			tiledMap = new TmxMapLoader().load("map" + Integer.toString(map) + ".tmx");
			tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
			if (map == 99) {
				entities.clear();
			}
		});
	}
}

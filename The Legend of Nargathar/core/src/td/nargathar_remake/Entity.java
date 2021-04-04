package td.nargathar_remake;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	//TiledGameMap newMap;
	
	protected Vector2 pos; // Vector2 keeps track of x and y coordinates
	protected EntityType type;
	protected float velocityY = 0;
	protected TiledGameMap map;
	protected boolean grounded = false;
	protected boolean impact = false;
	private Sound deathSound, teleportSound, impactSound, explodeSound;
	static int nextMap = 2;
	static boolean gameOver = false;
	
	public Entity(float x, float y, EntityType type, TiledGameMap map) {
		this.pos = new Vector2(x, y);
		this.type = type;
		this.map = map;
	}
	
	public void update(float deltaTime, float gravity) {
		deathSound = Gdx.audio.newSound(Gdx.files.internal("death.wav"));
		impactSound = Gdx.audio.newSound(Gdx.files.internal("impact.ogg"));
		float newY = pos.y;
		this.velocityY += gravity * deltaTime * getWeight();
		newY += this.velocityY * deltaTime;
		
		// collision detection
		if (map.doesRectCollideWithMap(pos.x, newY, getWidth(), getHeight())) {
			if (velocityY < 0) {
				this.pos.y = (float) Math.floor(pos.y);
				grounded = true;
			}
			this.velocityY = 0;
		} else {
			this.pos.y = newY;
			grounded = false;
			impact = true;
		}
		
		// if entity collides with hazardous tile, load game over screen
		if (map.doesRectKillPlayer(pos.x, newY, getWidth(), getHeight())) {
			gameOver = true;
			deathSound.play();
			map.changeMap(99);
		}
	}
	
	public abstract void render (SpriteBatch batch);
	
	// checks collisions on x axis
	protected void moveX(float amount) {
		float newX = this.pos.x + amount;
		if (!map.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight())){
			this.pos.x = newX;
		}
	}

	public Vector2 getPos() {
		return pos;
	}
	
	public float getX() {
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}

	public EntityType getType() {
		return type;
	}

	public boolean isGrounded() {
		return grounded;
	}
	
	public int getWidth() {
		return type.getWidth();
	}
	
	public int getHeight() {
		return type.getHeight();
	}
	
	public float getWeight() {
		return type.getHeight();
	}

}

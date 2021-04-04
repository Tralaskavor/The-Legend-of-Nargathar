package td.nargathar_remake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import td.nargathar_remake.GameMap;

public class Player extends Entity {
	
	private static final int SPEED = 120;
	private static final int JUMP_VELOCITY = 5;
	private Sound jumpSound2;
	Texture image;
	
	private SpriteBatch batch;
	private float elapsedTime = 0;
	private Sound deathSound, teleportSound, impactSound, magicSound;
	
	public Player (float x, float y, TiledGameMap map) {
		super(x, y, EntityType.PLAYER, map);
		image = new Texture("hero.png");
	}
	
	@Override
	public void update(float deltaTime, float gravity) {
		jumpSound2 = Gdx.audio.newSound(Gdx.files.internal("jump2.ogg"));
		teleportSound = Gdx.audio.newSound(Gdx.files.internal("teleport.wav"));
		magicSound = Gdx.audio.newSound(Gdx.files.internal("blessing.wav"));
		if (Gdx.input.isKeyPressed(Keys.SPACE) && grounded) {
			this.velocityY += JUMP_VELOCITY * getWeight();
			jumpSound2.play();
			grounded = false;
		} else if (Gdx.input.isKeyPressed(Keys.SPACE) && !grounded && this.velocityY > 0){
			this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;
		}
		super.update(deltaTime, gravity); // applies gravity
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			moveX(-SPEED * deltaTime);
				image = new Texture("hero_left.png");
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			moveX(SPEED * deltaTime);
				image = new Texture("hero.png");
		}
		float newY = pos.y;
		newY += this.velocityY * deltaTime;
		// if entity jumps on angel, magic tiles are created (but really just loads next map)
		if (map.doesRectMagicMap(pos.x, newY, getWidth(), getHeight())) {
			magicSound.play();
			System.out.println("Current Map MAGIC'D to = " + super.nextMap);
			map.changeMap(super.nextMap);
			super.nextMap++;
		}
		
		// if entity collides with teleport tile, load next map
		if (map.doesRectChangeMap(pos.x, newY, getWidth(), getHeight())) {
			teleportSound.play();
			System.out.println("Current Map CHANGED to = " + nextMap);
			map.changeMap(super.nextMap);
			//nextMap++;
			super.nextMap++;

		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
	}
	
	public void dispose(){
//		batch.dispose();
//		textureAtlas.dispose();
	}

}

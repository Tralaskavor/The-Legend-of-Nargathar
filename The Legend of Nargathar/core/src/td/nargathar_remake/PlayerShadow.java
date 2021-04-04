package td.nargathar_remake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import td.nargathar_remake.GameMap;

public class PlayerShadow extends Entity{
	
	private static final int SPEED = 120;
	private static final int JUMP_VELOCITY = 5;
	private Sound jumpSound2, explodeSound, teleportSound;
	Texture image;
	
	public PlayerShadow (float x, float y, TiledGameMap map) {
		super(x, y, EntityType.PLAYERSHADOW, map);
		image = new Texture("goblin.png");
	}
	
	@Override
	public void update(float deltaTime, float gravity) {
		jumpSound2 = Gdx.audio.newSound(Gdx.files.internal("jump1.ogg"));
		explodeSound = Gdx.audio.newSound(Gdx.files.internal("explode1.wav"));
		teleportSound = Gdx.audio.newSound(Gdx.files.internal("teleport.wav"));
		if (Gdx.input.isKeyPressed(Keys.SPACE) && grounded) {
			this.velocityY += JUMP_VELOCITY * getWeight();
			jumpSound2.play();
		} else if (Gdx.input.isKeyPressed(Keys.SPACE) && !grounded && this.velocityY > 0){
			this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;
		}
		super.update(deltaTime, gravity); // applies gravity
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			moveX(-SPEED * deltaTime);
			image = new Texture("goblin_left.png");
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			moveX(SPEED * deltaTime);
			image = new Texture("goblin.png");
		}
		
		float newY = pos.y;
		newY += this.velocityY * deltaTime;
		// if entity jumps on igniter, map changes (but only in that it removes dynamite blocks!)
		if (map.doesRectExplodeMap(pos.x, newY, getWidth(), getHeight())) {
			explodeSound.play();
			System.out.println("Current Map EXPLODED to = " + super.nextMap);
			map.changeMap(super.nextMap);
			super.nextMap++;
		}
		// if entity collides with teleport tile, load next map
		if (map.doesRectChangeMap(pos.x, newY, getWidth(), getHeight())) {
			if (super.nextMap == 11) {
				teleportSound.play();
				System.out.println("Current Map CHANGED to = " + nextMap);
				map.changeMap(12);
				super.nextMap++;
			} else {
			teleportSound.play();
			System.out.println("Current Map CHANGED to = " + nextMap);
			map.changeMap(super.nextMap);
			//nextMap++;
			super.nextMap++;
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
	}

}

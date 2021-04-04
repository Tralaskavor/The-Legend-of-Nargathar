package td.nargathar_remake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import td.nargathar_remake.GameMap;

public class Monster extends Entity {
	
	private static final int SPEED = 120;
	private static final int JUMP_VELOCITY = 5;
	private Sound jumpSound2;
	Texture image;
	
	public Monster (float x, float y, TiledGameMap map) {
		super(x, y, EntityType.MONSTER, map);
		image = new Texture("goblin.png");
	}
	
	@Override
	public void update(float deltaTime, float gravity) {
		super.update(deltaTime, gravity); // applies gravity
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
	}

}

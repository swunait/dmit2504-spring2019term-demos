package ca.nait.dmit2504;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class StarfishGame extends Game {

	SpriteBatch batch;
	Texture turtleTexture;
	float turtleX;
	float turtleY;
	Rectangle turtleRectangle;

	Texture starfishTexture;
	float starfishX;
	float starfishY;
	Rectangle starfishRectangle;

	Texture oceanTexture;
	Texture winMessageTexture;

	boolean win;

	@Override
	public void create() {
		batch = new SpriteBatch();

		turtleTexture = new Texture("turtle-1.png");
		turtleX = 20;
		turtleY = 20;
		turtleRectangle = new Rectangle(turtleX, turtleY,
				turtleTexture.getWidth(), turtleTexture.getHeight());

		starfishTexture = new Texture("starfish.png");
		starfishX = 380;
		starfishY= 380;
		starfishRectangle = new Rectangle(starfishX, starfishY,
				starfishTexture.getWidth(), starfishTexture.getHeight());

		oceanTexture = new Texture("water.jpg");
		winMessageTexture = new Texture("you-win.png");

		win = false;
	}

	@Override
	public void render() {
		super.render();

		// check user input
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			turtleX--;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			turtleX++;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			turtleY++;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			turtleY--;
		}

		// update turtle rectangle location
		turtleRectangle.setPosition(turtleX, turtleY);

		// check win condition: turtle must overlap starfish
		if (turtleRectangle.overlaps(starfishRectangle)) {
			win = true;
		}

		// Clear screen
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Draw graphics
		batch.begin();
		batch.draw(oceanTexture, 0 ,0);
		if (!win) {
			batch.draw(starfishTexture, starfishX, starfishY);
		}
		batch.draw(turtleTexture, turtleX, turtleY);
		if (win) {
			batch.draw(winMessageTexture, 180, 180);
		}
		batch.end();
	}

	//	Texture img;
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
}

package ca.nait.dmit2504;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

public abstract class BaseGame extends Game {

    private static BaseGame game;

    public BaseGame() {
        game = this;
    }

    public static void setActiveScreen(BaseScreen s) {
        game.setScreen(s);
    }

    @Override
    public void create() {
        // prepare for multiple classes/stages to receive discrete input
        InputMultiplexer im = new InputMultiplexer();
        Gdx.input.setInputProcessor( im );
    }
}

package ca.nait.dmit2504;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Starfish extends BaseActor {

    boolean collected;

    public Starfish(float x, float y, Stage s) {
        super(x, y, s);

        loadTexture("starfish.png");

        Action spin = Actions.rotateBy(30,1);
        addAction( Actions.forever(spin) );

        setBoundaryPolygon(8);

        collected = false;
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true;

        clearActions();
        addAction( Actions.fadeOut(1) );
        // remove actor from stage
        addAction( Actions.after( Actions.removeActor() ) );
    }

}

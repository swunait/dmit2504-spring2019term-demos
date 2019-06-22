package ca.nait.dmit2504;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Whirlpool extends BaseActor {
    public Whirlpool(float x, float y, Stage s) {
        super(x, y, s);

        loadAnimationFromSheet("whirlpool.png",
                2, 5, 0.5f, false);

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if ( isAnimationFinished() )
            remove();
    }
}

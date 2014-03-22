package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;

public abstract class SpaceManState {

    protected static SpaceMan player;

    protected static void setStateLook(BodyImage stateImage, Shape stateShape) {
        //set image for this state
        player.setImage(stateImage);
        //destroy the old fixture, if there was one
        if (player.fixture != null) {
            player.fixture.destroy();
        }
        player.fixture = new SolidFixture(player, stateShape, 100);
        player.fixture.setFriction(10);
    }

    public abstract void setupState();

    public abstract void teardownState();

    public abstract void walkLeft();

    public abstract void walkRight();

    public abstract void walk();

    public abstract void shoot();

    public abstract void jump();

    public abstract void crouch();

    public abstract void stand();
}

package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import static spaceman.SpaceManState.player;

public final class WalkLeftState extends SpaceManState {

    private final BodyImage stateImage;
    private final Shape stateShape;
    private final SpaceManWalker walker;

    public WalkLeftState(SpaceMan player) {
        WalkLeftState.player = player;
        stateImage = new BodyImage("data/walkleft.gif", 4);
        stateShape = new PolygonShape(-0.08f, 1.61f, -1.28f, 0.09f, -0.95f, -2.0f, 0.88f, -1.99f, 1.36f, -0.88f, 0.72f, 1.37f, 0.46f, 1.59f);
        walker = new SpaceManWalker(player, -WALKSPEED);
    }

    @Override
    public void setupState() {
        if (WalkLeftState.fixture != null) {
            WalkLeftState.fixture.destroy();
        }
        WalkLeftState.fixture = new SolidFixture(player, stateShape);
        WalkLeftState.fixture.setFriction(10);
        player.setImage(stateImage);
        player.getWorld().addStepListener(walker);
    }

    @Override
    public void teardownState() {
        player.getWorld().removeStepListener(walker);
    }

    @Override
    public void walkLeft() {
    }

    @Override
    public void walkRight() {
        player.setNextState(player.walkRightState);
    }

    @Override
    public void walk() {

    }

    @Override
    public void shoot() {

    }

    @Override
    public void jump() {
        player.setNextState(player.jumpLeftState);
    }

    @Override
    public void crouch() {
        player.setNextState(player.crouchLeftState);
    }

    @Override
    public void stand() {
        player.setNextState(player.faceLeftState);
    }

}

package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import static spaceman.SpaceManState.WALKSPEED;

public class WalkRightState extends SpaceManState {

    private final BodyImage stateImage;
    private final Shape stateShape;
    private final SpaceManWalker walker;

    public WalkRightState(SpaceMan player) {
        WalkRightState.player = player;
        stateImage = new BodyImage("data/walkright.gif", 4);
        stateShape = new PolygonShape(-0.08f, 1.61f, -1.28f, 0.09f, -0.95f, -2.0f, 0.88f, -1.99f, 1.36f, -0.88f, 0.72f, 1.37f, 0.46f, 1.59f);
        walker = new SpaceManWalker(player, WALKSPEED);
    }

    @Override
    public void setupState() {
         if (WalkRightState.fixture != null) {
            WalkRightState.fixture.destroy();
        }
        WalkRightState.fixture = new SolidFixture(player, stateShape);
        WalkRightState.fixture.setFriction(10);
        player.setImage(stateImage);
        player.getWorld().addStepListener(walker);
    }

    @Override
    public void teardownState() {
        player.getWorld().removeStepListener(walker);
    }

    @Override
    public void walkLeft() {
        player.setNextState(player.walkLeftState);
    }

    @Override
    public void walkRight() {
    }

    @Override
    public void walk() {

    }

    @Override
    public void shoot() {

    }

    @Override
    public void jump() {
        player.setNextState(player.jumpRightState);
    }

    @Override
    public void crouch() {
        player.setNextState(player.crouchRightState);
    }

    @Override
    public void stand() {
        player.setNextState(player.faceRightState);
    }

}

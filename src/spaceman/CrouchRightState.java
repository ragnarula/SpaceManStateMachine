package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import static spaceman.SpaceManState.player;

public class CrouchRightState extends SpaceManState {

    private static final BodyImage stateImage = new BodyImage("data/crouchright.gif", 4);
    private static final Shape stateShape = new PolygonShape(0.08f, 1.03f, 1.29f, -0.49f, 0.97f, -2.0f, -1.2f, -2.0f, -0.72f, 0.79f, -0.48f, 1.04f);

    public CrouchRightState(SpaceMan player) {
        CrouchRightState.player = player;
    }

    @Override
    public void setupState() {
        setStateLook(stateImage, stateShape);
    }

    @Override
    public void teardownState() {
    }

    @Override
    public void walkLeft() {
        player.setNextState(player.walkLeftState);
    }

    @Override
    public void walkRight() {
        player.setNextState(player.walkRightState);
    }

    @Override
    public void walk() {
        walkRight();
    }

    @Override
    public void shoot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void jump() {
    }

    @Override
    public void crouch() {
    }

    @Override
    public void stand() {
        player.setNextState(player.faceRightState);
    }

    @Override
    public String toString() {
        return "Crouch Right";
    }
}

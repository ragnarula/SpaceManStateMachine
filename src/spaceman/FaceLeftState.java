package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;

final class FaceLeftState extends SpaceManState {

    private final BodyImage stateImage = new BodyImage("data/standleft.gif", 4);
    private final Shape stateShape = new PolygonShape(0.96f, -2.0f, 0.89f, 0.48f, 0.71f, 1.36f, 0.48f, 1.59f, -0.08f, 1.6f, -1.27f, 0.07f, -0.8f, -1.99f);

    public FaceLeftState(SpaceMan player) {
        FaceLeftState.player = player;
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
        walkLeft();
    }

    @Override
    public void shoot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    }

    @Override
    public String toString() {
        return "Face Left";
    }
}

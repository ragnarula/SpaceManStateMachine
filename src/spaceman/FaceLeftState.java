package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import statemachine.StateMachine;

final class FaceLeftState extends SpaceManState {

    private final BodyImage stateImage = new BodyImage("data/standleft.gif", 4);
    private final Shape stateShape = new PolygonShape(0.96f, -2.0f, 0.89f, 0.48f, 0.71f, 1.36f, 0.48f, 1.59f, -0.08f, 1.6f, -1.27f, 0.07f, -0.8f, -1.99f);

    public FaceLeftState(StateMachine<SpaceMan> fsm) {
        super(fsm);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void setup() {
        System.out.println("left up");
        setStateLook(stateImage, stateShape);
    }

    @Override
    protected void teardown() {
        System.out.println("left down");
    }

}

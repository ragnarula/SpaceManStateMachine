package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import statemachine.StateMachine;

final class StandRight extends SpaceManState {

    private static final BodyImage stateImage = new BodyImage("data/standRight.gif", 4);
    private static final Shape stateShape = new PolygonShape(-0.48f, 1.59f, -0.71f, 1.36f, -0.87f, 0.48f, -0.96f, -2.02f, 0.79f, -2.01f, 1.29f, 0.0f, 0.08f, 1.6f);

    public StandRight(StateMachine<SpaceMan> fsm) {
        super(fsm);
    }


    @Override
    protected void update() {

    }

    @Override
    protected void setup() {
        System.out.println("stand right up");
        setStateLook(stateImage, stateShape);
    }

    @Override
    protected void teardown() {
        System.out.println("stand right down");

    }
}

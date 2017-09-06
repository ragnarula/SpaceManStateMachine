package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import statemachine.StateMachine;

public class CrouchRight extends SpaceManState{

    private static final BodyImage stateImage = new BodyImage("data/crouchright.gif", 4);
    private static final Shape stateShape = new PolygonShape(0.08f, 1.03f, 1.29f, -0.49f, 0.97f, -2.0f, -1.2f, -2.0f, -0.72f, 0.79f, -0.48f, 1.04f);

    public CrouchRight(StateMachine<SpaceMan> fsm) {
        super(fsm);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void setup() {
        System.out.println("Crouch Right Up");
        setStateLook(stateImage,stateShape);

    }

    @Override
    protected void teardown() {
        System.out.println("Crouch Right Down");
    }
}

package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import statemachine.StateMachine;

public class CrouchLeft extends SpaceManState{

    private final BodyImage stateImage = new BodyImage("data/crouchleft.gif", 4);
    private final Shape stateShape = new PolygonShape(0.48f, 1.04f, 0.72f, 0.79f, 0.88f, -0.08f, 1.2f, -2.0f, -0.96f, -2.0f, -1.28f, -0.48f, -0.08f, 1.04f);

    public CrouchLeft(StateMachine<SpaceMan> fsm) {
        super(fsm);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void setup() {
        System.out.println("Crouch Left Up");
        setStateLook(stateImage,stateShape);
    }

    @Override
    protected void teardown() {
        System.out.println("Crouch Left Down");
    }
}

package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import statemachine.StateMachine;

public class JumpRight extends SpaceManState {

    private static final BodyImage stateImage = new BodyImage("data/jumpRight.gif", 4);
    private static final Shape stateShape = new PolygonShape(0.06f, 1.58f, 1.28f, 0.07f, 0.33f, -1.85f, -0.39f, -1.85f, -0.71f, -1.53f, -0.88f, 0.48f, -0.72f, 1.36f);

    public JumpRight(StateMachine<SpaceMan> fsm) {
        super(fsm);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void setup() {
        setStateLook(stateImage,stateShape);
        System.out.println("Jump right up");
    }

    @Override
    protected void teardown() {
        System.out.println("Jump right down");
    }
}

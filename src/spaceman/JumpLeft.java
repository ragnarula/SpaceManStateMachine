package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import statemachine.StateMachine;

public class JumpLeft extends SpaceManState {

    private static final BodyImage stateImage = new BodyImage("data/jumpleft.gif", 4);
    private static final Shape stateShape = new PolygonShape(0.44f, 1.57f, 0.69f, 1.33f, 0.84f, 0.46f, 0.69f, -1.8f, -0.3f, -1.81f, -1.26f, 0.03f, -0.05f, 1.57f);

    public JumpLeft(StateMachine<SpaceMan> fsm) {
        super(fsm);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void setup() {
        setStateLook(stateImage, stateShape);
        System.out.println("Jump Left Up");
    }

    @Override
    protected void teardown() {
        System.out.println("Jump Left Down");
    }
}

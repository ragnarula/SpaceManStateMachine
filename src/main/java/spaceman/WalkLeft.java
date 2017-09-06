package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import statemachine.StateMachine;

public class WalkLeft extends SpaceManState{

    private static final BodyImage stateImage = new BodyImage("data/walkleft.gif", 4);
    private static final Shape stateShape = new PolygonShape(-0.08f, 1.61f, -1.28f, 0.09f, -0.95f, -2.0f, 0.88f, -1.99f, 1.36f, -0.88f, 0.72f, 1.37f, 0.46f, 1.59f);

    public WalkLeft(StateMachine<SpaceMan> fsm) {
        super(fsm);
    }

    @Override
    protected void update() {
        Vec2 v = player.getLinearVelocity();
        player.setLinearVelocity(new Vec2(-player.WALKSPEED, v.y));
    }

    @Override
    protected void setup() {
        System.out.println("Walk Left Up");
        setStateLook(stateImage, stateShape);
    }

    @Override
    protected void teardown() {
        System.out.println("Walk Left Down");
    }
}

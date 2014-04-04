package spaceman;

import org.jbox2d.common.Vec2;
import statemachine.StateMachine;

public class JumpTurn extends SpaceManState {

    public JumpTurn(StateMachine<SpaceMan> fsm) {
        super(fsm);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void setup() {
        player.setLinearVelocity(new Vec2(-player.getLinearVelocity().x, player.getLinearVelocity().y));
        System.out.println("JumpTurn up");
    }

    @Override
    protected void teardown() {
        System.out.println("JumpTurn town");
    }
}

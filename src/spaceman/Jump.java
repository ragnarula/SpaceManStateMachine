package spaceman;


import org.jbox2d.common.Vec2;
import statemachine.StateMachine;

public class Jump extends SpaceManState{

    public Jump(StateMachine<SpaceMan> fsm) {
        super(fsm);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void setup() {
        player.setLinearVelocity(new Vec2(player.getLinearVelocity().x, player.JUMPSPEED));
        player.getFootSensor().addSensorListener(new GroundContactListener(player));
        System.out.println("Jump Up");
    }

    @Override
    protected void teardown() {
        System.out.println("Jump Down");
    }
}

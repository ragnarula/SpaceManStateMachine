package spaceman;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import statemachine.StateMachine;

public final class SpaceMan extends DynamicBody implements StepListener {

    public enum actions {LEFT, RIGHT, CROUCH, STAND}

    protected static final float WALKSPEED = 3.75f;
    protected static final int JUMPSPEED = 6;

    protected SolidFixture fixture;

    private final StateMachine<SpaceMan> fsm = new StateMachine<>(this);


    private final BoxShape footSensorShape;
    protected final Sensor footSensor;

    public SpaceMan(World w) {
        super(w);
        this.setFixedRotation(true);

        this.footSensorShape = new BoxShape(0.125f, 0.125f, new Vec2(0, -1.9f));
        this.footSensor = new Sensor(this, footSensorShape);

        final SpaceManState standLeft = new StandLeft(fsm);
        final SpaceManState standRight = new StandRight(fsm);
        final SpaceManState crouchLeft = new CrouchLeft(fsm);
        final SpaceManState crouchRight = new CrouchRight(fsm);
        final SpaceManState walkLeft = new WalkLeft(fsm);
        final SpaceManState walkRight = new WalkRight(fsm);

        //setup state machine
        fsm.setInitialState(standRight);
        //add edges for state graph
        fsm.addEdge(standLeft, standRight, true);
        fsm.addEdge(standLeft, crouchLeft, true);
        fsm.addEdge(standRight, crouchRight, true);
        fsm.addEdge(walkLeft, standLeft, true);
        fsm.addEdge(walkRight, standRight, true);
        //add actions for stand left
        fsm.addAction(actions.LEFT, standLeft, walkLeft);
        fsm.addAction(actions.RIGHT, standLeft, walkRight);
        fsm.addAction(actions.CROUCH, standLeft, crouchLeft);
        //add actions for stand right
        fsm.addAction(actions.LEFT, standRight, walkLeft);
        fsm.addAction(actions.RIGHT, standRight, walkRight);
        fsm.addAction(actions.CROUCH, standRight, crouchRight);
        //add actions for crouch left
        fsm.addAction(actions.LEFT, crouchLeft, walkLeft);
        fsm.addAction(actions.RIGHT, crouchLeft, walkRight);
        fsm.addAction(actions.STAND, crouchLeft, standLeft);
        //add actions for crouch right
        fsm.addAction(actions.LEFT, crouchRight, walkLeft);
        fsm.addAction(actions.RIGHT, crouchRight, walkRight);
        fsm.addAction(actions.STAND, crouchRight, standRight);
        //add actions for walk left
        fsm.addAction(actions.RIGHT, walkLeft, walkRight);
        fsm.addAction(actions.CROUCH, walkLeft, crouchLeft);
        fsm.addAction(actions.STAND, walkLeft, standLeft);
        //add actions for walk right
        fsm.addAction(actions.LEFT, walkRight, walkLeft);
        fsm.addAction(actions.CROUCH, walkRight, crouchRight);
        fsm.addAction(actions.STAND, walkRight, standRight);
    }

    public void doAction(Enum e) {
        fsm.doAction(e);
    }


    @Override
    public void preStep(StepEvent e) {
        fsm.update();
    }

    @Override
    public void postStep(StepEvent e) {

    }
}

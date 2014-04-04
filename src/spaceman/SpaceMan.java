package spaceman;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import statemachine.StateMachine;

public final class SpaceMan extends DynamicBody implements StepListener {
    public enum actions {LEFT, RIGHT, CROUCH, STAND}

    ;

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

        //setup state machine
        fsm.setInitialState(standRight);
        //add edges
        fsm.addEdge(standLeft, standRight, true);
        fsm.addEdge(standLeft, crouchLeft, true);
        fsm.addEdge(standRight, crouchRight, true);
        //add actions for stand left
        fsm.addAction(actions.RIGHT, standLeft, standRight);
        fsm.addAction(actions.CROUCH, standLeft, crouchLeft);
        //add actions for stand right
        fsm.addAction(actions.LEFT, standRight, standLeft);
        fsm.addAction(actions.CROUCH, standRight, crouchRight);
        //add actions for crouch left
        fsm.addAction(actions.RIGHT, crouchLeft, standRight);
        //add actions for crouch right
        fsm.addAction(actions.LEFT, crouchRight, standLeft);

//        System.out.println(standLeft);
//        System.out.println(standRight);
    }

    public void doAction(Enum e) {
//        System.out.println(e);
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

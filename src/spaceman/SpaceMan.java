package spaceman;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import statemachine.StateMachine;

import java.awt.event.KeyListener;

public final class SpaceMan extends DynamicBody implements StepListener {

    public enum actions {
        LEFT, RIGHT, CROUCH, STAND, JUMP, LAND
    }

    protected static final float WALKSPEED = 3.75f;
    protected static final int JUMPSPEED = 6;

    protected SolidFixture fixture;
    protected SpaceManController controller;

    private final StateMachine<SpaceMan> fsm = new StateMachine<>(this);

    protected final Sensor footSensor;
    protected final SensorListener sensorListener;

    public SpaceMan(World w) {
        super(w);
        setFixedRotation(true);

        BoxShape footSensorShape = new BoxShape(0.125f, 0.125f, new Vec2(0, -2));
        footSensor = new Sensor(this, footSensorShape);
        sensorListener = new GroundContactListener(this);

        final SpaceManState standLeft = new StandLeft(fsm);
        final SpaceManState standRight = new StandRight(fsm);
        final SpaceManState crouchLeft = new CrouchLeft(fsm);
        final SpaceManState crouchRight = new CrouchRight(fsm);
        final SpaceManState walkLeft = new WalkLeft(fsm);
        final SpaceManState walkRight = new WalkRight(fsm);
        final SpaceManState jump = new Jump(fsm);
        final SpaceManState jumpLeft = new JumpLeft(fsm);
        final SpaceManState jumpRight = new JumpRight(fsm);
        final SpaceManState jumpTurn = new JumpTurn(fsm);
        final SpaceManState land = new Land(fsm);

        //setup state machine
        fsm.setInitialState(standRight);
        //add edges for state graph
        fsm.addEdge(standLeft, standRight, true);
        fsm.addEdge(standLeft, crouchLeft, true);
        fsm.addEdge(standLeft, jump, false);
        fsm.addEdge(standRight, crouchRight, true);
        fsm.addEdge(standRight, jump, false);
        fsm.addEdge(walkLeft, standLeft, true);
        fsm.addEdge(walkLeft,jump,false);
        fsm.addEdge(walkRight, standRight, true);
        fsm.addEdge(walkRight,jump,false);
        fsm.addEdge(jump,jumpLeft,false);
        fsm.addEdge(jump,jumpRight,false);
        fsm.addEdge(jumpLeft, jumpTurn, true);
        fsm.addEdge(jumpLeft, land, false);
        fsm.addEdge(jumpRight, jumpTurn, true);
        fsm.addEdge(jumpRight, land, false);
        fsm.addEdge(land, standLeft, false);
        fsm.addEdge(land, standRight, false);

        //add actions for stand left
        fsm.addAction(actions.LEFT, standLeft, walkLeft);
        fsm.addAction(actions.RIGHT, standLeft, walkRight);
        fsm.addAction(actions.CROUCH, standLeft, crouchLeft);
        fsm.addAction(actions.JUMP, standLeft, jumpLeft);
        //add actions for stand right
        fsm.addAction(actions.LEFT, standRight, walkLeft);
        fsm.addAction(actions.RIGHT, standRight, walkRight);
        fsm.addAction(actions.CROUCH, standRight, crouchRight);
        fsm.addAction(actions.JUMP, standRight, jumpRight);
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
        fsm.addAction(actions.JUMP, walkLeft, jumpLeft);
        //add actions for walk right
        fsm.addAction(actions.LEFT, walkRight, walkLeft);
        fsm.addAction(actions.CROUCH, walkRight, crouchRight);
        fsm.addAction(actions.STAND, walkRight, standRight);
        fsm.addAction(actions.JUMP, walkRight, jumpRight);
        //add actions for jump left
        fsm.addAction(actions.RIGHT, jumpLeft, jumpRight);
        fsm.addAction(actions.LAND, jumpLeft, standLeft);
        //add actions for jump right
        fsm.addAction(actions.LEFT, jumpRight, jumpLeft);
        fsm.addAction(actions.LAND, jumpRight, standRight);

    }
    public Sensor getFootSensor() {
        return footSensor;
    }

    public SensorListener getSensorListener() {
        return sensorListener;
    }

    public SpaceManController getController() {
        return controller;
    }

    public void setController(SpaceManController controller) {
        this.controller = controller;
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

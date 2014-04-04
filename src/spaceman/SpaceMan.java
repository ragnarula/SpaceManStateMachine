package spaceman;

import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Sensor;
import city.cs.engine.SolidFixture;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public final class SpaceMan extends DynamicBody implements StepListener {
    
    protected static final float WALKSPEED = 3.75f;
    protected static final int JUMPSPEED = 6;
    
    protected SolidFixture fixture;

    protected final SpaceManState faceLeftState = new FaceLeftState(this);
    protected final SpaceManState faceRightState = new FaceRightState(this);
    protected final SpaceManState walkLeftState = new WalkLeftState(this);
    protected final SpaceManState walkRightState = new WalkRightState(this);
    protected final SpaceManState crouchLeftState = new CrouchLeftState(this);
    protected final SpaceManState crouchRightState = new CrouchRightState(this);
    protected final SpaceManState jumpLeftState = new JumpLeftState(this);
    protected final SpaceManState jumpRightState = new JumpRightState(this);

    private SpaceManState currentState;
    protected SpaceManState nextState;
    protected SpaceManState previousState;
    protected boolean stateLocked = false;

    private final BoxShape footSensorShape;
    protected final Sensor footSensor;

    public SpaceMan(World w) {
        super(w);
        this.setFixedRotation(true);

        this.footSensorShape = new BoxShape(0.125f, 0.125f, new Vec2(0, -1.9f));
        this.footSensor = new Sensor(this, footSensorShape);

        currentState = faceLeftState;
        currentState.setupState();
        nextState = faceLeftState;
    }
    
    public void goToNextState() {
        if (currentState != nextState) {
            currentState.teardownState();
            previousState = currentState;
            currentState = nextState;
            currentState.setupState();
        }
    }
    
    public void skipToNextState(SpaceManState state){
        currentState.teardownState();
        currentState = state;
        currentState.setupState();
    }

    public void setNextState(SpaceManState state) {
        if (nextState != state) {
//            System.out.println(state.toString());
            nextState = state;    
        }
    }

    public void walkLeft() {
        currentState.walkLeft();
    }

    public void walkRight() {
        currentState.walkRight();
    }

    public void walk() {
        currentState.walk();
    }

    public void jump() {
        currentState.jump();
    }

    public void crouch() {
        currentState.crouch();
    }

    public void stand() {
        currentState.stand();
    }

    public void shoot() {
        currentState.shoot();
    }

    @Override
    public void preStep(StepEvent e) {
    }

    @Override
    public void postStep(StepEvent e) {
        if (!stateLocked) {
            goToNextState();
        }
    }
}

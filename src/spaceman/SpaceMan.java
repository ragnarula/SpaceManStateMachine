package spaceman;

import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Sensor;
import city.cs.engine.SolidFixture;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import java.awt.event.KeyListener;
import org.jbox2d.common.Vec2;
import statemachine.StateMachine;

public final class SpaceMan extends DynamicBody implements StepListener {
    public enum actions { LEFT, RIGHT};
    
    protected static final float WALKSPEED = 3.75f;
    protected static final int JUMPSPEED = 6;
    
    protected SolidFixture fixture;

    private final StateMachine<SpaceMan> fsm = new StateMachine<>(this);
    private final SpaceManState faceLeftState = new FaceLeftState(fsm);
    private final SpaceManState faceRightState = new FaceRightState(fsm);


    private final BoxShape footSensorShape;
    protected final Sensor footSensor;

    public SpaceMan(World w) {
        super(w);
        this.setFixedRotation(true);

        this.footSensorShape = new BoxShape(0.125f, 0.125f, new Vec2(0, -1.9f));
        this.footSensor = new Sensor(this, footSensorShape);
        fsm.setInitialState(faceLeftState);
        fsm.addEdge(faceLeftState, faceRightState, true);

        fsm.addAction(actions.LEFT, faceRightState, faceLeftState);

        fsm.addAction(SpaceMan.actions.RIGHT, faceLeftState,faceRightState);
        System.out.println(faceLeftState);
        System.out.println(faceRightState);
    }

    public void doAction(Enum e){
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

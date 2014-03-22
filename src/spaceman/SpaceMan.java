package spaceman;

import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public final class SpaceMan extends DynamicBody {
    
    private enum MoveState {

        WALKING, STANDING, CROUCHING, JUMPING
    }
    
    private MoveState moveState;

    private final SpaceManState faceLeftState = new FaceLeft(this);
    private final SpaceManState faceRightState = new FaceRight(this);
    
    private SpaceManState currentState;

    public SpaceMan(World w) {
        super(w);
        this.setFixedRotation(true);
        currentState = faceLeftState;
    }
    
    public SpaceManState getFaceLeftState() {
        return faceLeftState;
    }

    public SpaceManState getFaceRightState() {
        return faceRightState;
    }

    public void setState(SpaceManState state) {
        if (currentState != state) {
            moveState = null;
            this.currentState = state;
        }
    }

    public void walk() {
        if (moveState != MoveState.WALKING) {
            currentState.walk();
            moveState = MoveState.WALKING;
        }
    }

    public void jump() {
        if (moveState != MoveState.JUMPING) {
            currentState.jump();
            this.setLinearVelocity(new Vec2(this.getLinearVelocity().x,8));
            moveState = MoveState.JUMPING;
        }

    }

    public void crouch() {
        if (moveState != MoveState.CROUCHING) {
            currentState.crouch();
            moveState = MoveState.CROUCHING;
        }
    }

    public void stand() {
        if (moveState != MoveState.STANDING) {
            currentState.stand();
            moveState = MoveState.STANDING;
        }
    }

    public void shoot() {
        currentState.shoot();
    }

}

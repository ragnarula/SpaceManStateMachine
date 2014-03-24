package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

public class JumpLeftState extends SpaceManState {

    private static final BodyImage stateImage = new BodyImage("data/jumpleft.gif", 4);
    private static final Shape stateShape = new PolygonShape(0.44f, 1.57f, 0.69f, 1.33f, 0.84f, 0.46f, 0.69f, -1.8f, -0.3f, -1.81f, -1.26f, 0.03f, -0.05f, 1.57f);
    
    public JumpLeftState(SpaceMan player) {
        JumpLeftState.player = player;
    }

    @Override
    public void setupState() {
        setStateLook(stateImage, stateShape);
        // if state is not locked
        if (!player.stateLocked) {
            //lock the state, make him jump, add senson for detect landing
            player.stateLocked = true;
            player.setLinearVelocity(new Vec2(player.getLinearVelocity().x, SpaceMan.JUMPSPEED));
            player.footSensor.addSensorListener(new GroundContactListener(player));
            
        }
        else if (player.stateLocked) {
            //if the state is locked, just reverse the x velocity
            player.setLinearVelocity(new Vec2(-player.getLinearVelocity().x, player.getLinearVelocity().y));
        }
    }

    @Override
    public void teardownState() {

    }

    @Override
    public void walkLeft() {
        player.setNextState(player.walkLeftState);
    }

    @Override
    public void walkRight() {
        //if state is locked, it means he is in the air, so skip to jump right rather than walk right
        if (player.stateLocked) {
            player.skipToNextState(player.jumpRightState);
        }
        player.setNextState(player.walkRightState);
    }

    @Override
    public void walk() {
        walkLeft();
    }

    @Override
    public void shoot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void jump() {
       
    }

    @Override
    public void crouch() {
        player.setNextState(player.crouchLeftState);
    }

    @Override
    public void stand() {
       player.setNextState(player.faceLeftState);
    }
    
    @Override
    public String toString(){
        return "Jump Left";
    }
}
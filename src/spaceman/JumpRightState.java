package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import static spaceman.SpaceManState.player;

public class JumpRightState extends SpaceManState {

    private static final BodyImage stateImage = new BodyImage("data/jumpRight.gif", 4);
    private static final Shape stateShape = new PolygonShape(0.06f, 1.58f, 1.28f, 0.07f, 0.33f, -1.85f, -0.39f, -1.85f, -0.71f, -1.53f, -0.88f, 0.48f, -0.72f, 1.36f);

    public JumpRightState(SpaceMan player) {
        JumpRightState.player = player;
    }

    @Override
    public void setupState() {
        setStateLook(stateImage, stateShape);

        if (!player.stateLocked) {

            player.stateLocked = true;
            player.setLinearVelocity(new Vec2(player.getLinearVelocity().x, SpaceMan.JUMPSPEED));
            player.footSensor.addSensorListener(new GroundContactListener(player));

        } else if (player.stateLocked) {

            player.setLinearVelocity(new Vec2(-player.getLinearVelocity().x, player.getLinearVelocity().y));
        }
    }

    @Override
    public void teardownState() {

    }

    @Override
    public void walkLeft() {
        if (player.stateLocked) {
            player.skipToNextState(player.jumpLeftState);
        }
        player.setNextState(player.walkLeftState);

    }

    @Override
    public void walkRight() {

        player.setNextState(player.walkRightState);

    }

    @Override
    public void walk() {
        walkRight();
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
        player.setNextState(player.crouchRightState);
    }

    @Override
    public void stand() {
        player.setNextState(player.faceRightState);
    }

}

package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import org.jbox2d.common.Vec2;
import static spaceman.SpaceManState.player;

public class JumpLeftState extends SpaceManState {

    private final BodyImage stateImage;
    private final Shape stateShape;

    public JumpLeftState(SpaceMan player) {
        JumpLeftState.player = player;
        this.stateImage = new BodyImage("data/jumpleft.gif", 4);
        this.stateShape = new PolygonShape(0.44f, 1.57f, 0.69f, 1.33f, 0.84f, 0.46f, 0.69f, -1.8f, -0.3f, -1.81f, -1.26f, 0.03f, -0.05f, 1.57f);
    }

    @Override
    public void setupState() {

        player.setImage(stateImage);

        if (JumpLeftState.fixture != null) {
            JumpLeftState.fixture.destroy();
        }
        JumpLeftState.fixture = new SolidFixture(player, stateShape, 100);
        JumpLeftState.fixture.setFriction(10);

        if (!player.stateLocked) {
            
            player.stateLocked = true;
            player.setLinearVelocity(new Vec2(player.getLinearVelocity().x, 6));
            player.footSensor.addSensorListener(new GroundContactListener(player));
            
        }
        else if (player.stateLocked) {
            
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
        if (player.stateLocked) {
            player.skipToNextState(player.jumpRightState);
        }
        player.setNextState(player.walkRightState);
    }

    @Override
    public void walk() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}

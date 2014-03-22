package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import static spaceman.SpaceManState.player;

public class CrouchLeftState extends SpaceManState{
    
    private final BodyImage stateImage;
    private final Shape stateShape;
    
    public CrouchLeftState(SpaceMan player){
        CrouchLeftState.player = player;
        this.stateImage = new BodyImage("data/crouchleft.gif", 4);
        this.stateShape = new PolygonShape(0.48f, 1.04f, 0.72f, 0.79f, 0.88f, -0.08f, 1.2f, -2.0f, -0.96f, -2.0f, -1.28f, -0.48f, -0.08f, 1.04f);
    }

    @Override
    public void setupState() {
         player.setImage(stateImage);
         if (CrouchLeftState.fixture != null) {
            CrouchLeftState.fixture.destroy();
        }
        CrouchLeftState.fixture = new SolidFixture(player,stateShape);
        CrouchLeftState.fixture.setFriction(10);
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
    }

    @Override
    public void stand() {
        player.setNextState(player.faceLeftState);
    }

}

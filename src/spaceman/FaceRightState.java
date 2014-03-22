package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;


final class FaceRightState extends SpaceManState {
    
    private final BodyImage stateImage;
    private final Shape stateShape;
    
    public FaceRightState(SpaceMan player){
        FaceRightState.player = player;
        this.stateImage = new BodyImage("data/standRight.gif", 4);
        this.stateShape = new PolygonShape(-0.48f, 1.59f, -0.71f, 1.36f, -0.87f, 0.48f, -0.96f, -2.02f, 0.79f, -2.01f, 1.29f, 0.0f, 0.08f, 1.6f);
    }

    @Override
    public void setupState() {
        player.setImage(stateImage);
        if (FaceRightState.fixture != null) {
            FaceRightState.fixture.destroy();
        }
        FaceRightState.fixture = new SolidFixture(player, stateShape);
        FaceRightState.fixture.setFriction(10);
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
        player.setNextState(player.jumpRightState);
    }

    @Override
    public void crouch() {
        player.setNextState(player.crouchRightState);
    }

    @Override
    public void stand() {
    }





}
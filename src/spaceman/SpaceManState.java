package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import statemachine.State;
import statemachine.StateMachine;

public abstract class SpaceManState extends State<SpaceMan> {

    protected SpaceMan player;

    public SpaceManState(StateMachine<SpaceMan> fsm) {
        super(fsm);
        player = getContext();
    }

    protected void setStateLook(BodyImage stateImage, Shape stateShape) {
        //set image for this state
        player.setImage(stateImage);
        //destroy the old fixture, if there was one
        if (player.fixture != null) {
            player.fixture.destroy();
        }
        player.fixture = new SolidFixture(player, stateShape, 100);
        player.fixture.setFriction(10);
    }

}

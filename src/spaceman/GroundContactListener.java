package spaceman;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;

import java.awt.event.KeyListener;

public class GroundContactListener implements SensorListener {

    private final SpaceMan player;
    private final SpaceManController controller;

    public GroundContactListener(SpaceMan _player) {
        player = _player;
        controller = player.getController();
    }

    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() != null) {
            player.doAction(SpaceMan.actions.LAND);
//            controller.keyPressed(controller.keyStack.peek());
//            player.getFootSensor().removeSensorListener(this);
        }
    }

    @Override
    public void endContact(SensorEvent e) {

    }

}

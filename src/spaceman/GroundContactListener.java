package spaceman;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;

public class GroundContactListener implements SensorListener {

    private final SpaceMan player;

    public GroundContactListener(SpaceMan player) {
        this.player = player;
    }

    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() != null) {
            player.stateLocked = false;
            player.footSensor.removeSensorListener(this);
        }

    }

    @Override
    public void endContact(SensorEvent e) {

    }

}

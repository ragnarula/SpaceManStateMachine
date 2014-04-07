package spaceman;

import statemachine.StateMachine;

public class Land extends SpaceManState {
    public Land(StateMachine<SpaceMan> fsm) {
        super(fsm);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void setup() {
        player.getFootSensor().removeSensorListener(player.getSensorListener());
        System.out.println("land up");
    }

    @Override
    protected void teardown() {
        System.out.println("land down");
    }
}

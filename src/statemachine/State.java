package statemachine;

public abstract class State<T> {

    private StateMachine<T> fsm;

    public State(StateMachine<T> fsm) {
        this.fsm = fsm;
    }

    protected final T getContext() {
        return fsm.getContext();
    }
    
    protected abstract void update();
    protected abstract void setup();
    protected abstract void teardown();

}

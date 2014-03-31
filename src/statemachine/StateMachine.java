package statemachine;

import java.util.ArrayList;

public class StateMachine<T> {

    private final StateMap<T> map;
    private final ActionList<T> actions;

    private T context;
    private State<T> currentState;
    private State<T> goalState;

    public StateMachine(State<T> initialState) {
        currentState = initialState;
        map = new StateMap<>(this);
        actions = new ActionList<>();
    }

    public void update() {
        if (goalState == currentState) {
            currentState.update();
        } else {
            followPath(map.getPath(goalState));
        }
    }

    private void followPath(ArrayList<State<T>> states) {
        currentState.teardown();
        for (int i = 0; i < states.size() - 1; i++) {
            states.get(i).setup();
            states.get(i).teardown();
        }
        currentState = states.get(states.size());
        currentState.setup();
    }
    
    protected State<T> getCurrentState(){
        return currentState;
    }

    public void addEdge(State<T> parent, State<T> child, boolean bidirectional) {
        map.addPath(parent, child, bidirectional);
    }

    public void addAction(Enum e, State<T> _source, State<T> _goal) {
        actions.addAction(e, _source, _goal);
    }

    public void doAction(Enum e) {

        State<T> state = actions.getGoal(e, currentState);
        if (state != null && state != goalState) {
            goalState = state;
        }

    }

}

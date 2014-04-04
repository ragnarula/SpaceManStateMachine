package statemachine;

import java.util.Stack;

public class StateMachine<T> {

    private final StateMap<T> map;
    private final ActionList<T> actions;

    private T context;
    private State<T> currentState;
    private State<T> goalState;

    public StateMachine(T _context) {
        context = _context;
        map = new StateMap<>(this);
        actions = new ActionList<>();
    }

    public void setInitialState(State<T> initialState) {
        currentState = initialState;
        goalState = currentState;
        currentState.setup();
    }

    public void update() {

        if (goalState == currentState) {
            currentState.update();
        } else if(goalState != null) {
            System.out.println(goalState + " " + currentState);
            followPath(map.getPath(goalState));
        }
    }

    private void followPath(Stack<State<T>> states) {
        currentState.teardown();
        while (!states.empty()) {
            currentState = states.pop();
            currentState.setup();
            if (!states.empty()) {
                currentState.teardown();
            }
        }
    }

    protected State<T> getCurrentState() {
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

    protected final T getContext() {
        return context;
    }

}

package statemachine;

public class StateMachine<T> {

    private T context;
    private State<T> currentState;
    private State<T> goalState;

    public StateMachine(State<T> initialState) {
        currentState = initialState;
    }

    public void update() {
        if (goalState == currentState) {
            currentState.update();
        } else {
            
            
            findPath(goalState);
        }
        
    }
    
    private void findPath(State<T> ){
        
    }

}

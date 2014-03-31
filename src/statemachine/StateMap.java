package statemachine;

import java.util.ArrayList;

public class StateMap<T> {

    private final ArrayList<Edge<T>> pathList;
    private final StateMachine<T> fsm;

    protected StateMap(StateMachine<T> _fsm) {
        pathList = new ArrayList<>();
        fsm = _fsm;
    }

    protected void addPath(State<T> parent, State<T> child, boolean bidirectional) {
        pathList.add(new Edge(parent, child));
        
        if (bidirectional) {
            pathList.add(new Edge(child, parent));
        }
    }
    
    protected ArrayList<State<T>> getPath(State<T> goalState){
        return iddfsSearch(goalState);
    }
    
    private ArrayList<State<T>> iddfsSearch(State<T> goalState){
        int depthLimit = 0;
        ArrayList<State<T>> path = new ArrayList<>();
        ArrayList<State<T>> children = new ArrayList<>();
        
        if(goalState == fsm.getCurrentState()){
            path.add(goalState);
            return path;
        } else {
            for(Edge e : pathList){
                if(e.getParent() == fsm.getCurrentState()){
                    
                }
                    
            }
        }
        
        return null;
    }
    
    private ArrayList<State<T>> depthLimitSearch(State<T> goalState, int limit){
        
        
        return null;
        
    }
}

package statemachine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class StateMap<T> {

    private final ArrayList<Edge<T>> pathList;
    private final StateMachine<T> fsm;

    private Stack<State<T>> solution;
    private Stack<State<T>> frontier;

    protected StateMap(StateMachine<T> _fsm) {
        pathList = new ArrayList<>();
        fsm = _fsm;
    }

    protected void addPath(State<T> parent, State<T> child, boolean bidirectional) {
        pathList.add(new Edge<>(parent, child));

        if (bidirectional) {
            pathList.add(new Edge<>(child, parent));
        }
    }

    protected Stack<State<T>> getPath(State<T> goalState) {
        depthLimitSearch(fsm.getCurrentState(),goalState,3);
        return solution;
    }


    private boolean depthLimitSearch(State<T> current, State<T> goal, int limit) {
        return depthLimitSearch(current, goal, 0, limit);
    }

    private boolean depthLimitSearch(State<T> current, State<T> goal, int depth, int limit) {
        if (depth == 0) {
            solution = new Stack<>();
            frontier = new Stack<>();
        }
        if (current == goal) {
            solution.push(goal);
            return true;
        }
        if (depth == limit) {
            return false;
        }

        if(!getChildren(current)){
            return false;
        }

        boolean found = false;
        while(!frontier.empty()){
            found = depthLimitSearch(frontier.pop(), goal, depth + 1, limit);
            if(found){
                solution.push(current);
                break;
            }
        }
        return found;

    }

    private boolean getChildren(State<T> parent) {
        boolean hasChildren;

        Iterator it = pathList.iterator();

        while (hasChildren = it.hasNext()) {
            Edge edge = (Edge) it.next();
            if (edge.getParent() == parent) {
                frontier.push(edge.getChild());
            }
        }
        return hasChildren;
    }
}

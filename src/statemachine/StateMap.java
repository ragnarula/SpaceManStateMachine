package statemachine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class StateMap<T> {

    private final ArrayList<Edge<T>> pathList;
    private final StateMachine<T> fsm;

    private Stack<State<T>> solution;
    private Stack<State<T>> frontier;
    private ArrayList<State<T>> explored;

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
        System.out.println("Solution :" + solution);
        return solution;
    }

    private boolean depthLimitSearch(State<T> current, State<T> goal, int limit) {
        return depthLimitSearch(current, goal, 0, limit);
    }

    private boolean depthLimitSearch(State<T> current, State<T> goal, int depth, int limit) {
        if (depth == 0) {
            solution = new Stack<>();
            frontier = new Stack<>();
            explored = new ArrayList<>();
            System.out.println("init : " + depth);
        }
        if (current == goal) {
            solution.push(goal);
            System.out.println("goal : " + depth + " solution: " + solution);
            return true;
        }
        if (depth == limit) {
            System.out.println("limit : " + depth);
            return false;
        }

        if(!getChildren(current)){
            System.out.println("get children : " + depth + " " + frontier);
            return false;
        }
        System.out.println("got children : " + depth + " " + frontier);
        boolean found = false;
        while(!frontier.empty() && !found){
            System.out.println("while : " + depth);
            found = depthLimitSearch(frontier.pop(), goal, depth + 1, limit);
            if(found && depth > 0){
                solution.push(current);
                System.out.println("backtrack : " + depth + " solution: " + solution);
                break;
            }
        }
        System.out.println("end : " + depth);
        return found;

    }

    private boolean getChildren(State<T> parent) {

        boolean hasChildren = false;

        explored.add(parent);
        Iterator it = pathList.iterator();

        while (it.hasNext()) {

            Edge edge = (Edge) it.next();
            if (edge.getParent() == parent && !explored.contains(edge.getChild())) {
                frontier.push(edge.getChild());
                hasChildren = true;
            }
        }
        return hasChildren;
    }
}

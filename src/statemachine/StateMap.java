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
        for(int i = 1; i < 5; i++){
            boolean found = depthLimitSearch(fsm.getCurrentState(),goalState,i);
            if(found) break;
        }
        System.out.println("Returning solution: " + solution);
        return solution;
    }

    private boolean depthLimitSearch(State<T> current, State<T> goal, int limit) {
        return depthLimitSearch(current, goal, 0, limit);
    }

    private boolean depthLimitSearch(State<T> current, State<T> goal, int depth, int limit) {
        System.out.println("entering search with current state " + current + ", goal state " + goal);
        if (depth == 0) {
            solution = new Stack<>();
            frontier = new Stack<>();
            explored = new ArrayList<>();
            System.out.println("beginning iterative depth first search at depth " + depth + ", limit " + limit);
        }
        if (current == goal) {
            solution.push(goal);
            System.out.println("goal found at depth " + depth + ", solution: " + solution);
            return true;
        }
        if (depth == limit) {
            System.out.println("limit reached at depth " + depth);
            return false;
        }

        if(!getChildren(current)){
            System.out.println("no new children found at depth " + depth + ", frontier: " + frontier);
            return false;
        }
        System.out.println("found new children at depth " + depth + ", frontier: " + frontier);
        boolean found = false;
        while(!frontier.empty() && !found){
            System.out.println("in while loop at depth " + depth + ", frontier: " + frontier);
            found = depthLimitSearch(frontier.pop(), goal, depth + 1, limit);
            if(found && depth > 0){
                solution.push(current);
                System.out.println("backtracking at depth " + depth + ", solution: " + solution);
                break;
            }
        }
        System.out.println("ending at depth " + depth + ", success: " + found);
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

package statemachine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class StateMap<T> {

    private final ArrayList<Edge<T>> pathList;
    private final StateMachine<T> fsm;

    private Stack<State<T>> solution;
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
        for(int i = 0; i < 5; i++){
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

        System.out.println("NEW SEARCH current state " + current + ", goal state " + goal);

        Stack<State<T>> frontier = new Stack<>();
        boolean found = false;

        if (depth == 0) {
            solution = new Stack<>();
            explored = new ArrayList<>();
//            System.out.println("INIT, " + depth + ", limit " + limit);
        }
        if (current == goal) {
            solution.push(goal);
            System.out.println("GOAL " + depth + ", solution: " + solution);
            return true;
        }
        if (depth == limit) {
            System.out.println("LIMIT HIT " + depth);
            return false;
        }
        if(!getChildren(current, frontier)){
            System.out.println("NO KIDS " + depth);
            return false;
        } else {
            System.out.println("NEW KIDS " + depth + ", frontier: " + frontier);

            while (!frontier.empty() && !found) {
            System.out.println("while depth " + depth + ", frontier: " + frontier);
                found = depthLimitSearch(frontier.pop(), goal, depth + 1, limit);
                if (found && depth > 0) {
                    solution.push(current);
                    System.out.println("BACKTRACK " + depth + ", solution: " + solution);
                    break;
                }
            }
        }
        System.out.println("END " + depth + ", success: " + found);
        return found;
    }

    private boolean getChildren(State<T> parent, Stack<State<T>> frontier) {

        boolean hasChildren = false;

        explored.add(parent);
        Iterator it = pathList.iterator();

        while (it.hasNext()) {

            Edge edge = (Edge) it.next();
            if (edge.getParent() == parent && !explored.contains(edge.getChild()) && !frontier.contains(edge.getChild())) {
                frontier.push(edge.getChild());
                hasChildren = true;
            }
        }
        return hasChildren;
    }
}

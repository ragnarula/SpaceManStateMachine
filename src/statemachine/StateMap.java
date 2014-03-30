package statemachine;

import java.util.ArrayList;

public class StateMap<T> {

    private ArrayList<Edge<T>> pathList;

    public StateMap() {
        pathList = new ArrayList<>();
    }

    public void addPath(State<T> parent, State<T> child, boolean bidirectional) {
        pathList.add(new Edge(parent, child));
        
        if (bidirectional) {
            pathList.add(new Edge(child, parent));

        }
    }

}

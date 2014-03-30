package statemachine;

public class Edge<T> {
    
    private final State<T> parent;
    private final State<T> child;
    
    public Edge(State<T> _parent, State<T> _child){
        parent = _parent;
        child = _child;
    }

}

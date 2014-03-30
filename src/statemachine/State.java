package statemachine;

public abstract class State<T> {
    
    public abstract void update();
    public abstract void setup();
    public abstract void teardown();

}

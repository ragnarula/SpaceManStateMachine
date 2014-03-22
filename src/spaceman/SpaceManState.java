package spaceman;

public abstract class SpaceManState {
    
    protected static SpaceMan player;

    public abstract void setupState();
    
    public abstract void teardownState();
    
    public abstract void walkLeft();
    
    public abstract void walkRight();
    
    public abstract void walk();

    public abstract void shoot();

    public abstract void jump();

    public abstract void crouch();

    public abstract void stand();
}
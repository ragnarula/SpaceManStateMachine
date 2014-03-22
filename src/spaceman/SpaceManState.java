package spaceman;

import city.cs.engine.SolidFixture;

public abstract class SpaceManState {

    protected static final int WALKSPEED = 5;
    protected static SolidFixture fixture;
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
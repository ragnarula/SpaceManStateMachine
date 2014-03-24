package spaceman;

public abstract class WeaponState {
    
    private static SpaceMan player;
    
    public abstract void setupState();
    public abstract void teardownState();
    public abstract void runState();
    public abstract void setShooting();
    public abstract void stopShooting();
    
}

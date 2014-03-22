package spaceman;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

public class SpaceManWalker implements StepListener{

    private final SpaceMan player;
    private final int WALKSPEED;
    
    public SpaceManWalker(SpaceMan player, int WALKSPEED) {
        this.player = player;
        this.WALKSPEED = WALKSPEED;
                
    }

    @Override
    public void preStep(StepEvent e) {
        Vec2 v = player.getLinearVelocity();
        player.setLinearVelocity(new Vec2(WALKSPEED, v.y));
    }

    @Override
    public void postStep(StepEvent e) {
        
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package playerstatetest;

import spaceman.SpaceMan;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class TestWorld extends World{
    private SpaceMan p;
    public TestWorld(){
        {
            Shape s = new BoxShape(12.5f, 0.5f);
            StaticBody platform = new StaticBody(this, s);
            platform.setPosition(new Vec2(0,-12));
        }
        {
            Shape s = new BoxShape(0.5f,11.5f);
            StaticBody platform = new StaticBody(this, s);
            platform.setPosition(new Vec2(-12.5f,0));
        }
        {
            Shape s = new BoxShape(12.5f, 0.5f);
            StaticBody platform = new StaticBody(this, s);
            platform.setPosition(new Vec2(0,12));
        }
        {
            Shape s = new BoxShape(0.5f, 11.5f);
            StaticBody platform = new StaticBody(this, s);
            platform.setPosition(new Vec2(12.5f,0));
        }
        
        p = new SpaceMan(this);
        p.setPosition(new Vec2(0,-11));
//        p.stand();
    }
    
    public SpaceMan getPlayer(){
        return p;
    }

}

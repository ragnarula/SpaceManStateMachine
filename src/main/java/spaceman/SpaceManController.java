package spaceman;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Stack;

public class SpaceManController implements KeyListener, StepListener {

    private final SpaceMan player;
    protected final Stack<KeyEvent> keyStack;
    private boolean repeatKeys = false;

    public SpaceManController(SpaceMan p) {
        //stack to keep a history of keys pressed
        keyStack = new Stack();
        player = p;
        player.setController(this);
    }

    private void keyStackAdd(KeyEvent e) {
        if (keyStack.isEmpty()) {
            keyStack.push(e);

        } else if (keyStack.peek().getKeyCode() != e.getKeyCode()) {
            keyStack.push(e);
        }
    }

    private void keyStackRemove(KeyEvent e) {
        //Iterate over stack and remove the key that was released
        Iterator it = keyStack.iterator();
        while (it.hasNext()) {
            KeyEvent eStack = (KeyEvent) it.next();
            if (eStack.getKeyCode() == e.getKeyCode()) {
                it.remove();
            }
        }
        //if stack is not now empty, go to key pressed at top of stack
        if (!keyStack.isEmpty()) {
//            keyPressed(keyStack.pop());
            repeatKeys = true;
        }
        //if key stack is empty, stand still
        if (keyStack.isEmpty()) {
            repeatKeys = false;
            player.doAction(SpaceMan.actions.STAND);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
//        System.out.println(key);
        if (key == KeyEvent.VK_A) {
            keyStackAdd(e);
            player.doAction(SpaceMan.actions.LEFT);
        }
        if (key == KeyEvent.VK_D) {
            keyStackAdd(e);
            player.doAction(SpaceMan.actions.RIGHT);
        }
        if (key == KeyEvent.VK_S) {
            keyStackAdd(e);
            player.doAction(SpaceMan.actions.CROUCH);
        }
        if (key == KeyEvent.VK_W) {
            keyStackAdd(e);
            player.doAction(SpaceMan.actions.JUMP);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            keyStackRemove(e);
        }
        if (key == KeyEvent.VK_D) {
            keyStackRemove(e);
        }
        if (key == KeyEvent.VK_S) {
            keyStackRemove(e);
        }
        if (key == KeyEvent.VK_W) {
            keyStackRemove(e);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if(repeatKeys){
            keyPressed(keyStack.peek());
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}

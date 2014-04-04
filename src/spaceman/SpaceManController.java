package spaceman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Stack;

public class SpaceManController implements KeyListener {

    private final SpaceMan player;
    private final Stack<KeyEvent> keyStack;

    public SpaceManController(SpaceMan p) {
        //stack to keep a history of keys pressed
        this.keyStack = new Stack();
        this.player = p;
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
            keyPressed(keyStack.pop());
        }
        //if key stack is empty, stand still
        if (keyStack.isEmpty()) {
            player.stand();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
//        System.out.println(key);
        if (key == KeyEvent.VK_A) {
            keyStackAdd(e);
            player.walkLeft();
        }
        if (key == KeyEvent.VK_D) {
            keyStackAdd(e);
            player.walkRight();
        }
        if (key == KeyEvent.VK_S) {

            keyStackAdd(e);
            player.crouch();

        }
        if (key == KeyEvent.VK_W) {
            keyStackAdd(e);
            player.jump();
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
}

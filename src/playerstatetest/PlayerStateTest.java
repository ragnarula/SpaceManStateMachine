package playerstatetest;


import city.cs.engine.DebugViewer;
import spaceman.SpaceManController;
import city.cs.engine.UserView;
import javax.swing.JFrame;

/**
 *
 * @author Rag
 */
public class PlayerStateTest {
    
    private final TestWorld world;
    private final UserView view;
    
    public PlayerStateTest(){
        world = new TestWorld();
        view = new UserView(world, 500, 500);
//        view.setGridResolution(1);
        JFrame jframe = new JFrame();
        jframe.add(view);
        jframe.pack();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationByPlatform(true); 
        jframe.setResizable(false);
        jframe.setVisible(true);
        jframe.setFocusable(true);
        jframe.requestFocusInWindow();
        world.start();
        SpaceManController controller = new SpaceManController(world.getPlayer());
        jframe.addKeyListener(controller);
        world.addStepListener(controller);
//        JFrame debugView = new DebugViewer(world, 500, 500);
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new PlayerStateTest();
    }
    
}
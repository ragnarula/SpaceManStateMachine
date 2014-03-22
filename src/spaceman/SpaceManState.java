package spaceman;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;

public abstract class SpaceManState {

    protected static final int WALKSPEED = 5;
    protected static SolidFixture fixture;
    protected static SpaceMan player;
    
    private static SpaceManWalker currentWalker;
    
    protected void setWalking(SpaceManWalker w) {
        if (currentWalker != w) {
            stopWalking();
            player.getWorld().addStepListener(w);
            currentWalker = w;
        }
    }

    protected void stopWalking() {
        if (currentWalker != null) {
            player.getWorld().removeStepListener(currentWalker);
            currentWalker = null;
        }
    }

    public abstract void walk();

    public abstract void shoot();

    public abstract void jump();

    public abstract void crouch();

    public abstract void stand();
}

final class FaceLeft extends SpaceManState {

    private static final BodyImage walkLeftImage = new BodyImage("data/walkleft.gif", 4);
    private static final BodyImage jumpLeftImage = new BodyImage("data/jumpleft.gif", 4);
    private static final BodyImage crouchLeftImage = new BodyImage("data/crouchleft.gif", 4);
    private static final BodyImage standLeftImage = new BodyImage("data/standleft.gif", 4);

    private static final Shape walkLeftShape = new PolygonShape(-0.08f, 1.61f, -1.28f, 0.09f, -0.95f, -2.0f, 0.88f, -1.99f, 1.36f, -0.88f, 0.72f, 1.37f, 0.46f, 1.59f);
    private static final Shape jumpLeftShape = new PolygonShape(0.44f, 1.57f, 0.69f, 1.33f, 0.84f, 0.46f, 0.69f, -1.8f, -0.3f, -1.81f, -1.26f, 0.03f, -0.05f, 1.57f);
    private static final Shape crouchLeftShape = new PolygonShape(0.48f, 1.04f, 0.72f, 0.79f, 0.88f, -0.08f, 1.2f, -2.0f, -0.96f, -2.0f, -1.28f, -0.48f, -0.08f, 1.04f);
    private static final Shape standLeftShape = new PolygonShape(0.96f, -2.0f, 0.89f, 0.48f, 0.71f, 1.36f, 0.48f, 1.59f, -0.08f, 1.6f, -1.27f, 0.07f, -0.8f, -1.99f);

    private final SpaceManWalker walker;

    public FaceLeft(SpaceMan p) {
        FaceLeft.player = p;
        this.walker = new SpaceManWalker(player, -WALKSPEED);
        stand();
    }

    @Override
    public void walk() {
        player.setImage(walkLeftImage);
        FaceLeft.fixture.destroy();
        FaceLeft.fixture = new SolidFixture(player, walkLeftShape);
        setWalking(walker);
        FaceLeft.fixture.setFriction(10);
    }

    @Override
    public void shoot() {

    }

    @Override
    public void jump() {
        player.setImage(jumpLeftImage);
        FaceLeft.fixture.destroy();
        FaceLeft.fixture = new SolidFixture(player, jumpLeftShape);
    }

    @Override
    public void crouch() {
        stopWalking();
        player.setImage(crouchLeftImage);
        FaceLeft.fixture.destroy();
        FaceLeft.fixture = new SolidFixture(player, crouchLeftShape);
        FaceLeft.fixture.setFriction(10);
    }

    @Override
    public void stand() {
        player.setImage(standLeftImage);
        if (FaceLeft.fixture != null) {
            FaceLeft.fixture.destroy();
        }
        stopWalking();
        FaceLeft.fixture = new SolidFixture(player, standLeftShape);
        FaceLeft.fixture.setFriction(10);
    }
}

final class FaceRight extends SpaceManState {

    private static final BodyImage walkRightImage = new BodyImage("data/walkRight.gif", 4);
    private static final BodyImage jumpRightImage = new BodyImage("data/jumpRight.gif", 4);
    private static final BodyImage crouchRightImage = new BodyImage("data/crouchRight.gif", 4);
    private static final BodyImage standRightImage = new BodyImage("data/standRight.gif", 4);

    private static final Shape walkRightShape = new PolygonShape(0.09f, 1.59f, 1.21f, 0.07f, 0.96f, -1.99f, -1.12f, -1.99f, -1.35f, -0.89f, -0.73f, 1.36f, -0.48f, 1.59f);
    private static final Shape jumpRightShape = new PolygonShape(0.06f, 1.58f, 1.28f, 0.07f, 0.33f, -1.85f, -0.39f, -1.85f, -0.71f, -1.53f, -0.88f, 0.48f, -0.72f, 1.36f);
    private static final Shape crouchRightShape = new PolygonShape(0.08f, 1.03f, 1.29f, -0.49f, 0.97f, -2.0f, -1.2f, -2.0f, -0.72f, 0.79f, -0.48f, 1.04f);
    private static final Shape standRightShape = new PolygonShape(-0.48f, 1.59f, -0.71f, 1.36f, -0.87f, 0.48f, -0.96f, -2.02f, 0.79f, -2.01f, 1.29f, 0.0f, 0.08f, 1.6f);

    private final SpaceManWalker walker;

    public FaceRight(SpaceMan p) {
        FaceRight.player = p;
        this.walker = new SpaceManWalker(player, WALKSPEED);
        stand();
    }

    @Override
    public void walk() {
        player.setImage(walkRightImage);
        FaceRight.fixture.destroy();
        FaceRight.fixture = new SolidFixture(player, walkRightShape);
        setWalking(walker);
        FaceRight.fixture.setFriction(10);
    }

    @Override
    public void shoot() {

    }

    @Override
    public void jump() {
        player.setImage(jumpRightImage);
        FaceRight.fixture.destroy();
        FaceRight.fixture = new SolidFixture(player, jumpRightShape);

    }

    @Override
    public void crouch() {
        stopWalking();
        player.setImage(crouchRightImage);
        FaceRight.fixture.destroy();
        FaceRight.fixture = new SolidFixture(player, crouchRightShape);
        FaceRight.fixture.setFriction(10);
    }

    @Override
    public void stand() {
        player.setImage(standRightImage);
        if (FaceRight.fixture != null) {
            FaceRight.fixture.destroy();
        }
        stopWalking();
        FaceRight.fixture = new SolidFixture(player, standRightShape);
        FaceRight.fixture.setFriction(10);
    }

}
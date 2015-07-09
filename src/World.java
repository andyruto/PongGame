import Contants.*;

/**
 * Created by Tonye-Ce on 07.07.15.
 */
public class World {

    private final float width;
    private final float height;

    private Ball ball;

    private Paddle[] paddles;
    private int[] paddleMovement;
    private Key[] keys;

    private Line[] walls;

    private GameScore score;


    // --- settting things up --- //

    public World(float width, float height) {
        this.width = width;
        this.height = height;

        ball = new Ball(new Vector(this.width /2, this.height /2));

        walls = new Line[4];
        walls[WallConstants.LEFT] = new Line(new Vector(0,0), new Vector(0, WindowConstants.HEIGHT), false);
        walls[WallConstants.RIGHT] = new Line(new Vector(WindowConstants.WIDTH,0),
                new Vector(0,WindowConstants.HEIGHT), false);
        walls[WallConstants.TOP] = new Line(new Vector(0,0), new Vector(WindowConstants.WIDTH,0), true);
        walls[WallConstants.BOTTOM] = new Line(new Vector(0,WindowConstants.HEIGHT),
                new Vector(WindowConstants.WIDTH, 0), true);

        paddles = new Paddle[2];
        paddles[PaddleConstants.PADDLE_LEFT] = new Paddle(new Vector(PaddleConstants.OFFSET_X, height/2));
        paddles[PaddleConstants.PADDLE_RIGHT] = new Paddle(new Vector(width - PaddleConstants.OFFSET_X, height/2));

        paddleMovement = new int[2];
        paddleMovement[PaddleConstants.PADDLE_LEFT] = PaddleConstants.MOVING_NOT;
        paddleMovement[PaddleConstants.PADDLE_RIGHT] = PaddleConstants.MOVING_NOT;

        keys = new Key[4];
        keys[KeyConstants.LEFT_UP] = new Key(KeyConstants.LEFT_UP_CHAR);
        keys[KeyConstants.LEFT_DOWN] = new Key(KeyConstants.LEFT_DOWN_CHAR);
        keys[KeyConstants.RIGHT_UP] = new Key(KeyConstants.RIGHT_UP_CHAR);
        keys[KeyConstants.RIGHT_DOWN] = new Key(KeyConstants.RIGHT_DOWN_CHAR);

        score = new GameScore();
    }


    // --- Main Logic --- //

    public void update() {
        movePaddles();
        moveBall();
        handleCollision();
    }

    private void handleCollision() {

        // check for collision with Paddles
        for (int i = 0; i < 2; i++) {
            // check for collision with middle box
            Line line1 = ball.isColliding(paddles[i].getBox(0));
            if (line1 != null) {
                // repell normally
                ball.repell(line1.isHorizontal());
            }
        }
        for (int i = 0; i < 2; i++) {
            // check for collision with middle box
            Line line2 = ball.isColliding(paddles[i].getBox(2));
            if (line2 != null) {
                // repell normally
                ball.repell(line2.isHorizontal());
            }
        }
        for (int i = 0; i < 2; i++) {
            // check for collision with middle box
            Line line3 = ball.isColliding(paddles[i].getBox(1));
            if (line3 != null) {
                // repell normally
                ball.repell(line3.isHorizontal());
            }
        }


        // check for collision with top & bottom walls
        for (int i = 0; i < 2; i++) {
            if (ball.isColliding(walls[i])) {
                // repell normally
                ball.repell(walls[i].isHorizontal());
            }
        }

        // check for collision with left & right walls
        for (int i = 2; i < 4; i++) {
            if (ball.isColliding(walls[i])) {
                // score
                score.score(i == WallConstants.LEFT ? ScoreConstants.PLAYER_2 : ScoreConstants.PLAYER_1);
                // reset ball
                ball.setPosition(new Vector(width/2, height/2));
            }
        }
    }

    private void movePaddles() {
        // potentially move paddles
        if (keys[KeyConstants.LEFT_UP].isPressedDown()) {
            paddles[PaddleConstants.PADDLE_LEFT].moveUp();
        }
        else if (keys[KeyConstants.LEFT_DOWN].isPressedDown()) {
            paddles[PaddleConstants.PADDLE_LEFT].moveDown();
        }

        if (keys[KeyConstants.RIGHT_UP].isPressedDown()) {
            paddles[PaddleConstants.PADDLE_RIGHT].moveUp();
        }
        else if (keys[KeyConstants.RIGHT_DOWN].isPressedDown()) {
            paddles[PaddleConstants.PADDLE_RIGHT].moveDown();
        }

        // update Paddle positions
        paddles[PaddleConstants.PADDLE_LEFT].update();
        paddles[PaddleConstants.PADDLE_RIGHT].update();
    }

    private void moveBall() {
        ball.update();
    }


    // --- input from Graphics --- //

    public void keyPressed(char key) {
        switch (key) {
            case KeyConstants.LEFT_UP_CHAR:
                keys[KeyConstants.LEFT_UP].presDown();
                System.out.println("Left Paddle moving up");
                break;
            case KeyConstants.LEFT_DOWN_CHAR:
                keys[KeyConstants.LEFT_DOWN].presDown();
                System.out.println("Left Paddle moving down");
                break;
            case KeyConstants.RIGHT_UP_CHAR:
                keys[KeyConstants.RIGHT_UP].presDown();
                System.out.println("Right Paddle moving up");
                break;
            case KeyConstants.RIGHT_DOWN_CHAR:
                keys[KeyConstants.RIGHT_DOWN].presDown();
                System.out.println("Rigth Paddle moving down");
        }
    }

    public void keyReleased(char key) {
        switch (key) {
            case KeyConstants.LEFT_UP_CHAR:
                keys[KeyConstants.LEFT_UP].release();
                break;
            case KeyConstants.LEFT_DOWN_CHAR:
                keys[KeyConstants.LEFT_DOWN].release();
                break;
            case KeyConstants.RIGHT_UP_CHAR:
                keys[KeyConstants.RIGHT_UP].release();
                break;
            case KeyConstants.RIGHT_DOWN_CHAR:
                keys[KeyConstants.RIGHT_DOWN].release();
        }
    }


    // --- return all the stuff --- //

    public GameScore getScore() {
        return score;
    }

    public Paddle[] getPeddles() {
        return paddles;
    }

    public Ball getBall() {
        return ball;
    }

}

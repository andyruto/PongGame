import Contants.PaddleConstants;
import Contants.WindowConstants;

/**
 * Created by Tonye-Ce on 07.07.15.
 */
public class Paddle {

    private Vector position;

    private Box[] box = new Box[3];

    private int yMovement;

    private boolean isMovingUp = false;
    private boolean isMovingDown = false;

    public Paddle(Vector position) {
        this.position = position;

        yMovement = 0;

        box[2] = new Box(new Vector(this.position.getX(), this.position.getY() - (PaddleConstants.HEIGHT / 3)),
                PaddleConstants.WIDTH, PaddleConstants.HEIGHT / 3, false);
        box[0] = new Box(this.position, PaddleConstants.WIDTH, PaddleConstants.HEIGHT / 3, false);
        box[1] = new Box(new Vector(this.position.getX(), this.position.getY() + (PaddleConstants.HEIGHT / 3)),
                PaddleConstants.WIDTH, PaddleConstants.HEIGHT / 3, false);

    }

    /**
     * Returnt die Box zu der gegebenen Position.
     * @param index gesuchet Box (0 - mitte, 1 - oben, 2 - unten).
     * @return Die gesuchte Box.
     */
    public Box getBox(int index) {
        return box[index];
    }

    public void moveUp() {
        isMovingUp = true;


    }

    public void moveDown() {
        isMovingDown = true;


    }

    public Vector getPosition() {
        return position;
    }

    public void update() {
        float distanceMoved = 0;

        if (isMovingUp & position.getY() - PaddleConstants.HEIGHT/2 > 0) {
            distanceMoved -= PaddleConstants.MOVEMENT_SPEED;
        }
        else if (isMovingDown & position.getY() + PaddleConstants.HEIGHT/2 < WindowConstants.WORLD_HEIGHT) {
            distanceMoved += PaddleConstants.MOVEMENT_SPEED;
        }

        position.setByCoordinates(position.getX(), position.getY() + distanceMoved);

        box[2] = new Box(new Vector(this.position.getX(), this.position.getY() - (PaddleConstants.HEIGHT / 3)),
                PaddleConstants.WIDTH, PaddleConstants.HEIGHT / 3, false);
        box[0] = new Box(this.position, PaddleConstants.WIDTH, PaddleConstants.HEIGHT / 3, false);
        box[1] = new Box(new Vector(this.position.getX(), this.position.getY() + (PaddleConstants.HEIGHT / 3)),
                PaddleConstants.WIDTH, PaddleConstants.HEIGHT / 3, false);

        isMovingUp = false;
        isMovingDown = false;
    }
}

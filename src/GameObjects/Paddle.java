package GameObjects;

import Contants.PaddleConstants;
import Contants.WindowConstants;

/**
 * Created by Tonye-Ce on 07.07.15.
 */
public class Paddle {

    private Vector position;

    private int yMovement;

    private boolean isMovingUp = false;
    private boolean isMovingDown = false;

    public Paddle(Vector position) {
        this.position = position;
        yMovement = 0;
    }

    /**
     * Returnt die GameObjects.Box zu der gegebenen Position.
     * @param index gesuchet GameObjects.Box (0 - mitte, 1 - oben, 2 - unten).
     * @return Die gesuchte GameObjects.Box.
     */
    public Box getBox(int index) {
        return null;
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
        if (isMovingDown & position.getY() + PaddleConstants.HEIGHT/2 < WindowConstants.WORLD_HEIGHT) {
            distanceMoved += PaddleConstants.MOVEMENT_SPEED;
        }

        position.set(position.getX(), position.getY() + distanceMoved);

        isMovingUp = false;
        isMovingDown = false;
    }
}

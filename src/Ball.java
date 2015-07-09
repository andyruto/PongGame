import Contants.BallConstants;

/**
 * Created by Tonye-Ce on 07.07.15.
 */
public class Ball {

    private Vector position;
    private Vector velocity = new Vector(.4f, 0);

    public Ball(Vector position) {
        this.position = position;
    }

    public void update() {

        position.setByCoordinates(position.getX() + velocity.getX(), position.getY() + velocity.getY());
        System.out.println("Me: " + position.toString() + "; My Friend: " + velocity.toString());

    }

    public boolean isColliding(Line line) {
        // Ist die Linie Horizontal?
        if (line.isHorizontal()) {
        // ja
            // Ist der Ball auf der Y-Ebene?
            if (Math.abs(line.getLocation().getY() - position.getY()) <= BallConstants.RADIUS
                    || (line.getLocation().getY() - position.getY()) == BallConstants.RADIUS * (-1)) {
            // ja
                // Ist der Ball im X-Bereich?
                if ((position.getX() - BallConstants.RADIUS) >= line.getLocation().getX()
                       && (position.getX() - BallConstants.RADIUS) <=
                        line.getLocation().addVectors(line.getDirection()).getX()) {
                // ja
                    // kollision
                    return true;
                }
                else {
                // nein
                    // keine kollision
                    return false;
                }
            }
            else {
            // nein
                // keine Kollision
                return false;
            }
        }
        else {
        // nein
            // Ist der Ball auf der X-Ebene?
            if (Math.abs(line.getLocation().getX() - position.getX()) <= BallConstants.RADIUS) {
            // ja
                // Ist der Ball im Y-Bereich?
                if ((position.getY() - BallConstants.RADIUS) >= line.getLocation().getY()
                        && (position.getY() - BallConstants.RADIUS) <=
                        line.getLocation().addVectors(line.getDirection()).getY()) {
                // ja
                    // kollision
                    return true;
                }
                else {
                // nein
                    // keine kollsion
                    return false;
                }
            }
            else {
            // nein
                // keine Kollision
                return false;
            }
        }

    }

    /**
     * Fragt ob der Ball mit einer der Linien in einer Box kollidiert.
     * @param box Abzufragende Box
     * @return Die Linie mit der der Ball kollidiert. (null falls es keine Kollision gibt)
     */
    public Line isColliding(Box box) {

        int collidingLineIndex = -1;
        Line line[] = new Line[4];

        // declare line array
        line[0] = box.getLine(0);
        line[1] = box.getLine(1);
        line[2] = box.getLine(2);
        line[3] = box.getLine(3);

        // check for collision with line
        for (int j = 0; j < 4; j++) {
            if (isColliding(line[j])) {
                collidingLineIndex = j;
            }
        }

        if (collidingLineIndex == -1) {
            return null;
        }
        else {
            return line[collidingLineIndex];
        }
    }

    /**
     * L�sst den Ball von etwas abprallen
     * @param repellVertically wahr wenn der ball vertikal abprallen soll (d.h. gegen eine horizontale wand prallt)
     */
    public void repell(boolean repellVertically) {

        /**
         * x speichert den X-Wert des velocity-Vektors
         * y speichert den Y-Wert des velocity-Vektors
         */

        float x = velocity.getX();
        float y = velocity.getY();

        if (repellVertically) {
            velocity.setByCoordinates(x, y * -1);
        }
        else {
            velocity.setByCoordinates(x * -1, y);
        }

    }

    /**
     * L�sst den Ball von etwas abprallen
     * @param repellVertically wahr wenn der ball vertikal abprallen soll (d.h. gegen eine horizontale wand prallt)
     */
    public void repellSrong(boolean repellVertically) {

        if (repellVertically) {

        }
        else {

        }

    }

    public void setPosition(Vector position) {

        this.position = position;

    }

    public Vector getPosition() {
        return position;
    }

}
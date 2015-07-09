package GameObjects;

/**
 * Created by Tonye-Ce on 07.07.15.
 */
public class Line {

    private final Vector location;
    private final Vector direction;
    private boolean isHorizontal;

    public Line(Vector location, Vector direction, boolean isHorizontal) {
        this.location = location;
        this.direction = direction;
        this.isHorizontal = isHorizontal;
    }

    public Vector getLocation() {
        return location;
    }

    public Vector getDirection() {
        return direction;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }
}

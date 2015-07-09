import Contants.BoxConstants;

/**
 * Created by Tonye-Ce on 07.07.15.
 */
public class Box {

    private Line[] lines = new Line[4];

    public Box(Vector position, int width, int height, boolean isHorizontal) {
        lines[BoxConstants.BOX_TOP] = new Line(new Vector(position.getX() - width/2, position.getY() + height/2),
                new Vector(width, 0), true);
        lines[BoxConstants.BOX_RIGHT] = new Line(new Vector(position.getX() + width/2, position.getY() - height/2),
                new Vector(0, height), false);
        lines[BoxConstants.BOX_BOTTOM] = new Line(new Vector(position.getX() - width/2, position.getY() - height/2),
                new Vector(width, 0), true);
        lines[BoxConstants.BOX_LEFT] = new Line(new Vector(position.getX() - width/2, position.getY() - height/2),
                new Vector(0, height), false);
    }

    public Line getLine(int index) {
        return lines[index];
    }

}

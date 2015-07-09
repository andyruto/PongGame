/**
 * Created by Tonye-Ce on 07.07.15.
 */
public class Vector {

    private float x;
    private float y;

    public Vector(float x, float y) {

        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadian() {
        return 0;
    }

    public void setByCoordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setByRadian(float value, float radian){



    }

    /**
     * Addiert ein Vector-Objekt auf aktuellen Vector.
     * @param vector
     */
    public void addVector(Vector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
    }

    /**
     * Addiert zwei Vectoren in ein neues Vektor-Objekt.
     * @param vector
     * @return Das Ergebnis aus der Addition der beiden Vektoren. (Ergebnis ist ein Vector-Objekt)
     */
    public Vector addVectors(Vector vector) {
        return new Vector(getX() + vector.getX(), getY() + vector.getY());
    }

    @Override
    public String toString() {
        return "[" + getX() + "|" + getY() + "]";
    }
}

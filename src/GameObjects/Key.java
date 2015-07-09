package GameObjects;

/**
 * Created by Tonye-Ce on 09.07.15.
 */
public class Key {

    private char keyChar;
    private boolean isPressedDown;

    public Key(char keyChar) {
        this.keyChar = keyChar;
        this.isPressedDown = false;
    }

    public void presDown() {
        isPressedDown = true;
    }

    public void release() {
        isPressedDown = false;
    }

    public boolean isPressedDown() {
        return isPressedDown;
    }

    public char getKeyChar() {
        return keyChar;
    }

}

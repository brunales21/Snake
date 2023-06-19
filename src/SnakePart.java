import javax.swing.*;
import java.awt.*;

public class SnakePart extends JPanel {
    private SnakePart nextPart;
    private Vector2 position;
    private Vector2 direction;

    public SnakePart(SnakePart snakePart, Vector2 position, Vector2 direction) {
        this.nextPart = snakePart;
        this.position = position;
        this.direction = direction;

        setBackground(Color.BLUE);
    }
    public SnakePart(Vector2 position, Vector2 direction) {
        this(null, position, direction);
    }

    public SnakePart(SnakePart snakePart) {
        this(snakePart, null, null);
    }

    public SnakePart(Vector2 position) {
        this.position = position;
        setBackground(Color.BLUE);
    }

    public SnakePart() {

    }

    public SnakePart getNextPart() {
        return nextPart;
    }

    public void setNextPart(SnakePart nextPart) {
        this.nextPart = nextPart;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public boolean hasNextPart() {
        return nextPart != null;
    }
}

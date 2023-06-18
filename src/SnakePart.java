import javax.swing.*;
import java.awt.*;

public class SnakePart {
    private SnakePart nextPart;
    private JPanel skin;
    private Vector2 position;
    private Vector2 direction;

    public SnakePart(SnakePart snakePart, Vector2 position, Vector2 direction) {
        this.nextPart = snakePart;
        this.skin = new JPanel();
        this.position = position;
        this.direction = direction;

        this.skin.setBackground(Color.BLUE);
    }
    public SnakePart(Vector2 position, Vector2 direction) {
        this(null, position, direction);
    }

    public SnakePart(SnakePart snakePart) {
        this(snakePart, null, null);
    }

    public SnakePart(Vector2 position) {
        this.position = position;
        this.skin = new JPanel();
        this.skin.setBackground(Color.BLUE);
    }

    public SnakePart() {

    }

    public SnakePart getNextPart() {
        return nextPart;
    }

    public void setNextPart(SnakePart nextPart) {
        this.nextPart = nextPart;
    }

    public JPanel getSkin() {
        return skin;
    }

    public void setSkin(JPanel skin) {
        this.skin = skin;
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

import javax.swing.*;
import java.awt.*;

public class Snake {
    private Vector2 position;
    private Vector2 direction;
    private JPanel skin;

    public Snake() {
        this.position = new Vector2(15, 15);
        this.direction = Vector2.RIGHT;
        this.skin = new JPanel();
        this.skin.setBackground(Color.MAGENTA);
        this.skin.requestFocus();

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

    public JPanel getSkin() {
        return skin;
    }

    public void setSkin(JPanel skin) {
        this.skin = skin;
    }
}

import javax.swing.*;

public class Casilla extends JPanel {
    private Vector2 position;

    public Casilla(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}

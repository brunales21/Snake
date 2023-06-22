import javax.swing.*;

public class Food extends JPanel {
    private Vector2 position;

    public Food() {
        this.position = new Vector2();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);

    }
}

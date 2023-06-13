public class Vector2 {
     static final Vector2 UP = new Vector2(0, -1);
     static final Vector2 DOWN = new Vector2(0, 1);
     static final Vector2 RIGHT = new Vector2(1, 0);
     static final Vector2 LEFT = new Vector2(-1, 0);

    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

import java.awt.*;

public class Snake {
    private SnakePart[] snakeParts;
    private SnakePart head;
    private final int MAX_LENGTH = 50;
    private final int STARTING_LENGTH = 6;
    private int snakeLen = STARTING_LENGTH;
    private final Vector2 startingPosition = new Vector2(13, 20);
    private final Vector2 startingDirection = Vector2.RIGHT;

    public Snake() {
        initParts();
        initSnake();
        head = snakeParts[0];
        head.setDirection(startingDirection);
        head.setBackground(Color.green);
    }

    public void initParts() {
        snakeParts = new SnakePart[MAX_LENGTH];
        for (int i = 0; i < STARTING_LENGTH; i++) {
            snakeParts[i] = new SnakePart(new Vector2(startingPosition.getX()-i, startingPosition.getY()));
        }
    }
    public void initSnake() {
        for (int i = 0; i < STARTING_LENGTH-1; i++) {
            snakeParts[STARTING_LENGTH-i-1].setNextPart(snakeParts[STARTING_LENGTH-i-2]);
        }
    }

    public void grow() {
        Vector2 direction = head.getDirection();
        snakeParts[snakeLen] = new SnakePart();
        snakeParts[snakeLen].setNextPart(snakeParts[snakeLen-1]);
        snakeParts[snakeLen].getPosition().set(snakeParts[snakeLen].getNextPart().getPosition().getX()-direction.getX(), snakeParts[snakeLen].getNextPart().getPosition().getY()-direction.getY());
        snakeLen++;
    }

    public void move() {
        for (int i = snakeLen-1; i >= 0; i--) {
            if (i > 0) {
                snakeParts[i].setPosition(snakeParts[i].getNextPart().getPosition());
            } else {
                head.setPosition(new Vector2(head.getPosition().getX()+head.getDirection().getX(), head.getPosition().getY()+head.getDirection().getY()));
            }
        }
    }

    public SnakePart getHead() {
        return head;
    }
    public void setHead(SnakePart head) {
        this.head = head;
    }
    public SnakePart[] getSnakeParts() {
        return snakeParts;
    }

    public int getSnakeLen() {
        return snakeLen;
    }

    public void setSnakeLen(int snakeLen) {
        this.snakeLen = snakeLen;
    }
}

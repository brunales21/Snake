import java.awt.*;

public class Snake {
    private SnakePart[] snake;
    private SnakePart head;
    private final int MAX_LENGTH = 50;
    private final int STARTING_LENGTH = 20;
    private int snakeLen = STARTING_LENGTH;
    private final Vector2 startingPosition = new Vector2(13, 20);
    private final Vector2 startingDirection = Vector2.RIGHT;

    public Snake() {
        initParts();
        initSnake();
        head = snake[0];
        head.setDirection(startingDirection);
        head.getSkin().setBackground(Color.green);
    }

    public void initParts() {
        snake = new SnakePart[MAX_LENGTH];
        for (int i = 0; i < STARTING_LENGTH; i++) {
            snake[i] = new SnakePart(new Vector2(startingPosition.getX()-i, startingPosition.getY()));
        }
    }
    public void initSnake() {
        for (int i = 0; i < STARTING_LENGTH-1; i++) {
            snake[STARTING_LENGTH-i-1].setNextPart(snake[STARTING_LENGTH-i-2]);
        }
    }

    public void grow() {
        snake[snakeLen +1] = new SnakePart(snake[snakeLen]);
        snakeLen++;
    }

    public void move() {
        for (int i = snakeLen-1; i >= 0; i--) {
            if (i > 0) {
                snake[i].setPosition(snake[i].getNextPart().getPosition());
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
    public SnakePart[] getSnake() {
        return snake;
    }

    public int getSnakeLen() {
        return snakeLen;
    }

    public void setSnakeLen(int snakeLen) {
        this.snakeLen = snakeLen;
    }
}

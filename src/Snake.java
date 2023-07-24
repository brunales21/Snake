import javax.swing.*;
import java.awt.*;

public class Snake {
    private JPanel board;
    private SnakePart[] snakeParts;
    private SnakePart head;
    private final int MAX_LENGTH = 500;
    private final int STARTING_LENGTH = 6;
    private int snakeLen = STARTING_LENGTH;
    private final Vector2 startingPosition = new Vector2(13, 20);
    private final Vector2 startingDirection = Vector2.RIGHT;

    public Snake(JPanel board) {
        this.board = board;
        initParts();
        initSnake();
        head = snakeParts[0];
        head.setDirection(startingDirection);
        head.setBackground(Color.BLUE);
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

    public void grow(int n) {
        for (int i = 0; i < n; i++) {
            Vector2 direction = head.getDirection();
            snakeParts[snakeLen] = new SnakePart();
            snakeParts[snakeLen].setNextPart(snakeParts[snakeLen-1]);
            snakeParts[snakeLen].getPosition().set(snakeParts[snakeLen].getNextPart().getPosition().getX()-direction.getX(), snakeParts[snakeLen].getNextPart().getPosition().getY()-direction.getY());
            snakeLen++;
        }

    }


    public void move() throws SnakeOutOfBounds, SelfCollideException {
        for (int i = snakeLen-1; i >= 0; i--) {
            if (i > 0) {
                snakeParts[i].setPosition(snakeParts[i].getNextPart().getPosition());
            } else {
                head.setPosition(new Vector2(head.getPosition().getX()+head.getDirection().getX(), head.getPosition().getY()+head.getDirection().getY()));
                if (collidesItSelf()) {
                    throw new SelfCollideException();
                }
                if (isOutOfBounds(head.getPosition())) {
                    throw new SnakeOutOfBounds();
                }
            }

        }
    }

    private boolean isOutOfBounds(Vector2 position) {
        return position.getX()> ((GridLayout)board.getLayout()).getRows() || position.getX()<0 || position.getY()>((GridLayout)board.getLayout()).getColumns() || position.getY()<0;
    }

    private boolean collidesItSelf() {
        for (int i = 1; i < snakeLen; i++) {
            if (head.getPosition().equals(snakeParts[i].getPosition())) {
                return true;
            }
        }
        return false;
    }
    public void eatEffect() {
        for (int i = 0; i < getSnakeLen(); i++) {
            getSnakeParts()[i].setBackground(Color.MAGENTA);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            getSnakeParts()[i].setBackground(Color.BLUE);
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

    public int getStartingLen() {
        return STARTING_LENGTH;
    }
}

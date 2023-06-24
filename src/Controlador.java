import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Controlador implements KeyListener {
    private static int DELAY = 101; // Intervalo de actualización en milisegundos
    private Vista vista;
    private Snake snake;
    private Food food;
    private Random rand;
    private SnakeAnimation snakeAnimation;
    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (isEating()) {
                snake.grow();
                placeApple();
            }
            showSnake();
            try {
                snake.move();
            } catch (SnakeOutOfBounds | SelfCollideException e) {
                //System.out.println(e.getMessage());
                timer.cancel();
            }
        }
    };


    public Controlador() {
        initObjects();
        placeApple();
        this.snakeAnimation = new SnakeAnimation(this.snake, this);

    }

    public void initObjects() {
        initVista();
        initSnake();
        initApple();
        initTimer();
        initOtherStaff();
    }


    public boolean isEating() {
        return snake.getHead().getPosition().getX() == food.getPosition().getX() && snake.getHead().getPosition().getY() == food.getPosition().getY();
    }

    private void initApple() {
        this.rand = new Random();
        this.food = new Apple();
        food.setBackground(Color.RED);
    }

    private void placeApple() {
        do {
            food.getPosition().set(rand.nextInt(vista.getRows()), rand.nextInt(vista.getCols()));
        } while (!canPlaceApple(food.getPosition()));
        vista.getBoard().getComponent(getIndex(food.getPosition())).setBackground(food.getBackground());
    }

    private boolean canPlaceApple(Vector2 position) {
        return vista.getBoard().getComponent(getIndex(position)).getBackground().equals(vista.getBoardBackground());
    }

    private int getIndex(Vector2 position) {
        return position.getY() * vista.getCols() + position.getX();
    }


    private void showSnake() {
        for (int i = 0; i < snake.getSnakeLen(); i++) {
            int index = snake.getSnakeParts()[i].getPosition().getY() * vista.getCols() + snake.getSnakeParts()[i].getPosition().getX();
            try {
                if (i < snake.getSnakeLen() - 1) {
                    vista.getBoard().getComponent(index).setBackground(snake.getSnakeParts()[i].getBackground());
                } else {
                    vista.getBoard().getComponent(index).setBackground(vista.getBoardBackground());
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("saliste");
            }

        }
    }

    private static final Map<Integer, Vector2> map = Map.of(KeyEvent.VK_UP, Vector2.UP, KeyEvent.VK_DOWN, Vector2.DOWN, KeyEvent.VK_RIGHT, Vector2.RIGHT, KeyEvent.VK_LEFT, Vector2.LEFT);

    @Override
    public void keyPressed(KeyEvent e) {
        snake.getHead().setDirection(map.get(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void initVista() {
        this.vista = new Vista();
    }

    public void initSnake() {
        this.snake = new Snake(this.vista.getBoard());
    }

    public void initTimer() {
        this.timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, DELAY);
    }
    private void initOtherStaff() {
        this.vista.getWindow().addKeyListener(this);
    }

}

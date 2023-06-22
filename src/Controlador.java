import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Controlador implements KeyListener {
    private static final int DELAY = 100; // Intervalo de actualización en milisegundos
    private Vista vista;
    private Snake snake;
    private Food food;
    private Random rand;
    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (isEating()) {
                //System.out.println("come");
                snake.grow();
                //snake.getHead().setBackground(Color.MAGENTA);
                placeApple();
            }
            showSnake();
            snake.move();
        }
    };


    public Controlador() {
        initObjects();
        placeApple();
    }

    public void initObjects() {
        initVista();
        initSnake();
        initApple();
        initTimer();

        this.rand = new Random();
    }

    private boolean isEating() {
        return snake.getHead().getPosition().getX() == food.getPosition().getX() && snake.getHead().getPosition().getY() == food.getPosition().getY();
    }

    private void initApple() {
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
            if (i < snake.getSnakeLen() - 1) {
                vista.getBoard().getComponent(index).setBackground(snake.getSnakeParts()[i].getBackground());
            } else {
                vista.getBoard().getComponent(index).setBackground(vista.getBoardBackground());

            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            snake.getHead().setDirection(Vector2.UP);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            snake.getHead().setDirection(Vector2.DOWN);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            snake.getHead().setDirection(Vector2.LEFT);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            snake.getHead().setDirection(Vector2.RIGHT);
        }
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
        this.snake = new Snake();
    }

    public void initTimer() {
        this.timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, DELAY);
        this.vista.getWindow().addKeyListener(this);
    }
}

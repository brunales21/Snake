import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Controlador implements KeyListener {
    private Vista vista;
    private Snake snake;
    private static final int DELAY = 100; // Intervalo de actualización en milisegundos
    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            showSnake();
            snake.move();
        }
    };

    public Controlador() {
        this.vista = new Vista();
        this.snake = new Snake();

        this.timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, DELAY);
        this.vista.getWindow().addKeyListener(this);
    }

    private void showSnake() {
        for (int i = 0; i < snake.getSnakeLen(); i++) {
            int index = vista.getCols()*snake.getSnake()[i].getPosition().getY()+snake.getSnake()[i].getPosition().getX();
            if (i < snake.getSnakeLen()-1) {
                vista.getBoard().getComponent(index).setBackground(snake.getSnake()[i].getSkin().getBackground());
            } else {
                vista.getBoard().getComponent(index).setBackground(Color.black);
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
}

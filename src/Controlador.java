import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Controlador implements KeyListener {
    private Vista vista;
    private Snake snake;
    private JPanel apple;
    private Random rand;
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
        this.rand = new Random();
        initApple();

        this.timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, DELAY);
        this.vista.getWindow().addKeyListener(this);
    }

    private void initApple() {
        this.apple = new JPanel();
        apple.setBackground(Color.GREEN);
    }

    private void placeApple() {
        do {
            int x = rand.nextInt(vista.getRows());
            int y = rand.nextInt(vista.getCols());
            vista.getBoard().getComponent(vista.getCols()*y+x);
        }
    }

    private boolean canPlaceApple(Vector2 position) {
        return vista.getBoard().getComponent(vista.getCols()*position.getY()+position.getX()).getBackground().equals(Casilla.getBackground());
    }



    private void showSnake() {
        for (int i = 0; i < snake.getSnakeLen(); i++) {
            int index = vista.getCols()*snake.getSnake()[i].getPosition().getY()+snake.getSnake()[i].getPosition().getX();
            if (i < snake.getSnakeLen()-1) {
                vista.getBoard().set
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

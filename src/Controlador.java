import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Controlador implements KeyListener {
    private Vista vista;
    private Snake snake;
    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            moveSnake();
        }
    };
    private static final int DELAY = 75; // Intervalo de actualización en milisegundos

    public Controlador() {
        this.vista = new Vista();
        this.snake = new Snake();
        this.timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, DELAY);
        this.vista.getWindow().addKeyListener(this);
        this.snake.getSkin().setFocusable(true);
        showSnake();
    }
    public void moveSnake() {
        snake.setPosition(new Vector2(snake.getPosition().getX()+snake.getDirection().getX(), snake.getPosition().getY()+snake.getDirection().getY()));
        showSnake();

    }
    private void showSnake() {
        this.vista.getBoard().getComponent(this.vista.getRows()*this.snake.getPosition().getY()+this.snake.getPosition().getX()).setBackground(snake.getSkin().getBackground());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            snake.setDirection(Vector2.UP);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            snake.setDirection(Vector2.DOWN);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            snake.setDirection(Vector2.LEFT);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            snake.setDirection(Vector2.RIGHT);
        }
        moveSnake();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeAnimation {
    private Timer timer;
    private Snake snake;
    private Controlador controlador;
    private Color color = new Color(0, 155, 0);


    public SnakeAnimation(Snake snake, Controlador controlador) {
        this.snake = snake;
        this.controlador = controlador;
        initTimer();
    }

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (controlador.isEating()) {
                snake.eatEffect();
            }
        }
    };

    public void initTimer() {
        this.timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 10);
    }
}

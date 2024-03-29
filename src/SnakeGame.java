import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.*;

public class SnakeGame implements KeyListener {
    private boolean autoMode;
    private static int DELAY = 35; // Intervalo de actualización en milisegundos
    private Vista vista;
    private Snake snake;
    private Food food;
    private Random rand;
    private SnakeAnimation snakeAnimation;
    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (autoMode) {
                modoEspectador();
            } else {
                singlePlayer();
            }
        }
    };

    private void singlePlayer() {
        snake.setApple(food);
        if (isEating()) {
            playSound("bonus1.wav");
            snake.grow(1);
            placeApple();
        }
        showSnake();
        try {
            snake.move();
        } catch (SnakeOutOfBounds | SelfCollideException e) {
            gameOverRutine();
        }
    }

    private void modoEspectador() {
        snake.setApple(food);
        if (isEating()) {
            playSound("bonus1.wav");
            snake.grow(0);
            placeApple();
        }
        showSnake();
        try {
            snake.move();
        } catch (SnakeOutOfBounds e) {
            gameOverRutine();
        } catch (SelfCollideException ignore) {
            //ignore
        }
    }
    public SnakeGame(boolean auto) {
        this.autoMode = auto;
        startGame(auto);
    }

    public void startGame(boolean auto) {
        initVista();
        initSnake();
        initApple();
        placeApple();
        initTimer();
        initAnimation();
        initOtherStaff();
        if (auto) {
            snake.start();
        }
    }

    public static void playSound(String name) {
        File soundFile = new File("sounds/"+name);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            //clip.close();
            //audioInputStream.close();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
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
            food.getPosition().set(rand.nextInt(vista.getRows()-2)+1, rand.nextInt(vista.getCols()-2)+1);
        } while (!canPlaceApple(food.getPosition()));
        vista.getBoard().getComponent(getIndex(food.getPosition())).setBackground(food.getBackground());
        snake.setApple(food);
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
            } catch (ArrayIndexOutOfBoundsException ignore) {
                //ignore
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
        this.snake = new Snake(this.vista.getBoard(), DELAY);
    }

    private void initTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, DELAY);
    }

    private void initOtherStaff() {
        this.vista.getWindow().addKeyListener(this);
    }

    private void initAnimation() {
        this.snakeAnimation = new SnakeAnimation(this.snake, this);
    }

    public void threadSleep(int n) {
        try {
            Thread.sleep(n* 1000L);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void gameOverRutine() {
        playSound("fail.wav");
        ThreadUtils.esperar(2000);
        vista.instanceEndWindow();
        vista.showScore(snake.getSnakeLen()-snake.getStartingLen());
        timer.cancel();
    }
}

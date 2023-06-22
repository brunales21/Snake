import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Vista extends JFrame {
    private JFrame window;
    private JPanel board;
    private Component[] components;
    private Color boardBackground;
    private int rows;
    private int cols;
    public Vista() {
        initWindow();
        initBoard();
        components = board.getComponents();
    }

    private void initWindow() {
        rows = 40;
        cols = 40;
        components = new Component[rows*cols];
        window = new JFrame("Snake");
        board = new JPanel(new GridLayout(rows, cols));

        window.setVisible(true);
        window.setSize(700, 700);
        window.setLocationRelativeTo(null);
        board.setSize(400, 400);
        board.setVisible(true);

        window.add(board);
    }

    private void initBoard() {
        this.boardBackground = Color.black;
        boolean flag = true;

        for (int i = 0; i < rows; i++) {
            flag = !flag;
            for (int j = 0; j < cols; j++) {
                Casilla casilla = new Casilla(new Vector2(i, j));
                if (flag) {
                    casilla.setBackground(boardBackground);
                } else {
                    casilla.setBackground(boardBackground);
                }
                board.add(casilla);
                flag = !flag;
            }
        }
    }

    public void reemplazarCasilla(int x, int y, JPanel nuevoPanel) {
        int index = y * cols + x;
        components[index] = nuevoPanel;
        repaintBoard();
    }

    public Color getBoardBackground() {
        return boardBackground;
    }

    public void setBoardBackground(Color boardBackground) {
        this.boardBackground = boardBackground;
    }

    public void repaintBoard() {
        board.removeAll();
        for (Component component: components) {
            board.add(component);
        }
    }

    public JFrame getWindow() {
        return window;
    }

    public JPanel getBoard() {
        return board;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}

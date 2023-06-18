import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Vista extends JFrame {
    private JFrame window;
    private JPanel board;
    private int rows;
    private int cols;
    public Vista() {
        initWindow();
        initBoard();
    }

    private void initWindow() {
        rows = 40;
        cols = 40;
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
        boolean flag = true;

        for (int i = 0; i < rows; i++) {
            flag = !flag;
            for (int j = 0; j < cols; j++) {
                JPanel casilla = new JPanel();
                if (flag) {
                    casilla.setBackground(Color.black);
                } else {
                    casilla.setBackground(Color.black);
                }
                board.add(casilla);
                flag = !flag;
            }
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

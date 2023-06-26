import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Vista extends JFrame {
    private JFrame window;
    private JPanel board;
    private JPanel endWindow;
    private Component[] components;
    private Color boardBackground;
    private int rows;
    private int cols;
    private JButton playAgainButton;

    public Vista() {
        initWindow();
        initBoard();
        window.revalidate();
        window.repaint();
        board.revalidate();
        board.repaint();
        components = board.getComponents();
        playAgainButton = new JButton("Jugar de nuevo");

    }

    public void initEndWindow() {
        this.endWindow = new JPanel(new BorderLayout());
        this.endWindow.setSize(300, 300);
        this.endWindow.setBackground(Color.BLACK);
    }

    public void instanceEndWindow() {
        board.setVisible(false);
        initEndWindow();
        endWindow.setVisible(true);
        this.window.add(endWindow);

        JTextPane text = new JTextPane();
        text.setPreferredSize(new Dimension(200, 100));
        text.setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setText("Game Over");
        text.setFont(text.getFont().deriveFont(Font.BOLD, 24));

        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet centerStyle = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerStyle, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), centerStyle, false);

/*
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        playAgainButton.setPreferredSize(new Dimension(150, 40));
        playAgainButton.setFont(playAgainButton.getFont().deriveFont(Font.BOLD, 14));
        playAgainButton.setBackground(Color.GREEN);
        playAgainButton.setForeground(Color.black);
        playAgainButton.setFocusPainted(false);
        playAgainButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(100, 0, 330, 0); // Espacio superior
        buttonPanel.add(playAgainButton, gbc);

 */

        this.endWindow.add(text, BorderLayout.CENTER);

        this.endWindow.revalidate();
        this.endWindow.repaint();
    }

    private void initWindow() {
        rows = 30;
        cols = 30;
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


    public Color getBoardBackground() {
        return boardBackground;
    }

    public void setBoardBackground(Color boardBackground) {
        this.boardBackground = boardBackground;
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

    public void setWindow(JFrame window) {
        this.window = window;
    }

    public void setBoard(JPanel board) {
        this.board = board;
    }

    public JPanel getEndWindow() {
        return endWindow;
    }

    public void setEndWindow(JPanel endWindow) {
        this.endWindow = endWindow;
    }

    @Override
    public Component[] getComponents() {
        return components;
    }

    public void setComponents(Component[] components) {
        this.components = components;
    }

    public JButton getPlayAgainButton() {
        return playAgainButton;
    }
}

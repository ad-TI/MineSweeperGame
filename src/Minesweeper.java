import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.event.MouseEvent;

/**
 * The Minesweeper class creates a Minesweeper game GUI with functionality for
 * playing the
 * game, placing flags, and checking game status.
 */
public class Minesweeper extends JFrame {
    private Board board;
    private JButton[][] buttons;
    private JLabel minesLabel;// to show number of mines
    public JLabel clickLabel;// to show number of clicks
    private int revealedCells = 0;// number of cells revealed

    public int rows;// number of rows
    public int cols;// number of columns
    public static int mine_count;// number of mines
    public int clickCount;// number of clicks

    Random random = new Random();

    /**
     * Minesweeper constructor initializes the attributes and performs the game
     */
    public Minesweeper() {
        this.rows = 8;
        this.cols = 8;
        mine_count = 10;

        this.board = new Board(mine_count, rows, cols);

        this.buttons = new JButton[rows][cols];

        this.setTitle("Minesweeper");
        this.setSize(520, 550);
        this.setLocation(430, 50);
        this.setResizable(false);

        JPanel gameBoard = new JPanel(new GridLayout(rows, cols));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new JButton("");
                // set the image for each tile
                buttons[i][j].setIcon(new ImageIcon("tile.png"));

                int row = i;
                int col = j;

                // When a button is clicked, the actionPerformed method inside the
                // ActionListener interface is
                // triggered
                buttons[i][j].addActionListener(new ActionListener() {
                    /**
                     * the action performed when a button is clicked, updating
                     * the game board and checking for a game-winning condition.
                     * 
                     * @param e The parameter "e" in the actionPerformed method represents the
                     *          ActionEvent that occurred, such as a button click
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        buttonClick(row, col);

                        checkGame();
                    }
                });

                // The above code is adding a MouseListener to each button in a 2D array of
                // buttons.
                // When a button is right-clicked, it checks if the button currently has an icon
                // of
                // "tile.png". If it does, it replaces the icon with "flag.png" and sets a flag
                // on the
                // board. If the button does not have the "tile.png" icon, it removes the flag
                // by
                // setting the icon back to "tile.png" and unsetting the flag on the board.
                // Additionally, when the mouse enters a button, it changes the background color
                // to
                // light gray, and when the
                // adding a MouseListener to each button in the array of buttons
                buttons[i][j].addMouseListener(new MouseAdapter() {
                    /**
                     * method handles right-click events on buttons to toggle between
                     * placing and removing flags.
                     * 
                     * @param e The parameter `e` contains information about the mouse event that
                     *          occurred, such as the type of mouse button clicked
                     */
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // when a button is right clicked
                        if (SwingUtilities.isRightMouseButton(e)) {
                            ImageIcon current = (ImageIcon) buttons[row][col].getIcon();
                            // checks if the button doesn't already have a flag then place it
                            if (current != null && current.getImage().equals(new ImageIcon("tile.png").getImage())) {
                                // Place a flag
                                buttons[row][col].setIcon(new ImageIcon("flag.png"));
                                board.setFlagged(true);
                            } else {
                                // if there is already a flag, remove the flag
                                buttons[row][col].setIcon(new ImageIcon("tile.png"));
                                board.setFlagged(false);
                            }
                        }
                    }

                    /**
                     * The method changes the background color of a button when the mouse enters it.
                     * 
                     * @param e contains information about the mouse event that occurred
                     */
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        buttons[row][col].setBackground(Color.LIGHT_GRAY);
                    }

                    /**
                     * This method changes the background color of a button to null when the mouse
                     * exits the button area.
                     * 
                     * @param e provides information about the event
                     */
                    @Override
                    public void mouseExited(MouseEvent e) {
                        buttons[row][col].setBackground(null);
                    }

                });
                gameBoard.add(buttons[i][j]);

            }
        }
        add(gameBoard);

        // create the click panel and label
        JPanel clickPanel = new JPanel(new BorderLayout());
        clickPanel.setLayout(new BorderLayout(10, 0));
        this.clickLabel = new JLabel("0", SwingConstants.CENTER);
        this.clickCount = 0;

        JLabel imageClick = new JLabel("", SwingConstants.CENTER);
        imageClick.setIcon(new ImageIcon("clock.png"));

        clickLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        clickPanel.add(imageClick, BorderLayout.WEST);
        clickPanel.add(clickLabel, BorderLayout.CENTER);

        // create mine panel and label
        JPanel minePanel = new JPanel();
        minePanel.setLayout(new BorderLayout(10, 0));
        this.minesLabel = new JLabel(Integer.toString(mine_count), SwingConstants.CENTER);

        JLabel imageMine = new JLabel(new ImageIcon("bg.png"), SwingConstants.CENTER);
        imageMine.setIcon(new ImageIcon("mine.png"));

        minesLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        minePanel.add(minesLabel, BorderLayout.WEST);
        minePanel.add(imageMine, BorderLayout.CENTER);

        // add click panel and mine panel
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout(0, 20));
        p.add(clickPanel, BorderLayout.WEST);
        p.add(minePanel, BorderLayout.EAST);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 10));
        panel.add(gameBoard, BorderLayout.CENTER);
        panel.add(p, BorderLayout.SOUTH);

        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // show instructions before the game begins
        String message = "Welcome to Minesweeper!\n\n"
                + "You are a mine detector and your task is to"
                + " find the land mines in the field.\nThe field is a two-dimensional board of squares\n"
                + "containing either mines, or the number of mines next to the square. The objective is\n"
                + "to clear every square that doesn't contain a mine using logical reasoning based\n"
                + "on the number squares you have cleared.\nIf you click a square that has a mine game ends.\n"
                + "If the square has a number of mines adjacent to it greater than 0, the number is revealed.\n"
                + "If the square has zero mines next to it, the game clears all the nearby squares, since all\n"
                + "squares next to a '0' would be safe.\n\n"
                + "How to play:\n"
                + "- Left-click to reveal a cell.\n"
                + "- Right-click to place or remove a flag.\n Note: You can use flags to mark a square you think is a mine\n\n"
                + "GOOD LUCK!";
        JOptionPane.showMessageDialog(this, message, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * The buttonClick mehtod handles the logic for clicking on a cell in a game
     * board,
     * updating the click count, revealing cells, checking for mines, and ending the
     * game if a mine is
     * clicked
     * 
     * @param row The row parameter represents the row index of the cell that was
     *            clicked on the game
     *            board
     * @param col The `col` parameter represents the column index of the cell that
     *            was clicked in the
     *            game board
     */
    public void buttonClick(int row, int col) {
        // increment the click count if a tile without a mine is clicked which has
        // not yet been revealed
        if (!board.isRevealed(row, col) && !board.hasMine(row, col)) {
            clickCount++;
            clickLabel.setText(" " + clickCount);
        }
        // show the solution and execute gameOver if a tile
        // with a mine is clicked
        if (board.hasMine(row, col)) {

            revealAllCells();
            buttons[row][col].setIcon(new ImageIcon("redmine.png"));
            gameOver();
        }
        // check if player has won
        else {
            revealCell(row, col);
            if (revealedCells == (rows * cols - mine_count)) {
                checkGame();
            }
        }
    }

    /**
     * The method getTextColor returns a Color based on the input number, with
     * default color as
     * black.
     * 
     * @param number If `number` matches one of the cases (1 to 8), a specific color
     *               is returned
     * @return returns a `Color` object based on the input `number`
     */
    private Color getTextColor(int number) {
        switch (number) {
            case 1:
                return Color.BLUE;
            case 2:
                return (new Color(0, 153, 0));
            case 3:
                return Color.RED;
            case 4:
                return Color.magenta;
            case 5:
                return Color.BLACK;
            case 6:
                return Color.DARK_GRAY;
            case 7:
                return Color.ORANGE;
            case 8:
                return Color.CYAN;
            default:
                return Color.BLACK;

        }
    }

    /**
     * The `revealCell` method recursively reveals adjacent cells in a Minesweeper
     * game board,
     * updating the GUI with the number of adjacent mines or clearing the cell if
     * there are zero adjacent
     * mines.
     * 
     * @param row The `row` parameter represents the row index of the cell
     * @param col The `col` parameter represents the column index of the
     *            cell
     */
    private void revealCell(int row, int col) {

        if (!board.isRevealed(row, col)) {
            int count = board.countAdjacentMines(row, col);
            // if there are no mines adjacent
            if (count == 0) {
                buttons[row][col].setIcon(null);
                buttons[row][col].setText("");
                board.setRevealed(row, col, true);
                revealedCells++;

                // ensure that the iteration stays between the bounds of the board using
                // Math.max and Math.min
                for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, board.getRows() - 1); i++) {
                    for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, board.getCols() - 1); j++) {
                        // Only reveal if the cell does not have a mine
                        revealCell(i, j);
                    }
                }
            } else {
                buttons[row][col].setText(Integer.toString(count));
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 25));
                buttons[row][col].setForeground(getTextColor(count));
                buttons[row][col].setIcon(null);
                board.setRevealed(row, col, true);
            }
        }
    }

    /**
     * The `revealAllCells` method iterates through all cells on the board, setting
     * the mine icon for
     * cells with mines and revealing non-mine cells.
     */
    private void revealAllCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board.hasMine(i, j)) {
                    // If it's a mine, set the mine icon
                    buttons[i][j].setIcon(new ImageIcon("mine.png"));
                } else {
                    // If it's not a mine, reveal the cell
                    revealCell(i, j);
                }
            }
        }
    }

    /**
     * The `gameOver` method displays a message dialog indicating that the game is
     * over, showing the
     * player's score and an image, and then exits the program.
     */
    private void gameOver() {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20)); // Adjust the font size here
        JOptionPane.showMessageDialog(this,
                "Game Over! You hit a mine.\n Your score was: " + clickCount + "\nCheck the board for the solution!",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("sad.gif"));
        System.exit(0);
    }

    /**
     * The method checks if all non-mine cells on the board are revealed to
     * determine if the game is
     * finished.
     * 
     * @return returns a boolean value indicating whether the game is finished
     *         or not
     */
    public boolean isFinished() {
        for (int x = 0; x < board.getCols(); x++) {
            for (int y = 0; y < board.getRows(); y++) {
                // If a non-mine cell is not revealed, the game is not finished
                if (!board.hasMine(x, y) && !board.isRevealed(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * The `checkGame` method displays a message with the player's score and exits
     * the
     * program if the game is finished.
     */
    private void checkGame() {
        if (isFinished()) {
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
            JOptionPane.showMessageDialog(this, "Congratulations! You won!\n Your score was: " + clickCount, "Won!!",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon("congrats.gif"));
            System.exit(0);
        }
    }
}

import javax.swing.SwingUtilities;

/**
 * The Main class initializes the Minesweeper game using
 * SwingUtilities.invokeLater().
 */
public class Main {
    /**
     * The main function creates a new instance of the Minesweeper game
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Minesweeper();
        });
    }
}

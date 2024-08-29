import java.util.Random;

/**
 * The `Board` class represents a game board with spaces that can contain mines
 * and tracks adjacent
 * mines for each space.
 */
public class Board extends Space {
    private Space[][] board;
    private int numOfMines;
    private int row;
    private int col;

    /**
     * The Board constructor initializes a new game board with the specified number
     * of mines, rows and columns
     */
    public Board(int numOfMines, int row, int col) {
        this.row = row;
        this.col = col;
        this.numOfMines = numOfMines;
        this.board = new Space[row][col];

        createEmptyBoard();

        setMines(numOfMines);
    }

    /**
     * The method creates an empty board by initializing a 2D array of Space
     * objects.
     */
    public void createEmptyBoard() {
        board = new Space[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                board[i][j] = new Space();
            }
        }
    }

    /**
     * The method sets a specified number of mines randomly on a board.
     * 
     * @param minesToPlace specifies the number of mines that need to be
     *                     randomly placed on the game board.
     */
    public void setMines(int minesToPlace) {
        Random random = new Random();
        while (minesToPlace > 0) {
            int x = random.nextInt(row);
            int y = random.nextInt(col);
            if (!board[x][y].hasMine()) {
                board[x][y].setMine(true);
                minesToPlace--;
            }
        }
    }

    /**
     * The method counts the number of adjacent mines around a given cell on a game
     * board.
     * 
     * @param x represents the x-coordinate of a cell on the game board where you
     *          want to count the number of adjacent mines.
     * @param y represents the y-coordinate of a cell on the game board
     * @return The count of adjacent mines around the cell at position is being
     *         returned.
     */
    public int countAdjacentMines(int x, int y) {
        int count = 0;
        for (int i = Math.max(0, x - 1); i <= Math.min(x + 1, row - 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(y + 1, col - 1); j++) {
                if (board[i][j].hasMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * The method returns the value of the `row` attribute.
     * 
     * @return returning the value of the instance variable `row`.
     */
    public int getRows() {
        return this.row;
    }

    /**
     * The method returns the number of columns in the object.
     * 
     * @return returning the value of the instance variable `col`.
     */
    public int getCols() {
        return this.col;
    }

    /**
     * The method `hasMine` in Java checks if a specific cell on the board has a
     * mine.
     * 
     * @param row represents the row index of the cell on the game board.
     * @param col represents the column index in a 2D board where you want to check
     *            for the presence of a mine.
     * @return returns a boolean value indicating whether there
     *         is a mine at the specified row and column on the game board.
     */
    public boolean hasMine(int row, int col) {
        return board[row][col].hasMine();
    }

    /**
     * The method checks if a specific cell on the board is revealed.
     * 
     * @param row represents the row index of a cell on the game board.
     * @param col represents the column index in a two-dimensional array or grid.
     * @return returning a boolean value indicating whether
     *         the cell at the specified row and column on the board is revealed or
     *         not.
     */
    public boolean isRevealed(int row, int col) {
        return board[row][col].isRevealed();
    }

    /**
     * The method sets the revealed status of a specific cell on the board
     * 
     * @param row      represents the row index of the cell on the game board where
     *                 you
     *                 want to set the revealed status
     * @param col      represents the column index in a two-dimensional array or
     *                 grid.
     *                 It specifies the column where you want to set the revealed
     *                 status for a particular cell in the
     *                 grid
     * @param revealed a boolean value that indicates whether a cell in a board
     *                 should be revealed or not
     */
    public void setRevealed(int row, int col, boolean revealed) {
        board[row][col].setRevealed(revealed);
    }

}

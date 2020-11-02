import java.util.Random;

public class GameCode {

    public int COLUMNS;
    public int ROWS;
    /** Grid holds the array of game numbers.
     * It is 2 elements larger than COLUMNS and ROWS in order to be
     16
     * surrounded by a ring of extra zeroes.
     * <p>This ring of zeroes mostly just simplifies canPlay().</p> */
    private final int[][] grid;

    /**
     * printGame() will print out the contents of the internal table
     * to stdOut, representing the current game state.
     */
    public void printGame() {
        for (int row = 1; row <= ROWS; row++) {
            for (int col = 1; col <= COLUMNS; col++) {
                System.out.printf("%d\t", grid[col][row]);
            }
            System.out.println();
        }
    }

    /**
     * Initialize a new game of the given dimensions.
     * Once set, the dimensions cannot be modified.
     *
     * @param columns The number of columns in the game grid.
     * @param rows The number of rows in the game grid.
     * @throws IllegalArgumentException Thrown if rows or columns are < 2
     */
    public GameCode(final int columns, final int rows) throws IllegalArgumentException {
        if (columns < 2 || rows < 2)
            throw new IllegalArgumentException("Rows and columns must both be >= 2.");
        this.COLUMNS = columns;
        this.ROWS = rows;
        grid = new int[columns + 2][rows + 2];
        for (int col = 0; col < COLUMNS + 2; col++) {
            for (int row = 0; row < ROWS + 2; row++) {
                grid[col][row] = 0;
            }
        }
    }

    /**
     * Get the value of a particular cell in the 2D game grid array.
     *
     * @param col The column to query in the game grid.
     * @param row The row to query in the game grid.
     * @return The value of the given array cell.
     * @throws IndexOutOfBoundsException If the row or column is outside the game range.
     */
    public NumSquare getCellValue(int col, int row) throws IndexOutOfBoundsException {
        if (col < 0 || col >= COLUMNS || row < 0 || row >= ROWS)
            throw new IndexOutOfBoundsException();
        int i = grid[col + 1][row + 1];
        NumSquare num=new NumSquare(i);
        return num;
    }

    /**
     * Set the value of a particular cell in the 2D game grid array.
     *
     * @param col The column to target in the game grid.
     * @param row The row to target in the game grid.
     *
     * @throws IndexOutOfBoundsException If the row or column is outside the game range.
     */
    public void setCellValue(int col, int row, int value) throws IndexOutOfBoundsException {
        if (col < 0 || col >= COLUMNS || row < 0 || row >= ROWS)
            throw new IndexOutOfBoundsException();
        grid[col+1][row+1] = value;
    }

    /**
     * Push all grid values to the left, filling in empty squares (0s).
     * If two equal numbers are pushed together,
     * they will combine to a larger number, double the size.
     */

    public void slideLeft() {
        int destCol;
        for (int row = 1; row <= ROWS; row++) {
            destCol = 1;
            for (int column = 2; column <= COLUMNS; column++) {
                if (destCol == column || grid[column][row] == 0) {
                    continue;
                } else if (grid[column][row] == grid[destCol][row]) {
                    grid[destCol][row] = grid[destCol][row] * 2;
                    grid[column][row] = 0;
                    destCol++;
                } else {
                    if (grid[destCol][row] != 0)
                        destCol++;
                    if (destCol != column) {
                        grid[destCol][row] = grid[column][row];
                        grid[column][row] = 0;
                    }
                }
            }
        }
    }

    /**
     * Push all grid values to the right, filling in empty squares (0s).
     * If two equal numbers are pushed together,
     * they will combine to a larger number, double the size.
     */
    public void slideRight() {
        int destCol;
        for (int row = 1; row <= ROWS; row++) {
            destCol = COLUMNS;
            for (int column = COLUMNS - 1; column >= 1; column--) {
                if (destCol == column || grid[column][row] == 0) {
                    continue;
                } else if (grid[column][row] == grid[destCol][row]) {
                    grid[destCol][row] = grid[destCol][row] * 2;
                    grid[column][row] = 0;
                    destCol--;
                } else {
                    if (grid[destCol][row] != 0)
                        destCol--;
                    if (destCol != column) {
                        grid[destCol][row] = grid[column][row];
                        grid[column][row] = 0;
                    }
                }
            }
        }
    }

    /**
     * Push all grid values to the top, filling in empty squares (0s).
     * If two equal numbers are pushed together,
     * they will combine to a larger number, double the size.
     */
    public void slideUp() {
        int destRow;
        for (int column = 1; column <= COLUMNS; column++) {
            destRow = 1;
            for (int row = 2; row <= ROWS; row++) {
                if (destRow == row || grid[column][row] == 0) {
                    continue;
                } else if (grid[column][row] == grid[column][destRow]) {
                    grid[column][destRow] = grid[column][destRow] * 2;
                    grid[column][row] = 0;
                    destRow++;
                } else {
                    if (grid[column][destRow] != 0)
                        destRow++;
                    if (destRow != row) {
                        grid[column][destRow] = grid[column][row];
                        grid[column][row] = 0;
                    }
                }
            }
        }
    }

    /**
     * Push all grid values to the bottom, filling in empty squares (0s).
     * If two equal numbers are pushed together,
     * they will combine to a larger number, double the size.
     */
    public void slideDown() {
        int destRow;
        for (int column = 1; column <= COLUMNS; column++) {
            destRow = ROWS;
            for (int row = ROWS - 1; row >= 1; row--) {
                if (destRow == row || grid[column][row] == 0) {
                    continue;
                } else if (grid[column][row] == grid[column][destRow]) {
                    grid[column][destRow] = grid[column][destRow] * 2;
                    grid[column][row] = 0;
                    destRow--;
                } else {
                    if (grid[column][destRow] != 0)
                        destRow--;
                    if (destRow != row) {
                        grid[column][destRow] = grid[column][row];
                        grid[column][row] = 0;
                    }
                }
            }
        }
    }

    /**
     * Place a new 2 at a random empty place in the game grid.
     * An empty place is any cell with a value of 0
     *
     * @return
     */
    public boolean addNew2() {
        int col;
        int row;
        Random random = new Random();

        if (isFull()) {
            return false;
        }

        do {
            col = random.nextInt(COLUMNS) + 1;
            row = random.nextInt(ROWS) + 1;
        } while (grid[col][row] != 0);

        grid[col][row] = 2;
//        System.out.println(col+" "+row);
        return true;
    }

    /**
     * Determine if there are any possible moves left.
     *
     * @return True if there are empty spaces or it is possible to combine two cells
     */
    public boolean canPlay() {
        if (!isFull())
            return true;

        for (int col = 1; col <= COLUMNS; col++) {
            for (int row = 1; row <= ROWS; row++) {
                if (grid[row][col] == grid[row + 1][col]
                        || grid[row][col] == grid[row][col + 1]
                        || grid[row][col] == grid[row - 1][col]
                        || grid[row][col] == grid[row][col - 1])
                    return true;
            }
        }
        return false;
    }

    /**
     * Tally the values of all cells in the game grid.
     *
     * @return The sum of all cells in the game grid.
     */
    public int getScore() {
        int score = 0;
        for (int col = 1; col <= COLUMNS; col++) {
            for (int row = 1; row <= ROWS; row++) {
                score += grid[col][row];
            }
        }
        return score;
    }

    /**
     * Check if the game grid is full (i.e. there are no more empty spaces or zeroes.)
     *
     * @return True if there is no more empty (0) cells in the game grid.
     */
    public boolean isFull() {
        for (int column = 1; column <= COLUMNS; column++) {
            for (int row = 1; row <= ROWS; row++) {
                if (grid[column][row] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
import java.util.Random;

public class GameCode {

    public int COLUMNS;
    public int ROWS;
    private final int[][] grid;
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


    public NumSquare getCellValue(int col, int row) throws IndexOutOfBoundsException {
        if (col < 0 || col >= COLUMNS || row < 0 || row >= ROWS)
            throw new IndexOutOfBoundsException();
        int i = grid[col + 1][row + 1];
        NumSquare num=new NumSquare(i);
        return num;
    }

    public void slideUp() {
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

    public void slideDown() {
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

    public void slideLeft() {
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

    public void slideRight() {
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

    public int getScore() {
        int score = 0;
        for (int col = 1; col <= COLUMNS; col++) {
            for (int row = 1; row <= ROWS; row++) {
                score += grid[col][row];
            }
        }
        return score;
    }

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
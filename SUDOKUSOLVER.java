public class SudokuSolver {
    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
                {7, 0, 0, 0, 0, 0, 2, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 8, 0, 5, 1, 0},
                {0, 0, 3, 0, 0, 0, 0, 0, 0},
                {0, 9, 0, 1, 0, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 6, 0, 4, 0},
                {5, 0, 4, 0, 2, 0, 0, 6, 0},
                {0, 0, 0, 6, 0, 0, 1, 0, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 8}
        };

        if (solveBoard(board)) {
            System.out.println("Solved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("Unsolvable board");
        }
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                // Look for empty cells (represented by 0)
                if (board[row][col] == 0) {
                    // Try possible numbers (1 to 9)
                    for (int num = 1; num <= 9; num++) {
                        if (isValidPlacement(board, num, row, col)) {
                            board[row][col] = num;

                            // Recursively attempt to solve the rest of the board
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                // Backtrack if the current choice doesn't work
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false; // Trigger backtracking
                }
            }
        }
        return true; // The board is solved
    }

    private static boolean isValidPlacement(int[][] board, int num, int row, int col) {
        // Check the row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check the column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check the 3x3 subgrid
        int subGridRow = (row / 3) * 3;
        int subGridCol = (col / 3) * 3;
        for (int i = subGridRow; i < subGridRow + 3; i++) {
            for (int j = subGridCol; j < subGridCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true; // Valid placement
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col] == 0 ? "." : board[row][col]);
            }
            System.out.println();
        }
    }
}

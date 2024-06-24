import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY_SLOT = '.';
    private static final char PLAYER_ONE_TOKEN = 'X';
    private static final char PLAYER_TWO_TOKEN = 'O';
    
    private static char[][] board = new char[ROWS][COLUMNS];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        boolean playerOneTurn = true;
        boolean gameWon = false;

        while (true) {
            int column;
            if (playerOneTurn) {
                System.out.print("Player One, choose a column (0-6): ");
            } else {
                System.out.print("Player Two, choose a column (0-6): ");
            }

            column = scanner.nextInt();

            if (column < 0 || column >= COLUMNS || !isColumnValid(column)) {
                System.out.println("Invalid column. Try again.");
                continue;
            }

            if (playerOneTurn) {
                dropToken(column, PLAYER_ONE_TOKEN);
                if (checkForWin(PLAYER_ONE_TOKEN)) {
                    System.out.println("Player One wins!");
                    gameWon = true;
                }
            } else {
                dropToken(column, PLAYER_TWO_TOKEN);
                if (checkForWin(PLAYER_TWO_TOKEN)) {
                    System.out.println("Player Two wins!");
                    gameWon = true;
                }
            }

            printBoard();

            if (gameWon) {
                break;
            }

            playerOneTurn = !playerOneTurn;

            if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY_SLOT;
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isColumnValid(int column) {
        return board[0][column] == EMPTY_SLOT;
    }

    private static void dropToken(int column, char token) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == EMPTY_SLOT) {
                board[i][column] = token;
                break;
            }
        }
    }

    private static boolean checkForWin(char token) {
        // Check horizontal win
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (board[row][col] == token && board[row][col + 1] == token &&
                    board[row][col + 2] == token && board[row][col + 3] == token) {
                    return true;
                }
            }
        }

        // Check vertical win
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (board[row][col] == token && board[row + 1][col] == token &&
                    board[row + 2][col] == token && board[row + 3][col] == token) {
                    return true;
                }
            }
        }

        // Check diagonal win (bottom left to top right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (board[row][col] == token && board[row - 1][col + 1] == token &&
                    board[row - 2][col + 2] == token && board[row - 3][col + 3] == token) {
                    return true;
                }
            }
        }

        // Check diagonal win (top left to bottom right)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (board[row][col] == token && board[row + 1][col + 1] == token &&
                    board[row + 2][col + 2] == token && board[row + 3][col + 3] == token) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col] == EMPTY_SLOT) {
                return false;
            }
        }
        return true;
    }
}
